package com.finance.web.controller;

import com.finance.web.dto.MemberDto;
import com.finance.web.dto.SignUpRequestDto;
import com.finance.web.service.MemberService;
import com.finance.web.vo.StockItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/members/signUp")
    public ResponseEntity<MemberDto> signUp(@RequestBody SignUpRequestDto requestDto) {
        return new ResponseEntity<MemberDto>(memberService.signUpMember(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/members/email/{email}")
    public ResponseEntity checkAvailableEmail(@PathVariable("email") String email) {
        return memberService.isAvailableEmail(email) ? new ResponseEntity(HttpStatus.NO_CONTENT) : new ResponseEntity(HttpStatus.CONFLICT);
    }

    @GetMapping("/members/username/{username}")
    public ResponseEntity checkAvailableUsername(@PathVariable("username") String username) {
        return memberService.isAvailableUsername(username) ? new ResponseEntity(HttpStatus.NO_CONTENT) : new ResponseEntity(HttpStatus.CONFLICT);
    }

    @GetMapping("/members/{memberId}/notifications")
    public ResponseEntity<Object> notificationItemList(@PathVariable String memberId) {
        return new ResponseEntity(memberService.getItemListFromNotifications(memberId), HttpStatus.OK);
    }

    @PostMapping("/members/{memberId}/notifications")
    public ResponseEntity<Object> notificationItemAdd(@PathVariable String memberId, @RequestBody StockItem stockItem) {
        return new ResponseEntity(memberService.addItemToNotifications(memberId, stockItem), HttpStatus.CREATED);
    }

    @DeleteMapping("/members/{memberId}/notifications")
    public ResponseEntity<Object> notificationItemRemove(@PathVariable String memberId, @RequestBody StockItem stockItem) {
        return new ResponseEntity(memberService.deleteItemInNotifications(memberId, stockItem), HttpStatus.NO_CONTENT);
    }

}
