package org.example.madeinha.domain.fixedArea.repository;

import org.example.madeinha.domain.fixedArea.entity.AreaType;
import org.example.madeinha.domain.fixedArea.entity.FixedArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FixedAreaRepository extends JpaRepository<FixedArea,Long> {
    Optional<FixedArea> findByAreaName(String name);

    Optional<FixedArea> findByAreaId(Long id);

    List<FixedArea> findAllByAreaType(AreaType areaType);

}
