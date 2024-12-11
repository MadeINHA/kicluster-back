package org.example.madeinha.domain.kickboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.madeinha.domain.cluster.service.ClusterService;
import org.example.madeinha.domain.history.dto.HistoryRequest;
import org.example.madeinha.domain.kickboard.converter.KickboardConverter;
import org.example.madeinha.domain.kickboard.dto.request.KickboardRequest;
import org.example.madeinha.domain.kickboard.dto.response.KickboardResponse;
import org.example.madeinha.domain.kickboard.entity.RDB.Kickboard;
import org.example.madeinha.domain.kickboard.entity.Redis.RedisKickboard;
import org.example.madeinha.domain.kickboard.service.KickboardService;
import org.example.madeinha.domain.kickboard.service.RedisKickboardService;
import org.example.madeinha.global.result.ResultResponse;
import org.example.madeinha.global.result.code.KickBoardResultCode;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.madeinha.domain.kickboard.dto.request.KickboardRequest.RegisterRequest;
import static org.example.madeinha.domain.kickboard.dto.response.KickboardResponse.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kickboards")
public class KickBoardController {

    private final KickboardService kickboardService;
    private final KickboardConverter kickboardConverter;
    private final RedisKickboardService redisKickboardService;
    private final ClusterService clusterService;

    @PostMapping("/register")
    @Operation(summary = "RDB에 새로운 킥보드를 등록하는 api", description = "클라이언트 단에서 호출 X")
    public ResultResponse<List<KickboardInfo>> register(@RequestBody RegisterRequest request) {

        List<Kickboard> register = kickboardService.register(request);
        List<KickboardInfo> list = register.stream().map(
                kickboardConverter::toKickboardInfo
        ).toList();

        return ResultResponse.of(KickBoardResultCode.KICKBOARD_REGISTER, list);
    }

    @GetMapping("/RDBtoREDIS")
    @Operation(summary = "RDB에 있는 킥보드 정보를 Redis로 이동시키는 api", description = "클라이언트 단에서 호출 X")
    public ResultResponse<Integer> rdbToRedis() {

        redisKickboardService.register();
        return ResultResponse.of(KickBoardResultCode.KICKBOARD_REGISTER, 1);
    }

    @PatchMapping("/tow/lent")
    @Operation(summary = "견인모드로 킥보드를 빌리는 api", description = "1) path는 ***반드시*** 리스트에서 첫 위경도와 끝 위경도 값이 같아야함!!!!!!!!!!!!!!!!\n" +
            "2) acting을 바꾸기만 하는데 만약 acting이 true이면 알고리즘에서도 빠지고 지도에서도 보여주면 안됨 ")
    public ResultResponse<TowModeLentInfo> towModeLent(@RequestBody HistoryRequest.TowLent request) {
        RedisKickboard kickboard = redisKickboardService.towModeLent(request);

        TowModeLentInfo towModeLentInfo = kickboardConverter.toTowModeLentInfo(kickboard);

        return ResultResponse.of(KickBoardResultCode.LENT_KICKBOARD_TO_TOW_MODE, towModeLentInfo);
    }

    @PatchMapping("/tow/return")
    @Operation(summary = "견인모드에서 킥보드를 반납하는 api", description = "check가 true이면 정상반납, false이면 다른 장소에 반납")
    public ResultResponse<TowModeReturnInfo> towModeReturn(@RequestBody KickboardRequest.ReturnRequest request) throws JsonProcessingException {

        TowModeReturnInfo towModeReturnInfo = redisKickboardService.towModeReturn(request);
        clusterService.updateKickboardInfo(); // 반납후 상태를 가지고 다시 알고리즘 수행해서 db에 반영

        return ResultResponse.of(KickBoardResultCode.RETURN_KICKBOARD, towModeReturnInfo);

    }

    @GetMapping("/info/{kickboardId}")
    @Operation(summary = "특정 킥보드의 정보를 조회하는 api", description = "특정 킥보드의 모든 정보를 반환해주는 api이며 PathVariable로 조회하고 싶은 킥보드 id를 넣으면 됨")
    public ResultResponse<KickboardDetailInfo> getDetailInfo(@PathVariable("kickboardId") Long kickboardId) {

        RedisKickboard kickboard = redisKickboardService.findKickboardById(kickboardId);
        KickboardDetailInfo kickboardDetailInfo = kickboardConverter.toDbScanInput(kickboard);

        return ResultResponse.of(KickBoardResultCode.KICKBOARD_DETAIL_INFO, kickboardDetailInfo);
    }


    @GetMapping("/location/{kickboardId}")
    @Operation(summary = "특정 킥보드의 위치정보를 조회하는 api", description = "특정 킥보드의 위치 정보를 반환해주는 api이며 PathVariable로 조회하고 싶은 킥보드 id를 넣으면 됨")
    public ResultResponse<LocationInfo> getLocationInfo(@PathVariable("kickboardId") Long kickboardId) {
        Coordinate coordinate = redisKickboardService.getLoaction(kickboardId);
        LocationInfo locationInfo = kickboardConverter.toLocationInfo(coordinate);

        return ResultResponse.of(KickBoardResultCode.KICKBOARD_LOCATION_INFO, locationInfo);
    }

    @GetMapping("/{clusterId}")
    @Operation(summary = "특정 클러스터에 속한 모든 킥보드를 조회하는 api", description = "특정 클러스터에 속하는 모든 킥보드 정보를 조회하며 PathVariable로 클러스터id를 넣으면 됨")
    public ResultResponse<ClusterInfo> getClusterInfo(@PathVariable("clusterId") Integer clusterId) {
        List<RedisKickboard> kickboardList = redisKickboardService.getKickboardInfoByClusterId(clusterId);
        ClusterInfo redisClusterInfo = kickboardConverter.toRedisClusterInfo(kickboardList, clusterId);

        return ResultResponse.of(KickBoardResultCode.KICKBOARD_LIST_BY_CLUSTER_ID, redisClusterInfo);
    }

    @GetMapping("/location/all")
    @Operation(summary = "모든 킥보드의 위치정보를 조회하는 api")
    public ResultResponse<AllKickboardLocationInfo> getAllKickboardLocationInfo() {
        List<RedisKickboard> kickboardList = redisKickboardService.getAllKickboardInfo();
        AllKickboardLocationInfo allkickboardLocationInfo = kickboardConverter.toAllkickboardLocationInfo(kickboardList);

        return ResultResponse.of(KickBoardResultCode.ALL_KICKBOARD_LOCATION_INFO, allkickboardLocationInfo);
    }

    @GetMapping("/all")
    @Operation(summary = "모든 킥보드의 상세 정보를 조회하는 api")
    public ResultResponse<AllKickboardInfo> getAllKickboardInfo() {
        List<RedisKickboard> kickboardList = redisKickboardService.getAllKickboardInfo();
        AllKickboardInfo allKickboardInfo = kickboardConverter.toAllKickboardInfoUseList(kickboardList);

        return ResultResponse.of(KickBoardResultCode.ALL_KCIKBOARD_INFO, allKickboardInfo);
    }

    @PatchMapping("/move")
    @Operation(summary = "킥보드가 이동하는 것을 처리하는 api")
    public ResultResponse<KickboardResponse.MoveInfo> moveKickboard(@RequestBody KickboardRequest.ReturnRequest request) {
        RedisKickboard kickboard = redisKickboardService.moveKickboard(request);
        MoveInfo moveInfo = kickboardConverter.toMoveInfo(kickboard);

        return ResultResponse.of(KickBoardResultCode.MOVE_SUCCESS, moveInfo);
    }
}
