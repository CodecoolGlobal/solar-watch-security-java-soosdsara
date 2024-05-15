package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.newMemberDTO;
import com.codecool.solarwatch.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody newMemberDTO request) {
        memberService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
