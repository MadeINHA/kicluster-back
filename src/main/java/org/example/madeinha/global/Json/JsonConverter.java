package org.example.madeinha.global.Json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.kickboard.dto.response.KickboardResponse;
import org.example.madeinha.global.gRPC.dto.GrpcDTO;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonConverter {

    private final ObjectMapper objectMapper;

    public JsonDTO.JsonAllClusterInfo parseJson(String jsonString) throws JsonProcessingException {

        return objectMapper.readValue(jsonString, JsonDTO.JsonAllClusterInfo.class);
    }

    public String makeDbScanRequest(KickboardResponse.AllKickboardInfo allKickboardInfo) throws JsonProcessingException {
        return objectMapper.writeValueAsString(allKickboardInfo);
    }

    public GrpcDTO.dbScanResponse updateKickboardInfo(String json) throws JsonProcessingException {
        return objectMapper.readValue(
                json,
                new TypeReference<GrpcDTO.dbScanResponse>() {}
        );
    }
}
