package br.com.pieri.pismotechnicalchallenge.controller.rest.advices;

import br.com.pieri.pismotechnicalchallenge.business.exception.AccountNotFoundException;
import br.com.pieri.pismotechnicalchallenge.business.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BusinessAdvices {

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String accountNotFound(BusinessException e){
        return e.getMessage();
    }
}
