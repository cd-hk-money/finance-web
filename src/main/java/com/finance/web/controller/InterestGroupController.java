package com.finance.web.controller;

import com.finance.web.dto.InterestGroupDto;
import com.finance.web.dto.InterestGroupResponseDto;
import com.finance.web.service.InterestGroupService;
import com.finance.web.service.InterestGroupServiceImpl;
import com.finance.web.service.InterestService;
import com.finance.web.service.InterestServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class InterestGroupController {

    private final InterestGroupService interestGroupService;
    private final InterestService interestService;

    public InterestGroupController(InterestGroupServiceImpl interestGroupService, InterestServiceImpl interestService) {
        this.interestGroupService = interestGroupService;
        this.interestService = interestService;
    }

    @RequestMapping(value = "/member/{id}/group", method = GET)
    public ResponseEntity<Object> getInterestGroup(@RequestParam("id") Long memberId) {
        // InterestGroup을 가져오고
        // InterestGroup에 소속된 Interest를 가져온다면?
        List<InterestGroupResponseDto> interestGroups = interestGroupService.getInterestGroups(memberId);


        return ResponseEntity.ok().body(interestGroups);
    }


    @RequestMapping(value = "/member/{id}/group", method = POST)
    public ResponseEntity<InterestGroupDto> createGroup(@RequestParam("id") Long memberId, @RequestBody InterestGroupDto interestGroupDto, @PathVariable String id) {
        InterestGroupDto responseDto = interestGroupService.create(memberId, interestGroupDto);
        return ResponseEntity.ok().body(responseDto);
    }

}
