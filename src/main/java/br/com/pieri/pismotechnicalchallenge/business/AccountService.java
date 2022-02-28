package br.com.pieri.pismotechnicalchallenge.business;

import br.com.pieri.pismotechnicalchallenge.data.entity.Account;
import br.com.pieri.pismotechnicalchallenge.data.repository.AccountRepository;
import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository repository;

    public Account addAccount(Account account){
        LOGGER.debug("addAccount: {}", account.getId());
        Account savedAccount = repository.save(account);
        return savedAccount;
    }

    public Optional<Account> find(Long accountId){
        LOGGER.debug("find: {}", accountId);
        return repository.findById(accountId);
    }

    public List<Account> findAll() {
        LOGGER.debug("findAll");
        Iterable<Account> all = repository.findAll();
        return IterableUtils.toList(all);
    }
}
