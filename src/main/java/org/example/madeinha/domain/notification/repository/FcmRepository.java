package org.example.madeinha.domain.notification.repository;

import org.example.madeinha.domain.notification.entity.FCM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FcmRepository extends JpaRepository<FCM,Long> {

}
