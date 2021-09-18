package com.toby.practice.controller;

import com.toby.practice.controller.dto.MemberSaveRequestDto;
import com.toby.practice.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.transaction.Transactional;
import static org.hamcrest.core.Is.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(
        properties = {
                "testId=gojung",
                "testName=junggooah"
        }
        ,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class MemberControllerTest {
        @Value("${testId}")
        private String testId;

        @Value("${testName}")
        private String testName;

        //이 친구 가져오기 위해 @AutoConfigureMockMvc 해준다
        @Autowired
        MockMvc mvc;

        @Autowired
        private TestRestTemplate restTemplate;

        @Autowired
        private MemberService memberService;

        @Autowired
        private WebApplicationContext context;

        @BeforeEach
        public void setup(){
                this.mvc = MockMvcBuilders.webAppContextSetup(context)
                        .alwaysDo(print())
                        .build();
        }

        @Test
        void saveAndFindById() throws Exception{
                //log.info("=====MemberController.save()=====");
                MemberSaveRequestDto requestDto = MemberSaveRequestDto.builder()
                        .name("gojung")
                        .password("1234a")
                        .email("wjdrndk96@gmail.com")
                        .build();
                memberService.save(requestDto);
//
                mvc.perform(get("/api/member/1"))
                        .andExpect(status().isOk())//200
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.name", is("gojung")))
                        .andDo(print());

        }

}
