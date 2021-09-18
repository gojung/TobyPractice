package com.toby.practice.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    /*success*/
    @Test
    public void getName(){
        Member member = Member.builder()
                .name("gooah")
                .password("gooah!!")
                .email("wjdrndk6@gmail.com")
                .build();
        String name = member.getName();
        assertEquals("gooah", name);
    }

    /*fail*/
    @Test
    public void getId_failed(){
        Member member = Member.builder()
                .name("gooah")
                .password("gooah!!")
                .email("wjdrndk6@gmail.com")
                .build();
        String name = member.getName();
        assertEquals("hyunah", name);
    }

}
