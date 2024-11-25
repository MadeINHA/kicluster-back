package org.example.madeinha.domain.DesignatedParkingArea.entity;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Polygon;

@Entity
@Table(name = "designated_parking_area")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DesignatedParkingArea {
    // 기존에 지정되어 있는 주차 구역 정보
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_area_id")
    private Long parkingAreaId; //주차구역 id

    @Column(nullable = false)
    private String parkingAreaName; //구역 이름

    @Column(name = "zone", columnDefinition = "POLYGON", nullable = false)
    private Polygon zone; //주차 구역 다각형


}
