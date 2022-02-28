package br.com.pieri.pismotechnicalchallenge.controller.rest;

import br.com.pieri.pismotechnicalchallenge.business.AccountService;
import br.com.pieri.pismotechnicalchallenge.controller.rest.vo.AccountVO;
import br.com.pieri.pismotechnicalchallenge.data.entity.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        Account account1 = new Account();
        account1.setId(1l);
        account1.setDocumentNumber(111l);

        Account account2 = new Account();
        account2.setId(2l);
        account2.setDocumentNumber(222l);

        List<Account> accountList = new ArrayList<>(){
            {
                add(account1);
                add(account2);
            }
        };

        when(accountService.find(1l)).thenReturn(Optional.of(account1));
        when(accountService.findAll()).thenReturn(accountList);
        when(accountService.addAccount(Mockito.any())).thenReturn(account1);

    }

    @Test
    void all() {
        try {
            mockMvc.perform(get("/accounts"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$", Matchers.hasSize(2)))
                    .andExpect(jsonPath("$[0].account_id").exists())
                    .andExpect(jsonPath("$[1].document_number").exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void find() {
        try {
            mockMvc.perform(get("/accounts/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.account_id").exists())
                    .andExpect(jsonPath("$.document_number").exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void findNotFound() {
        try {
            mockMvc.perform(get("/accounts/99"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Account not found")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void addNewAccount() {
        try {
            AccountVO accountVO = new AccountVO();
            accountVO.setDocument_number(12345l);
            mockMvc.perform(
                    post("/accounts")
                            .content(asJsonString(accountVO))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.account_id").exists())
                    .andExpect(jsonPath("$.document_number").exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String asJsonString(final Object o){
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw  new RuntimeException(e);
        }
    }
}