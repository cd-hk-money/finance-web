package com.finance.web.controller;

import com.finance.web.vo.StockItem;
import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupUpdateDto;
import com.finance.web.service.InterestGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InterestGroupController {

    private final InterestGroupService interestGroupService;

    @ResponseBody
    @GetMapping("/members/{memberId}/groups")
    public ResponseEntity<LinkedHashSet<InterestGroupDto>> interestGroupList(@PathVariable String memberId) {

        return ResponseEntity.ok().body(interestGroupService.getInterestGroups(memberId));
    }

    @ResponseBody
    @PostMapping("/members/{memberId}/groups")
    public ResponseEntity<InterestGroupDto> interestGroupAdd(@PathVariable String memberId, @RequestBody InterestGroupDto interestGroupDto) {

        return new ResponseEntity<InterestGroupDto>(interestGroupService.addInterestGroup(interestGroupDto), HttpStatus.CREATED);
    }

    @ResponseBody
    @PutMapping("/members/{memberId}/groups/{groupId}")
    public ResponseEntity<String> interestGroupModify(@PathVariable String memberId, @PathVariable String groupId, @RequestBody InterestGroupUpdateDto updateDto) {

        return interestGroupService.updateInterestGroup(groupId, updateDto)
                ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ResponseBody
    @DeleteMapping("/members/{memberId}/groups/{groupId}")
    public ResponseEntity<String> interestGroupRemove(@PathVariable String memberId, @PathVariable String groupId) {

        return interestGroupService.deleteInterestGroup(groupId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @ResponseBody
    @PostMapping("/members/{memberId}/groups/{groupId}")
    public ResponseEntity<String> interestAddToGroup(@PathVariable String memberId, @PathVariable String groupId, @RequestBody StockItem stockItem) {

        return interestGroupService.addInterest(groupId, stockItem) ?
                ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ResponseBody
    @PostMapping("/members/{memberId}/groups/{groupId}/interests")
    public ResponseEntity<InterestGroupDto> interestModifySequenceInGroup(@PathVariable String memberId, @PathVariable String groupId, @RequestBody List<StockItem> stockItems) {
        return new ResponseEntity<InterestGroupDto>(interestGroupService.updateInterestsInGroups(groupId, stockItems), HttpStatus.CREATED);
    }

    @ResponseBody
    @DeleteMapping("/members/{memberId}/groups/{groupId}/interests")
    public ResponseEntity<String> interestRemove(@PathVariable String memberId, @PathVariable String groupId, @RequestBody StockItem stockItem) {

        return interestGroupService.popInterest(groupId, stockItem) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
