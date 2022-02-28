package br.com.pieri.pismotechnicalchallenge.controller.rest;

import br.com.pieri.pismotechnicalchallenge.business.TransactionService;
import br.com.pieri.pismotechnicalchallenge.business.enums.OperationType;
import br.com.pieri.pismotechnicalchallenge.business.exception.BusinessException;
import br.com.pieri.pismotechnicalchallenge.controller.rest.vo.TransactionVO;
import br.com.pieri.pismotechnicalchallenge.data.entity.Account;
import br.com.pieri.pismotechnicalchallenge.data.entity.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        Account account = new Account();
        account.setId(1l);
        account.setDocumentNumber(123l);
        account.setCreationDateTime(LocalDateTime.now());

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setOperationTypeId(OperationType.COMPRA_PARCELADA.getId());
        transaction.setAmmount(BigDecimal.valueOf(-100.00));
        transaction.setEventDate(LocalDateTime.now());


        Mockito.when(transactionService.addTransaction(99l, 4, BigDecimal.valueOf(100l)))
                .thenThrow(new BusinessException("Account no found 99"));

        Mockito.when(transactionService.addTransaction(1l, OperationType.COMPRA_PARCELADA.getId(), BigDecimal.valueOf(100)))
                .thenReturn(transaction);
    }

    @Test
    void addNewTransactionSuccess() {
        try {
            TransactionVO vo = new TransactionVO();
            vo.setAccount_id(1l);
            vo.setAmount(BigDecimal.valueOf(100l));
            vo.setOperation_type_id(2);
            mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                            .content(asJsonString(vo))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                    )
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.account_id").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(BigDecimal.valueOf(-100.0)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void addNewTransactionBadRequest() {
        try {
            TransactionVO vo = new TransactionVO();
            vo.setAccount_id(99l);
            vo.setAmount(BigDecimal.valueOf(100l));
            vo.setOperation_type_id(4);

            mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                            .content(asJsonString(vo))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                    )
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String asJsonString(final Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}