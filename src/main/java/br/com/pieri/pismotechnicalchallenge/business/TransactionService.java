package br.com.pieri.pismotechnicalchallenge.business;

import br.com.pieri.pismotechnicalchallenge.business.enums.OperationType;
import br.com.pieri.pismotechnicalchallenge.business.exception.BusinessException;
import br.com.pieri.pismotechnicalchallenge.data.entity.Account;
import br.com.pieri.pismotechnicalchallenge.data.entity.Transaction;
import br.com.pieri.pismotechnicalchallenge.data.repository.AccountRepository;
import br.com.pieri.pismotechnicalchallenge.data.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.pieri.pismotechnicalchallenge.business.enums.OperationType.getByid;

@Service
public class TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Transaction addTransaction(Long accountId, Integer operationType, BigDecimal amount) throws BusinessException {
        LOGGER.debug("AccountID: {} OperationType: {} amount: {}", accountId, operationType, amount);
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()){
            amount = amount.abs();
            OperationType transactionType = getByid(operationType);
            switch (transactionType) {
                case COMPRA_A_VISTA:
                case COMPRA_PARCELADA:
                case SAQUE:
                    amount = amount.multiply(new BigDecimal(-1));
            }

            Transaction transaction = transactionRepository.save(new Transaction(account.get(), transactionType.getId(), amount, LocalDateTime.now()));
            return transaction;
        }else {
            LOGGER.error("Account {} not found to add Transaction", accountId);
            throw new BusinessException("Account Not Found " + accountId);
        }

    }
}
