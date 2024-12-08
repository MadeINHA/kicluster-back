package org.example.madeinha.domain.fixedArea.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.example.madeinha.domain.fixedArea.entity.AreaType;
import org.example.madeinha.domain.fixedArea.entity.FixedArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FixedAreaRepository extends JpaRepository<FixedArea,Long> {
    Optional<FixedArea> findByAreaName(String name);

    Optional<FixedArea> findByAreaId(Long id);

    List<FixedArea> findAllByAreaType(AreaType areaType);

    @Query(value = "SELECT * FROM fixed_area " +
            "WHERE ST_Contains(" +
            "zone, ST_PointFromText(CONCAT('POINT(', :longitude, ' ', :latitude, ')'), 4326))" +
            "LIMIT 1",
            nativeQuery = true)
    Optional<FixedArea> findAreaContainingPoint(@Param("latitude") double latitude,
                                             @Param("longitude") double longitude);


    @Query(
            value = "SELECT * FROM fixed_area " +
                    "WHERE area_type = 'EXIST' " +
                    "ORDER BY ST_Distance(zone, ST_GeomFromText(CONCAT('POINT(', :lat, ' ', :lng, ')'))) ASC " +
                    "LIMIT 1",
            nativeQuery = true
    )
    FixedArea findNearestExistFixedArea(@Param("lat") double lat, @Param("lng") double lng);
}
