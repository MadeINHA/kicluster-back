package org.example.madeinha.domain.fixedArea.converter;

import org.example.madeinha.domain.fixedArea.entity.AreaType;
import org.example.madeinha.domain.fixedArea.entity.CoordinateDTO;
import org.example.madeinha.domain.fixedArea.entity.FixedArea;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.example.madeinha.domain.fixedArea.dto.FixedAreaResponse.*;

@Component
public class FixedAreaConverter {

    public FixedArea toEntity(String name, Polygon polygon, AreaType areaType) {
        return FixedArea.builder()
                .areaName(name)
                .zone(polygon)
                .areaType(areaType)
                .build();
    }

    public RegisterInfo toRegisterInfo(FixedArea fixedArea) {
        return RegisterInfo.builder()
                .areaId(fixedArea.getAreaId())
                .name(fixedArea.getAreaName())
                .areaType(fixedArea.getAreaType())
                .build();
    }

    public RegisterList toRegisterList(List<FixedArea> fixedAreaList) {
        List<RegisterInfo> list = fixedAreaList.stream().map(
                this::toRegisterInfo
        ).toList();

        return RegisterList.builder()
                .registerInfoList(list)
                .build();
    }

    public AreaInfo toAreaInfo(FixedArea fixedArea) {
        Polygon zone = fixedArea.getZone();
        List<CoordinateDTO> list = Arrays.stream(zone.getCoordinates()).map(
                coordinate -> new CoordinateDTO(coordinate.getY(), coordinate.getX())
        ).toList();

        return AreaInfo.builder()
                .name(fixedArea.getAreaName())
                .path(list)
                .build();
    }

    public AreaInfoByType toAreaInfoByType(List<FixedArea> fixedAreaList, AreaType areaType) {
        List<AreaInfo> list = fixedAreaList.stream().map(
                this::toAreaInfo
        ).toList();

        return AreaInfoByType.builder()
                .areaType(areaType)
                .areaInfoList(list)
                .build();
    }


}
