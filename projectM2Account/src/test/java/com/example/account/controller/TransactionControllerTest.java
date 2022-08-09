package com.example.account.controller;

import com.example.account.dto.CancelBalance;
import com.example.account.dto.TransactionDto;
import com.example.account.dto.UseBalance;
import com.example.account.service.TransactionService;
import com.example.account.type.TransactionResultType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static com.example.account.type.TransactionResultType.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {
    @MockBean
    private TransactionService transactionService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("01. use balance success")
    @Test
    public void test_01() throws Exception {
        //given
        given(transactionService.useBalance(anyLong(), any(), anyLong()))
                .willReturn(TransactionDto.builder()
                        .accountNumber("1234512345")
                        .transactionResult(S)
                        .transactionId("transactionId")
                        .amount(1000L)
                        .transactedAt(LocalDateTime.now())
                        .build()
                );
        //when
        //then
        mockMvc.perform(post("/transaction/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new UseBalance.Request(
                                        1L, "1234512345", 1000L))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("1234512345"))
                .andExpect(jsonPath("$.amount").value(1000L))
                .andExpect(jsonPath("$.transactionId").value("transactionId"))
                .andExpect(jsonPath("$.transactionResult").value("S"));
    }

    @DisplayName("02. cancel balance success")
    @Test
    public void test_02() throws Exception {
        //given
        given(transactionService.cancelBalance(anyString(), any(), anyLong()))
                .willReturn(TransactionDto.builder()
                        .accountNumber("5555544444")
                        .transactionResult(S)
                        .transactionId("transactionId")
                        .amount(3000L)
                        .transactedAt(LocalDateTime.now())
                        .build()
                );
        //when
        //then
        mockMvc.perform(post("/transaction/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new CancelBalance.Request(
                                        "transactionId", "5555544444", 5000L))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("5555544444"))
                .andExpect(jsonPath("$.amount").value(3000L))
                .andExpect(jsonPath("$.transactionId").value("transactionId"))
                .andExpect(jsonPath("$.transactionResult").value("S"));
    }

    @DisplayName("03. query transaction success")
    @Test
    public void test_03() throws Exception {
        //given
        given(transactionService.queryTransaction(anyString()))
                .willReturn(TransactionDto.builder()
                        .accountNumber("5555544444")
                        .transactionResult(S)
                        .transactionId("queryTransaction")
                        .amount(3000L)
                        .transactedAt(LocalDateTime.now())
                        .build()
                );
        //when
        //then
        mockMvc.perform(get("/transaction/cancel")
                        .param("transactionId","queryTransaction"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("5555544444"))
                .andExpect(jsonPath("$.amount").value(3000L))
                .andExpect(jsonPath("$.transactionId").value("queryTransaction"))
                .andExpect(jsonPath("$.transactionResult").value("S"));
    }

}