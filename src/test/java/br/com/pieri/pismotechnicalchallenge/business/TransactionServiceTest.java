package br.com.pieri.pismotechnicalchallenge.business;

import br.com.pieri.pismotechnicalchallenge.business.enums.OperationType;
import br.com.pieri.pismotechnicalchallenge.business.exception.AccountNotFoundException;
import br.com.pieri.pismotechnicalchallenge.business.exception.BusinessException;
import br.com.pieri.pismotechnicalchallenge.data.entity.Account;
import br.com.pieri.pismotechnicalchallenge.data.entity.Transaction;
import br.com.pieri.pismotechnicalchallenge.data.repository.AccountRepository;
import br.com.pieri.pismotechnicalchallenge.data.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        Account account = new Account();
        account.setId(1l);
        account.setDocumentNumber(12345l);
        lenient().when(accountRepository.findById(1l)).thenReturn(Optional.of(account));
        lenient().when(accountRepository.findById(100l)).thenReturn(Optional.empty());
        lenient().when(transactionRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void addNegativeTransaction() {
        Transaction t = transactionService.addTransaction(1l, OperationType.COMPRA_PARCELADA.getId(), BigDecimal.valueOf(135));
        assertEquals(BigDecimal.valueOf(-135), t.getAmmount());
    }

    @Test
    void addPositiveTransaction() {
        Transaction t = transactionService.addTransaction(1l, OperationType.PAGAMENTO.getId(), BigDecimal.valueOf(1500));
        assertEquals(BigDecimal.valueOf(1500), t.getAmmount());
    }

    @Test
    void invalidAccount() {
        assertThrows(BusinessException.class,
                () -> {
                    transactionService.addTransaction(100l, OperationType.PAGAMENTO.getId(), BigDecimal.valueOf(1500));
                });

    }
}