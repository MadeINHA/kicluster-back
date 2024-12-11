package org.example.madeinha.domain.history.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.example.madeinha.domain.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {
    Optional<History> findByKickboardId(Long kickboardId);

    @Query(
            value = "SELECT COUNT(*)>0 " +
                    "FROM history " +
                    "WHERE kickboard_id = :kickboardId " +
                    "AND ST_Contains(zone, ST_PointFromText(CONCAT('POINT(', :longitude, ' ', :latitude, ')')))",
            nativeQuery = true)
    Long checkReturn(@Param("kickboardId") Long kickboardId,
                        @Param("latitude") double latitude,
                        @Param("longitude") double longitude);

    Boolean existsByKickboardId(Long kickboardId);
}
