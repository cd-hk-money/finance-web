package com.finance.web.controller;

import com.finance.web.dto.MemberDto;
import com.finance.web.dto.SignUpRequestDto;
import com.finance.web.service.MemberService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @InjectMocks
    MemberController memberController;

    @Mock
    MemberService memberService;

    MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    @DisplayName("회원 가입 성공")
    void signUpSuccess() throws Exception {
        //given
        SignUpRequestDto request = signUpRequest();
        MemberDto response = memberResponse();

        doReturn(response).when(memberService)
                .signUpMember(any(SignUpRequestDto.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/members/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        //then
        MvcResult mvcResult = resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("email", response.getEmail()).exists())
                .andExpect(jsonPath("username", response.getUsername()).exists())
                .andExpect(jsonPath("nickname", response.getNickname()).exists()).andReturn();


        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println("contentAsString = " + contentAsString);

    }


    @Test
    @DisplayName("이메일 중복 확인")
    void emailAvailableCheck() throws Exception {
        //given
        String request = "test@test.com";
        MemberDto response = memberResponse();

        doReturn(true).when(memberService)
                .isAvailableEmail(any(String.class));
        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/members/email/{email}", "test@test.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );
        MvcResult mvcResult = resultActions.andExpect(status().isNoContent()).andReturn();

        //then
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println("contentAsString = " + contentAsString);
    }

    @Test
    @DisplayName("username 중복 확인")
    void usernameAvailableCheck() throws Exception {
        //given
        String request = "홍길동";
        MemberDto response = memberResponse();

        doReturn(true).when(memberService)
                .isAvailableUsername(any(String.class));
        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/members/username/{username}", request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );
        MvcResult mvcResult = resultActions.andExpect(status().isNoContent()).andReturn();

        //then
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println("contentAsString = " + contentAsString);
    }

    private SignUpRequestDto signUpRequest() {
        return SignUpRequestDto.builder()
                .email("test@test.com")
                .username("testname")
                .password("test")
                .nickname("nickname")
                .subscription(false)
                .build();
    }

    private MemberDto memberResponse() {
        return MemberDto.builder()
                .email("test@test.com")
                .username("testname")
                .nickname("nickname")
                .subscription(false)
                .build();
    }


}