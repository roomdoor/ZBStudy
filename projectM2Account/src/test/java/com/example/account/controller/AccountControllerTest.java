package com.example.account.controller;

import com.example.account.domain.Account;
import com.example.account.dto.AccountDto;
import com.example.account.dto.CreateAccount;
import com.example.account.dto.DeleteAccount;
import com.example.account.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("1. success create account")
    @Test
    public void test_1() throws Exception {
        //given
        given(accountService.createAccount(anyLong(), anyLong()))
                .willReturn(
                        AccountDto.builder()
                                .userId(1L)
                                .accountNumber("12345")
                                .registerAt(LocalDateTime.now())
                                .unRegisterAt(LocalDateTime.now())
                                .build()
                );
        //when
        //then
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new CreateAccount.Request(1L, 100L))
                        )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.accountNumber").value("12345"))
                .andDo(print());
    }

    @DisplayName("2. success delete account")
    @Test
    public void test_2() throws Exception {
        //given
        given(accountService.deleteAccount(anyLong(), any()))
                .willReturn(
                        AccountDto.builder()
                                .userId(1L)
                                .accountNumber("1234567890")
                                .registerAt(LocalDateTime.now())
                                .unRegisterAt(LocalDateTime.now())
                                .build()
                );
        //when
        //then
        mockMvc.perform(delete("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new DeleteAccount.Request(1L, "1234567890"))
                        )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andDo(print());
    }


    @DisplayName("3. get account by user id")
    @Test
    public void test_3() throws Exception {
        //given
        List<AccountDto> list = new ArrayList<>();
        list.add(AccountDto.builder()
                .balance(1000L)
                .accountNumber("0987654321")
                .build());
        list.add(AccountDto.builder()
                .balance(4000L)
                .accountNumber("1234567890")
                .build());
        list.add(AccountDto.builder()
                .balance(6000L)
                .accountNumber("3453453453")
                .build());
        given(accountService.getAccountsByUserId(anyLong()))
                .willReturn(list);

        //when
        //then
        mockMvc.perform(get("/account?user_id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new CreateAccount.Request(1L, 100L))
                        )
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].accountNumber").value("0987654321"))
                .andExpect(jsonPath("$[1].accountNumber").value("1234567890"))
                .andExpect(jsonPath("$[2].accountNumber").value("3453453453"))
                .andExpect(jsonPath("$.length()").value(3));

    }

}