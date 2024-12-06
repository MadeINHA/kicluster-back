package org.example.madeinha.domain.notification.controller;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.notification.converter.FcmConverter;
import org.example.madeinha.domain.notification.dto.request.FcmRequest.FcmRequestDTO;
import org.example.madeinha.domain.notification.service.FcmService;
import org.example.madeinha.global.result.ResultResponse;
import org.example.madeinha.global.result.code.FcmResultCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.example.madeinha.domain.notification.dto.response.FcmResponse.FcmSend;

@RestController
@RequestMapping("/fcm")
@RequiredArgsConstructor
public class
FcmController {

    private final FcmService fcmService;
    private final FcmConverter fcmConverter;

    @PostMapping("/send")
    public ResultResponse<FcmSend> sendFcmAlarm(@RequestBody FcmRequestDTO request) throws IOException {
        fcmService.sendMessageTo(
                request.getTargetToken(),
                request.getTitle(),
                request.getBody()
        );
        return ResultResponse.of(FcmResultCode.MESSAGE_SEND,
                fcmConverter.toFcmSend(request.getTargetToken(), request.getTitle(), request.getBody()));
    }
}
