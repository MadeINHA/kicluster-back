package org.example.madeinha.domain.history.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.madeinha.global.entity.BaseTimeEntity;
import org.hibernate.annotations.SQLRestriction;
import org.locationtech.jts.geom.Polygon;

@Entity
@Table(name = "history")
@SQLRestriction("deleted_at is NULL")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class History extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @Column(name = "kickboard_id")
    private Long kickboardId;

    @Column(name = "zone", columnDefinition = "POLYGON", nullable = false)
    private Polygon zone;

}
