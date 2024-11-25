package org.example.madeinha.domain.DesignatedParkingArea.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.DesignatedParkingArea.converter.DesignatedParkingAreaConverter;
import org.example.madeinha.domain.DesignatedParkingArea.entity.DesignatedParkingArea;
import org.example.madeinha.domain.DesignatedParkingArea.service.DesignatedParkingAreaService;
import org.example.madeinha.global.result.ResultResponse;
import org.example.madeinha.global.result.code.DesignatedParkingAreaResultCode;
import org.springframework.web.bind.annotation.*;

import static org.example.madeinha.domain.DesignatedParkingArea.dto.request.DesignatedParkingAreaRequest.*;
import static org.example.madeinha.domain.DesignatedParkingArea.dto.response.DesignatedParkingAreaResponse.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking-area")
public class DesignatedParkingAreaController {

    private final DesignatedParkingAreaService designatedParkingAreaService;
    private final DesignatedParkingAreaConverter designatedParkingAreaConverter;

    @PostMapping("/register")
    public ResultResponse<RegisterInfo> register(@RequestBody @Valid Register request) {
        DesignatedParkingArea designatedParkingArea = designatedParkingAreaService.registerArea(request);
        RegisterInfo registerInfo = designatedParkingAreaConverter.toRegisterInfo(designatedParkingArea);

        return ResultResponse.of(DesignatedParkingAreaResultCode.PARKING_ZONE_REGISTER, registerInfo);
    }

    @GetMapping("/info/{parkingZoneId}")
    public ResultResponse<ParkingZoneInfo> getParkingkingZoneInfo(@PathVariable("parkingZoneId") Long parkingZoneId) {
        DesignatedParkingArea parkingZone = designatedParkingAreaService.getParkingZoneById(parkingZoneId);
        ParkingZoneInfo parkingZoneInfo = designatedParkingAreaConverter.toParkingZoneInfo(parkingZone);

        return ResultResponse.of(DesignatedParkingAreaResultCode.PARKING_ZONE_INFO_BY_ID, parkingZoneInfo);
    }
}
