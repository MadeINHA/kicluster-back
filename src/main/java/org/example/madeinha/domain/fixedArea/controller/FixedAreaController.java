package org.example.madeinha.domain.fixedArea.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.fixedArea.converter.FixedAreaConverter;
import org.example.madeinha.domain.fixedArea.dto.FixedAreaResponse;
import org.example.madeinha.domain.fixedArea.entity.AreaType;
import org.example.madeinha.domain.fixedArea.entity.FixedArea;
import org.example.madeinha.domain.fixedArea.service.FixedAreaService;
import org.example.madeinha.global.result.ResultResponse;
import org.example.madeinha.global.result.code.FixedAreaResultCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.madeinha.domain.fixedArea.dto.FixedAreaRequest.FixedAreaRegister;
import static org.example.madeinha.domain.fixedArea.dto.FixedAreaResponse.AreaInfoByType;
import static org.example.madeinha.domain.fixedArea.dto.FixedAreaResponse.RegisterList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fixed-area")
public class FixedAreaController {

    private final FixedAreaService fixedAreaService;
    private final FixedAreaConverter fixedAreaConverter;

    @PostMapping("/register/exist")
    @Operation(summary = "기존 주차구역을 등록하는 api")
    public ResultResponse<RegisterList> registerExist(@RequestBody FixedAreaRegister request) {
        List<FixedArea> fixedAreaList = fixedAreaService.registerArea(request, AreaType.EXIST);
        RegisterList registerList = fixedAreaConverter.toRegisterList(fixedAreaList);

        return ResultResponse.of(FixedAreaResultCode.EXIST_FIXED_AREA_REGISTER, registerList);
    }

    @PostMapping("/register/road")
    @Operation(summary = "도로 정보를 등록하는 api")
    public ResultResponse<RegisterList> registerRoad(@RequestBody FixedAreaRegister request) {
        List<FixedArea> fixedAreaList = fixedAreaService.registerArea(request, AreaType.ROAD);
        RegisterList registerList = fixedAreaConverter.toRegisterList(fixedAreaList);

        return ResultResponse.of(FixedAreaResultCode.ROAD_FIXED_AREA_REGISTER, registerList);
    }

    @PostMapping("/register/prohibit")
    @Operation(summary = "기존 주차 금지 구역을 등록하는 api")
    public ResultResponse<RegisterList> registerProhibit(@RequestBody FixedAreaRegister request) {
        List<FixedArea> fixedAreaList = fixedAreaService.registerArea(request, AreaType.PROHIBIT);
        RegisterList registerList = fixedAreaConverter.toRegisterList(fixedAreaList);

        return ResultResponse.of(FixedAreaResultCode.PROHIBIT_FIXED_AREA_REGISTER, registerList);
    }

    @GetMapping("/exist")
    @Operation(summary = "기존 주차구역을 조회하는 api")
    public ResultResponse<AreaInfoByType> getExistAreaInfoByType() {
        List<FixedArea> fixedAreaByType = fixedAreaService.getFixedAreaByType(AreaType.EXIST);
        AreaInfoByType areaInfoByType = fixedAreaConverter.toAreaInfoByType(fixedAreaByType, AreaType.EXIST);

        return ResultResponse.of(FixedAreaResultCode.EXIST_FIXED_AREA_INFO, areaInfoByType);
    }

    @GetMapping("/prohibit")
    @Operation(summary = "기존 주차 금지구역을 조회하는 api")
    public ResultResponse<AreaInfoByType> getProhibitAreaInfoByType() {
        List<FixedArea> fixedAreaByType = fixedAreaService.getFixedAreaByType(AreaType.PROHIBIT);
        AreaInfoByType areaInfoByType = fixedAreaConverter.toAreaInfoByType(fixedAreaByType, AreaType.PROHIBIT);

        return ResultResponse.of(FixedAreaResultCode.PROHIBIT_FIXED_AREA_INFO, areaInfoByType);
    }

    @GetMapping("/nearest")
    @Operation(summary = "가장 가까운 기존 주차구역을 조회하는 api")
    public ResultResponse<FixedAreaResponse.AreaInfo> getNearestExistArea(@RequestParam("lat") String s_lat, @RequestParam("lng") String s_lng) {
        Double lat = Double.parseDouble(s_lat);
        Double lng = Double.parseDouble(s_lng);
        FixedArea nearestExistArea = fixedAreaService.getNearestExistArea(lat, lng);
        FixedAreaResponse.AreaInfo areaInfo = fixedAreaConverter.toAreaInfo(nearestExistArea);

        return ResultResponse.of(FixedAreaResultCode.NEAREST_EXIST_FIXED_AREA_INFO, areaInfo);
    }
}
