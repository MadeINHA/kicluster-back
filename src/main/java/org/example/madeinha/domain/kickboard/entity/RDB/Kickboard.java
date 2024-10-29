package org.example.madeinha.domain.kickboard.entity.RDB;

import jakarta.persistence.*;
import lombok.*;
import org.example.madeinha.global.entity.BaseTimeEntity;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "kickboards")
@SQLRestriction("deleted_at is NULL")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Kickboard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kickboard_id")
    private Long kickboardId;
}
