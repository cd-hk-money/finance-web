package com.finance.web.controller;

import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupUpdateDto;
import com.finance.web.service.InterestGroupService;
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
    @RequestMapping(value = "/member/{userId}/group", method = GET)
    public ResponseEntity<LinkedHashSet<InterestGroupDto>> interestGroupList(@PathVariable String userId) {

        return ResponseEntity.ok().body(interestGroupService.getInterestGroups(userId));
    }

    @ResponseBody
    @RequestMapping(value = "/member/{userId}/group", method = POST)
    public ResponseEntity<InterestGroupDto> interestGroupAdd(@PathVariable String userId, @RequestBody InterestGroupDto interestGroupDto) {

        return new ResponseEntity<InterestGroupDto>(interestGroupService.addInterestGroup(interestGroupDto), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/member/{userId}/group/{groupId}", method = PUT)
    public ResponseEntity<Void> interestGroupModify(@PathVariable String userId, @PathVariable String groupId, @RequestBody InterestGroupUpdateDto updateDto) {

        return interestGroupService.updateInterestGroup(groupId, updateDto)
                ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ResponseBody
    @RequestMapping(value = "/member/{userId}/group/{groupId}", method = DELETE)
    public ResponseEntity<Void> interestGroupRemove(@PathVariable String userId, @PathVariable String groupId) {

        return interestGroupService.deleteInterestGroup(groupId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
