package br.com.pieri.pismotechnicalchallenge.data.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Account {

    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "account_id_seq")
//    @SequenceGenerator(name = "account_id_seq", sequenceName = "account_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long documentNumber;

    private LocalDateTime creationDateTime;

    public Account() {
    }

    public Account(Long documentNumber){
        this.documentNumber = documentNumber;
        this.creationDateTime = LocalDateTime.now();
    }
}
