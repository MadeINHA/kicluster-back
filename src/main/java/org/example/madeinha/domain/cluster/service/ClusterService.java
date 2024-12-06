package org.example.madeinha.domain.cluster.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.cluster.entity.RedisCluster;
import org.example.madeinha.domain.cluster.repository.RedisClusterRepository;
import org.example.madeinha.domain.kickboard.converter.KickboardConverter;
import org.example.madeinha.domain.kickboard.dto.response.KickboardResponse;
import org.example.madeinha.domain.kickboard.entity.Redis.RedisKickboard;
import org.example.madeinha.domain.kickboard.repository.Redis.RedisKickboardRepository;
import org.example.madeinha.global.Json.JsonConverter;
import org.example.madeinha.global.Json.JsonDTO;
import org.example.madeinha.global.gRPC.AlgorithmServiceClient;
import org.example.madeinha.global.gRPC.dto.GrpcDTO;
import org.example.madeinha.global.redis.RedisService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClusterService {

    private final RedisKickboardRepository redisKickboardRepository;
    private final RedisClusterRepository redisClusterRepository;
    private final AlgorithmServiceClient client;
    private final KickboardConverter kickboardConverter;
    private final JsonConverter jsonConverter;
    private final RedisService redisService;

    @Transactional
    @Scheduled(fixedRate = 30000) //5초마다 실행
    public void updateKickboardInfo() throws JsonProcessingException {
        Iterable<RedisKickboard> kickboardList = redisKickboardRepository.findAll();
        KickboardResponse.AllKickboardInfo infoList = kickboardConverter.toAllKickboardInfoUseIter(kickboardList);
        String request = jsonConverter.makeDbScanRequest(infoList);

        //db스캔 돌리고 갱신된 킥보드 저장
        String response = client.dbScan(request);

        GrpcDTO.dbScanResponse dbScanResponses = jsonConverter.updateKickboardInfo(response);
        List<GrpcDTO.dbScanCluster> clusterList = dbScanResponses.getCluster_list();

        //redis에 바뀐 clusterId를 갱신
        for (GrpcDTO.dbScanCluster cluster : clusterList) {
            Integer clusterId = cluster.getCluster_id();
            List<Long> list = cluster.getKickboard_list();
            list.forEach(
                    kickboardId -> redisService.updateClusterId(kickboardId, clusterId)
            );
        }

        makePolygon();
        System.out.println(LocalDateTime.now() + " : 알고리즘 수행");
    }

    @Transactional
    public void makePolygon() throws JsonProcessingException {
        String request = "";
        String response = client.convexHull(request);
        JsonDTO.JsonAllClusterInfo jsonAllClusterInfo = jsonConverter.parseJson(response);

        List<JsonDTO.JsonClusterInfo> clusterList = jsonAllClusterInfo.getClusterList();
        List<RedisCluster> list = clusterList.stream().map(
                cluster -> new RedisCluster(cluster.getClusterId(), cluster.getKickboardList())
        ).toList();

        redisClusterRepository.saveAll(list);
    }

    public List<RedisCluster> getAllClusterInfo() {
        Iterable<RedisCluster> clusterList = redisClusterRepository.findAll();

        return StreamSupport.stream(clusterList.spliterator(),false)
                .toList();
    }

}
