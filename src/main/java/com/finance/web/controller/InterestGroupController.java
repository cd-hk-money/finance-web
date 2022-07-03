package com.finance.web.controller;

import com.finance.web.vo.StockItem;
import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupUpdateDto;
import com.finance.web.service.InterestGroupService;
import com.finance.web.vo.StockItems;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequiredArgsConstructor
public class InterestGroupController {

    private final InterestGroupService interestGroupService;


    @ResponseBody
    @RequestMapping(value = "/members/{userId}/groups", method = GET)
    public ResponseEntity<LinkedHashSet<InterestGroupDto>> interestGroupList(@PathVariable String userId) {

        return ResponseEntity.ok().body(interestGroupService.getInterestGroups(userId));
    }

    @ResponseBody
    @RequestMapping(value = "/members/{userId}/groups", method = POST)
    public ResponseEntity<InterestGroupDto> interestGroupAdd(@PathVariable String userId, @RequestBody InterestGroupDto interestGroupDto) {

        return new ResponseEntity<InterestGroupDto>(interestGroupService.addInterestGroup(interestGroupDto), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/members/{userId}/groups/{groupId}", method = PUT)
    public ResponseEntity<String> interestGroupModify(@PathVariable String userId, @PathVariable String groupId, @RequestBody InterestGroupUpdateDto updateDto) {

        return interestGroupService.updateInterestGroup(groupId, updateDto)
                ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ResponseBody
    @RequestMapping(value = "/members/{userId}/groups/{groupId}", method = DELETE)
    public ResponseEntity<String> interestGroupRemove(@PathVariable String userId, @PathVariable String groupId) {

        return interestGroupService.deleteInterestGroup(groupId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @ResponseBody
    @RequestMapping(value = "/members/{userId}/groups/{groupId}/interest", method = POST)
    public ResponseEntity<String> interestAddToGroup(@PathVariable String userId, @PathVariable String groupId, @RequestBody StockItem stockItem) {

        return interestGroupService.addInterest(groupId, stockItem) ?
                ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ResponseBody
    @RequestMapping(value = "/members/{userId}/groups/{groupId}/interest", method = PUT)
    public ResponseEntity<InterestGroupDto> interestModifySequenceInGroup(@PathVariable String userId, @PathVariable String groupId, @RequestBody StockItems stockItems) {
        return new ResponseEntity<InterestGroupDto>(interestGroupService.changeInterestsSequenceInGroup(groupId, stockItems.getStockItems()), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/members/{userId}/groups/{groupId}/interest", method = DELETE)
    public ResponseEntity<String> interestRemove(@PathVariable String userId, @PathVariable String groupId, @RequestBody StockItem stockItem) {

        return interestGroupService.popInterest(groupId, stockItem) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
