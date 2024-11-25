package org.example.madeinha.domain.DesignatedParkingArea.repository;

import org.example.madeinha.domain.DesignatedParkingArea.entity.DesignatedParkingArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DesignatedParkingAreaRepository extends JpaRepository<DesignatedParkingArea, Long> {
    Optional<DesignatedParkingArea> findByParkingAreaName(String name);

    Optional<DesignatedParkingArea> findByParkingAreaId(Long id);
}
