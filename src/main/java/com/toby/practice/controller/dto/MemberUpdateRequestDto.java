package com.toby.practice.controller.dto;

import com.toby.practice.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private String pw;
    private String userEmail;

    public MemberUpdateRequestDto(String pw, String userEmail){
        this.pw = pw;
        this.userEmail = userEmail;
    }

}
