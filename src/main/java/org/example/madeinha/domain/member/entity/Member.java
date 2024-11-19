package org.example.madeinha.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.madeinha.domain.notification.entity.FCM;
import org.example.madeinha.global.entity.BaseTimeEntity;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@SQLRestriction("deleted_at is NULL")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    private Long point;

    @Column(length = 15)
    private String nickname;


    @OneToMany(mappedBy = "member")
    private List<FCM> fcmList = new ArrayList<>();
}
