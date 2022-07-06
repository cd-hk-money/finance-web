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

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequiredArgsConstructor
public class InterestGroupController {

    private final InterestGroupService interestGroupService;


    @ResponseBody
    @RequestMapping(value = "/members/{memberId}/groups", method = GET)
    public ResponseEntity<LinkedHashSet<InterestGroupDto>> interestGroupList(@PathVariable String memberId) {

        return ResponseEntity.ok().body(interestGroupService.getInterestGroups(memberId));
    }

    @ResponseBody
    @RequestMapping(value = "/members/{memberId}/groups", method = POST)
    public ResponseEntity<InterestGroupDto> interestGroupAdd(@PathVariable String memberId, @RequestBody InterestGroupDto interestGroupDto) {

        return new ResponseEntity<InterestGroupDto>(interestGroupService.addInterestGroup(interestGroupDto), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/members/{memberId}/groups/{groupId}", method = PUT)
    public ResponseEntity<String> interestGroupModify(@PathVariable String memberId, @PathVariable String groupId, @RequestBody InterestGroupUpdateDto updateDto) {

        return interestGroupService.updateInterestGroup(groupId, updateDto)
                ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ResponseBody
    @RequestMapping(value = "/members/{memberId}/groups/{groupId}", method = DELETE)
    public ResponseEntity<String> interestGroupRemove(@PathVariable String memberId, @PathVariable String groupId) {

        return interestGroupService.deleteInterestGroup(groupId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @ResponseBody
    @RequestMapping(value = "/members/{memberId}/groups/{groupId}", method = POST)
    public ResponseEntity<String> interestAddToGroup(@PathVariable String memberId, @PathVariable String groupId, @RequestBody StockItem stockItem) {

        return interestGroupService.addInterest(groupId, stockItem) ?
                ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ResponseBody
    @RequestMapping(value = "/members/{memberId}/groups/{groupId}/interest", method = PUT)
    public ResponseEntity<InterestGroupDto> interestModifySequenceInGroup(@PathVariable String memberId, @PathVariable String groupId, @RequestBody List<StockItem> stockItems) {
        return new ResponseEntity<InterestGroupDto>(interestGroupService.changeInterestsSequenceInGroup(groupId, stockItems ), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/members/{memberId}/groups/{groupId}/interest", method = DELETE)
    public ResponseEntity<String> interestRemove(@PathVariable String memberId, @PathVariable String groupId, @RequestBody StockItem stockItem) {

        return interestGroupService.popInterest(groupId, stockItem) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
