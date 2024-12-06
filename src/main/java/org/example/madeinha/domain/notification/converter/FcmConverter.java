package org.example.madeinha.domain.notification.converter;


import org.example.madeinha.domain.member.entity.Member;
import org.example.madeinha.domain.notification.entity.FCM;
import org.springframework.stereotype.Component;

import static org.example.madeinha.domain.notification.dto.response.FcmResponse.FcmSend;

@Component
public class FcmConverter {

    public FCM toEntity(Member member, String token) {
        return FCM.builder()
                .member(member)
                .fcmToken(token)
                .build();
    }

    public FcmSend toFcmSend(String token, String title, String body) {
        return FcmSend.builder()
                .targetToken(token)
                .title(title)
                .body(body)
                .build();
    }

}
