package org.example.madeinha.domain.cluster.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.cluster.entity.RedisCluster;
import org.example.madeinha.domain.cluster.repository.RedisClusterRepository;
import org.example.madeinha.domain.kickboard.converter.KickboardConverter;
import org.example.madeinha.domain.kickboard.dto.response.KickboardResponse;
import org.example.madeinha.domain.kickboard.entity.Redis.RedisKickboard;
import org.example.madeinha.domain.kickboard.repository.Redis.RedisKickboardRepository;
import org.example.madeinha.domain.kickboard.service.RedisKickboardService;
import org.example.madeinha.global.Json.JsonConverter;
import org.example.madeinha.global.Json.JsonDTO;
import org.example.madeinha.global.error.BusinessException;
import org.example.madeinha.global.error.code.ClusterErrorCode;
import org.example.madeinha.global.gRPC.AlgorithmServiceClient;
import org.example.madeinha.global.gRPC.dto.GrpcDTO;
import org.example.madeinha.global.redis.RedisService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.lang.Math.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClusterService {

    private final RedisKickboardRepository redisKickboardRepository;
    private final RedisClusterRepository redisClusterRepository;
    private final RedisKickboardService kickboardService;
    private final AlgorithmServiceClient client;
    private final KickboardConverter kickboardConverter;
    private final JsonConverter jsonConverter;
    private final RedisService redisService;

    @Transactional
    public void updateKickboardInfo() throws JsonProcessingException {
        Iterable<RedisKickboard> kickboardList = redisKickboardRepository.findAll();
        KickboardResponse.AllKickboardInfo infoList = kickboardConverter.toAllKickboardInfoUseIter(kickboardList);
        String request = jsonConverter.makeDbScanRequest(infoList);

        //db스캔 돌리고 갱신된 킥보드 저장
        String response = client.dbScan(request);

        GrpcDTO.dbScanResponse dbScanResponses = jsonConverter.updateKickboardInfo(response);
        List<GrpcDTO.dbScanCluster> clusterList = dbScanResponses.getCluster_list();

        //redis에 바뀐 clusterId를 갱신
        //바뀐 parkingZone 갱신
        for (GrpcDTO.dbScanCluster cluster : clusterList) {
            Integer clusterId = cluster.getCluster_id();
            List<Long> list = cluster.getKickboard_list();
            for (Long id : list) {
                RedisKickboard kickboard = kickboardService.findKickboardById(id);
                kickboard.setClusterId(clusterId);
                if (clusterId > 0) {
                    kickboard.setParkingZone(2);
                } else {
                    kickboard.setParkingZone(3);
                }
                redisKickboardRepository.save(kickboard);
            }
        }
        makePolygon();
    }

    @Transactional
    public void makePolygon() throws JsonProcessingException {
        String request = "";
        String response = client.convexHull(request);
        JsonDTO.JsonAllClusterInfo jsonAllClusterInfo = jsonConverter.parseJson(response);

        List<JsonDTO.JsonClusterInfo> clusterList = jsonAllClusterInfo.getClusterList();
        List<RedisCluster> list = clusterList.stream().map(
                cluster -> new RedisCluster(cluster.getClusterId(), cluster.getKickboardList(),cluster.getCentLat(),cluster.getCentLng())
        ).toList();
        redisClusterRepository.deleteAll();
        redisClusterRepository.saveAll(list);
    }

    public List<RedisCluster> getAllClusterInfo() {
        Iterable<RedisCluster> clusterList = redisClusterRepository.findAll();

        return StreamSupport.stream(clusterList.spliterator(),false)
                .toList();
    }

    public RedisCluster findClusterById(Integer clusterId) {
        return redisClusterRepository.findById(clusterId).orElseThrow(
                () -> new BusinessException(ClusterErrorCode.CLUSTER_NOT_FOUND_BY_ID)
        );
    }

    public RedisCluster getNearestCluster(Double lat, Double lng) {
        List<RedisCluster> allClusterInfo = getAllClusterInfo();

        double rad_lat = toRadians(lat);
        double rad_lng = toRadians(lng);

        double nearest = Double.MAX_VALUE;
        Integer clusterId = 0;

        for (RedisCluster rc : allClusterInfo) {
            int size = kickboardService.getKickboardInfoByClusterId(rc.getCluster_id()).size();
            if (size >= 10) continue; // 군집에서 10대 이상이면 추천 X

            double cent_lat = toRadians(rc.getCent_lat());
            double cent_lng = toRadians(rc.getCent_lng());

            double dLat = rad_lat - cent_lat;
            double dLng = rad_lng - cent_lng;

            double a = sin(dLat / 2) * sin(dLat / 2)
                    + cos(cent_lat) * cos(rad_lat)
                    * sin(dLng / 2) * sin(dLng / 2);

            double c = 2 * atan2(sqrt(a), sqrt(1 - a));

            double distance = 6371000.0 * c;
            if (nearest > distance) {
                nearest = distance;
                clusterId = rc.getCluster_id();
            }
        }

        return findClusterById(clusterId);
    }

}
