package org.example.madeinha.domain.kickboard.repository.RDB;

import org.example.madeinha.domain.kickboard.entity.RDB.Kickboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KickboardRepository extends JpaRepository<Kickboard, Long> {
}
