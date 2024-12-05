package org.example.madeinha.domain.fixedArea.entity;


import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

@Getter
@RedisHash(value = "area")
public class RedisFixedArea implements Serializable {

    @Id
    @Indexed
    private Long areaId;

    @Indexed
    private String areaName;

    @Indexed
    private AreaType areaType;

    List<CoordinateDTO> coordinateList;

    public RedisFixedArea(Long areaId, String areaName, AreaType areaType, List<CoordinateDTO> coordinateList) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.areaType = areaType;
        this.coordinateList = coordinateList;
    }
}
