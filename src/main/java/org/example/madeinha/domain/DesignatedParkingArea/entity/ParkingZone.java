package org.example.madeinha.domain.DesignatedParkingArea.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

@Getter
@RedisHash(value = "location")
public class ParkingZone implements Serializable {

    @Id
    @Indexed
    private Long parkingZoneId;

    @Indexed
    private String parkingZoneName;

    List<CoordinateDTO> coordinates; //다각형을 이루는 위경도 리스트

    public ParkingZone(Long parkingZoneId, String parkingZoneName, List<CoordinateDTO> coordinateList) {
        this.parkingZoneId = parkingZoneId;
        this.parkingZoneName = parkingZoneName;
        this.coordinates = coordinateList;
    }
}
