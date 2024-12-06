package org.example.madeinha.domain.kickboard.entity.RDB;

import jakarta.persistence.*;
import lombok.*;
import org.example.madeinha.global.entity.BaseTimeEntity;
import org.hibernate.annotations.SQLRestriction;
import org.locationtech.jts.geom.Point;


@Entity
@Table(name = "kickboards")
@SQLRestriction("deleted_at is NULL")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Kickboard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kickboard_id")
    private Long kickboardId;

    @Column(nullable = false, columnDefinition = "GEOMETRY")
    private Point location;

    private Integer clusterId; // 군집 정보

    private Integer parkingZone; // 소속 주차구역

    private Boolean acting; //킥보드 사용 여부

}
