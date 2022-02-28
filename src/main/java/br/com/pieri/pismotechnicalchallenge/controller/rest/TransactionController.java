package br.com.pieri.pismotechnicalchallenge.controller.rest;

import br.com.pieri.pismotechnicalchallenge.business.TransactionService;
import br.com.pieri.pismotechnicalchallenge.business.exception.BusinessException;
import br.com.pieri.pismotechnicalchallenge.controller.rest.vo.TransactionVO;
import br.com.pieri.pismotechnicalchallenge.data.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @PostMapping("")

    public ResponseEntity<TransactionVO> addNewTransaction(@RequestBody TransactionVO transactionVO) {
        LOGGER.debug("addNewTransaction account:{} type:{} amount:{}", transactionVO.getAccount_id(), transactionVO.getOperation_type_id(), transactionVO.getAmount());
        Transaction transaction = null;
        transaction = transactionService.addTransaction(transactionVO.getAccount_id(), transactionVO.getOperation_type_id(), transactionVO.getAmount());
        TransactionVO vo = new TransactionVO(transaction);
        return new ResponseEntity<>(vo, HttpStatus.CREATED);
    }
}
