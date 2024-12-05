package org.example.madeinha.domain.fixedArea.controller;

import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.fixedArea.converter.FixedAreaConverter;
import org.example.madeinha.domain.fixedArea.entity.AreaType;
import org.example.madeinha.domain.fixedArea.entity.FixedArea;
import org.example.madeinha.domain.fixedArea.service.FixedAreaService;
import org.example.madeinha.global.result.ResultResponse;
import org.example.madeinha.global.result.code.FixedAreaResultCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.madeinha.domain.fixedArea.dto.FixedAreaRequest.*;
import static org.example.madeinha.domain.fixedArea.dto.FixedAreaResponse.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fixed-area")
public class FixedAreaController {

    private final FixedAreaService fixedAreaService;
    private final FixedAreaConverter fixedAreaConverter;

    @PostMapping("/register/exist")
    public ResultResponse<RegisterList> registerExist(@RequestBody FixedAreaRegister request) {
        List<FixedArea> fixedAreaList = fixedAreaService.registerArea(request, AreaType.EXIST);
        RegisterList registerList = fixedAreaConverter.toRegisterList(fixedAreaList);

        return ResultResponse.of(FixedAreaResultCode.EXIST_FIXED_AREA_REGISTER, registerList);
    }

    @PostMapping("/register/road")
    public ResultResponse<RegisterList> registerRoad(@RequestBody FixedAreaRegister request) {
        List<FixedArea> fixedAreaList = fixedAreaService.registerArea(request, AreaType.ROAD);
        RegisterList registerList = fixedAreaConverter.toRegisterList(fixedAreaList);

        return ResultResponse.of(FixedAreaResultCode.ROAD_FIXED_AREA_REGISTER, registerList);
    }

    @PostMapping("/register/prohibit")
    public ResultResponse<RegisterList> registerProhibit(@RequestBody FixedAreaRegister request) {
        List<FixedArea> fixedAreaList = fixedAreaService.registerArea(request, AreaType.PROHIBIT);
        RegisterList registerList = fixedAreaConverter.toRegisterList(fixedAreaList);

        return ResultResponse.of(FixedAreaResultCode.PROHIBIT_FIXED_AREA_REGISTER, registerList);
    }

    @GetMapping("/exist")
    public ResultResponse<AreaInfoByType> getExistAreaInfoByType() {
        List<FixedArea> fixedAreaByType = fixedAreaService.getFixedAreaByType(AreaType.EXIST);
        AreaInfoByType areaInfoByType = fixedAreaConverter.toAreaInfoByType(fixedAreaByType, AreaType.EXIST);

        return ResultResponse.of(FixedAreaResultCode.EXIST_FIXED_AREA_INFO, areaInfoByType);
    }

    @GetMapping("/prohibit")
    public ResultResponse<AreaInfoByType> getProhibitAreaInfoByType() {
        List<FixedArea> fixedAreaByType = fixedAreaService.getFixedAreaByType(AreaType.PROHIBIT);
        AreaInfoByType areaInfoByType = fixedAreaConverter.toAreaInfoByType(fixedAreaByType, AreaType.PROHIBIT);

        return ResultResponse.of(FixedAreaResultCode.PROHIBIT_FIXED_AREA_INFO, areaInfoByType);
    }
}
