package org.example.madeinha.domain.kickboard.service;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.kickboard.converter.KickboardConverter;
import org.example.madeinha.domain.kickboard.entity.RDB.Kickboard;
import org.example.madeinha.domain.kickboard.repository.RDB.KickboardRepository;
import org.example.madeinha.domain.kickboard.repository.Redis.RedisKickboardRepository;
import org.example.madeinha.global.error.BusinessException;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.example.madeinha.domain.kickboard.dto.request.KickboardRequest.RegisterRequest;
import static org.example.madeinha.global.error.code.KickboardErrorCode.KICKBOARD_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KickboardService {

    private final KickboardRepository kickboardRepository;
    private final KickboardConverter kickboardConverter;

    @Transactional
    public Kickboard register(RegisterRequest request) {
        Coordinate coordinate = new Coordinate(request.getLongitude(), request.getLatitude());
        Kickboard kickboard = kickboardConverter.toEntity(coordinate);
        kickboardRepository.save(kickboard);

        return kickboard;
    }

    @Transactional
    public void registerKickboardList(List<Kickboard> kickboardList) {
        kickboardRepository.saveAll(kickboardList);
    }

    public Kickboard findKickboard(Long kickboardId) {
        return kickboardRepository.findById(kickboardId)
                .orElseThrow(()-> new BusinessException(KICKBOARD_NOT_FOUND_BY_ID));
    }

    public Coordinate location(Long kickboardId) {
        Kickboard kickboard = findKickboard(kickboardId);
        return kickboard.getLocation().getCoordinate();
    }

    @Transactional
    public List<Kickboard> getKickboardsByClusterId(Integer clusterId) {
        return kickboardRepository.findByClusterId(clusterId);
    }
}
