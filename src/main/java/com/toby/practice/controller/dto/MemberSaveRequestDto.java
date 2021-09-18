package com.toby.practice.controller.dto;

import com.toby.practice.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private String name;
    private String email;
    private String password;

    @Builder
    public MemberSaveRequestDto(String name, String email, String password){
        this.name = name;
        this.email= email;
        this.password = password;
    }

    public Member toEntity(){
        return Member.builder().name(name).email(email).password(password).build();
    }

}
