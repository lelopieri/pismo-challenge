package br.com.pieri.pismotechnicalchallenge.business.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}
