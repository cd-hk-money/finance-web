package com.finance.web.controller;

import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupUpdateDto;
import com.finance.web.dto.Response;
import com.finance.web.service.InterestGroupService;
import com.finance.web.vo.StockItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InterestGroupController {

    private final InterestGroupService interestGroupService;
    private final Response response;

    @GetMapping("/members/{memberId}/groups")
    public ResponseEntity<? extends Object> interestGroupList(@PathVariable String memberId) {
        return response.success(interestGroupService.getInterestGroups(memberId), HttpStatus.OK);
    }

    @PostMapping("/members/{memberId}/groups")
    public ResponseEntity<? extends Object> interestGroupAdd(@PathVariable String memberId, @RequestBody InterestGroupDto interestGroupDto) {
        return response.success(interestGroupService.addInterestGroup(interestGroupDto), HttpStatus.CREATED);
    }

    @PutMapping("/members/{memberId}/groups/{groupId}")
    public ResponseEntity<? extends Object> interestGroupModify(@PathVariable String memberId, @PathVariable String groupId, @RequestBody InterestGroupUpdateDto updateDto) {

        return interestGroupService.updateInterestGroup(groupId, updateDto)
                ? response.success(HttpStatus.CREATED) : response.fail("update failed", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/members/{memberId}/groups/{groupId}")
    public ResponseEntity<? extends Object> interestGroupRemove(@PathVariable String memberId, @PathVariable String groupId) {
        return interestGroupService.deleteInterestGroup(groupId) ? response.success(HttpStatus.NO_CONTENT) :
                response.fail("content was not founded", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/members/{memberId}/groups/{groupId}")
    public ResponseEntity<? extends Object> interestAddToGroup(@PathVariable String memberId, @PathVariable String groupId, @RequestBody StockItem stockItem) {
        return interestGroupService.addInterest(groupId, stockItem) ? response.success(HttpStatus.CREATED) :
                response.fail("access denied", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/members/{memberId}/groups/{groupId}/interests")
    public ResponseEntity<? extends Object> interestModifySequenceInGroup(@PathVariable String memberId, @PathVariable String groupId, @RequestBody List<StockItem> stockItems) {
        return response.success(interestGroupService.updateInterestsInGroups(groupId, stockItems), HttpStatus.CREATED);
    }

    @DeleteMapping("/members/{memberId}/groups/{groupId}/interests")
    public ResponseEntity<? extends Object> interestRemove(@PathVariable String memberId, @PathVariable String groupId, @RequestBody StockItem stockItem) {

        return interestGroupService.popInterest(groupId, stockItem) ?
                response.success(HttpStatus.NO_CONTENT) : response.fail("access denied", HttpStatus.FORBIDDEN);
    }

}
