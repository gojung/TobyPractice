package com.toby.practice.controller;

import com.toby.practice.controller.dto.MemberSaveRequestDto;
import com.toby.practice.domain.Member;
import com.toby.practice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/api/member/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Member getMember(@PathVariable("id") Long id) {
        return memberService.findById(id);
    }

    @PostMapping("/api/member/save")
    public Member save(MemberSaveRequestDto requestDto){
        return memberService.save(requestDto);
    }

}
