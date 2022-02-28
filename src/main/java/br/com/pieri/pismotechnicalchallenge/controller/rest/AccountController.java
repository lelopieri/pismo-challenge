package br.com.pieri.pismotechnicalchallenge.controller.rest;

import br.com.pieri.pismotechnicalchallenge.business.AccountService;
import br.com.pieri.pismotechnicalchallenge.business.exception.AccountNotFoundException;
import br.com.pieri.pismotechnicalchallenge.controller.rest.vo.AccountVO;
import br.com.pieri.pismotechnicalchallenge.data.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public List<AccountVO> all(){
        LOGGER.debug("all()");
        List<AccountVO> vos = new ArrayList<>();
        List<Account> accountList = accountService.findAll();
        LOGGER.debug("{} accounts found", accountList != null ? accountList.size() : 0);
        accountList.stream().forEach(account -> {
            vos.add(new AccountVO(account));
        });
        return vos;
    }

    @GetMapping("/{id}")
    public AccountVO find(@PathVariable Long id){
        LOGGER.debug("find: {}",id );
        Optional<Account> account = accountService.find(id);
        if(account.isPresent()){
            return new AccountVO(account.get());
        }else{
            throw new AccountNotFoundException( id);
        }
    }

    @PostMapping("")
    public ResponseEntity<AccountVO> addNewAccount(@RequestBody AccountVO accountVO) {
        LOGGER.debug("addNewAccount: {}", accountVO.getDocument_number());
        Account savedAccount = accountService.addAccount(new Account(accountVO.getDocument_number()));
        AccountVO vo =  new AccountVO(savedAccount);
        return new ResponseEntity<>(vo, HttpStatus.CREATED);
    }

}
