package org.example.madeinha.domain.kickboard.service;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.kickboard.converter.KickboardConverter;
import org.example.madeinha.domain.kickboard.dto.request.KickboardRequest;
import org.example.madeinha.domain.kickboard.entity.RDB.Kickboard;
import org.example.madeinha.domain.kickboard.entity.Redis.RedisKickboard;
import org.example.madeinha.domain.kickboard.repository.RDB.KickboardRepository;
import org.example.madeinha.domain.kickboard.repository.Redis.RedisKickboardRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.example.madeinha.domain.kickboard.dto.request.KickboardRequest.RegisterRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KickboardService {

    private final KickboardRepository kickboardRepository;
    private final KickboardConverter kickboardConverter;
    private final RedisKickboardRepository redisKickboardRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Transactional
    public List<Kickboard> register(RegisterRequest request) {
        List<KickboardRequest.Register> registerList = request.getRegisterList();
        Coordinate[] array = registerList.stream().map(
                register -> new Coordinate(register.getLng(), register.getLat())
        ).toArray(Coordinate[]::new);
        List<Kickboard> list = Arrays.stream(array).map(
                kickboardConverter::toEntity
        ).toList();

        return kickboardRepository.saveAll(list);
    }

    @Transactional
    @Scheduled(cron = "0 0 6,12,18,0 * * *")
    public void updateRdbByRedis() {
        Iterable<RedisKickboard> redisKickboards = redisKickboardRepository.findAll();

        for (RedisKickboard redisKickboard : redisKickboards) {
            Long id = redisKickboard.getKickboardId();

            Kickboard kickboard = kickboardRepository.findById(id).get();
            if (!kickboard.getClusterId().equals(redisKickboard.getClusterId())) {
                kickboard.setClusterId(redisKickboard.getClusterId());
            }
            if (!kickboard.getParkingZone().equals(redisKickboard.getParkingZone())) {
                kickboard.setParkingZone(redisKickboard.getParkingZone());
            }
            Point point = geometryFactory.createPoint(new Coordinate(redisKickboard.getLongitude(), redisKickboard.getLatitude()));
            if (!kickboard.getLocation().equals(point)) {
                kickboard.setLocation(point);
            }
            if (!kickboard.getActing().equals(redisKickboard.getActing())) {
                kickboard.setActing(redisKickboard.getActing());
            }
        }
    }

}
