package br.com.pieri.pismotechnicalchallenge.controller.rest.vo;

import br.com.pieri.pismotechnicalchallenge.data.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionVO {

    private Long id;
    private Long account_id;
    private Integer operation_type_id;
    private BigDecimal amount;
    private LocalDateTime eventDate;

    public TransactionVO(Transaction transaction){
        this.id = transaction.getId();
        this.account_id = transaction.getAccount().getId();
        this.operation_type_id = transaction.getOperationTypeId();
        this.amount = transaction.getAmmount();
        this.eventDate = transaction.getEventDate();
    }
}
