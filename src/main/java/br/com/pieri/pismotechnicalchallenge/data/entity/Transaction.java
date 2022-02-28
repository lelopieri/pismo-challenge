package br.com.pieri.pismotechnicalchallenge.data.entity;

import br.com.pieri.pismotechnicalchallenge.controller.rest.vo.TransactionVO;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Transaction {

    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "transaction_id_seq")
//    @SequenceGenerator(name = "transaction_id_seq", sequenceName = "transaction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account account;

    private Integer operationTypeId;

    private BigDecimal ammount;

    private LocalDateTime eventDate;

    public Transaction() {
    }

    public Transaction(Account account, Integer operationTypeId, BigDecimal ammount, LocalDateTime eventDate) {
        this.account = account;
        this.operationTypeId = operationTypeId;
        this.ammount = ammount;
        this.eventDate = eventDate;
    }

}
