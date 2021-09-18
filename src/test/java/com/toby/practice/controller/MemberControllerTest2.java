package com.toby.practice.controller;

import com.toby.practice.controller.dto.MemberSaveRequestDto;
import com.toby.practice.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.core.Is.is;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//Mockito
@Slf4j
@WebMvcTest(MemberController.class)
public class MemberControllerTest2 {
    @Autowired
    MockMvc mvc;

    @MockBean
    private MemberService memberService;
//Mock -> 실제 객체 말고 모의객체로 만듬
    @Test
    void save() throws Exception{
        MemberSaveRequestDto requestDto = MemberSaveRequestDto.builder()
                .name("gojung")
                .password("1234a")
                .email("wjdrndk96@gmail.com")
                .build();
        //given
        log.info("=====DO SAVE=====");
        given(memberService.findById(1L)).willReturn(requestDto.toEntity());

        //when
        log.info("=====ACTIONS=====");
        ResultActions actions = mvc.perform(get("/api/member/1"))
                .andDo(print());
        //then
        log.info("=====ASSERT=====");
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("gojung")))
                .andDo(print());
    }
}
