package org.example.madeinha.domain.fixedArea.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Polygon;


@Entity
@Table(name = "fixed_area")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FixedArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long areaId; //주차구역 id

    @Column(nullable = false)
    private String areaName; //구역 이름

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AreaType areaType;

    @Column(name = "zone", columnDefinition = "POLYGON", nullable = false)
    private Polygon zone; //주차 구역 다각형
}
