package br.com.pieri.pismotechnicalchallenge.controller.rest.vo;

import br.com.pieri.pismotechnicalchallenge.data.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountVO {
    private Long account_id;
    private Long document_number;

    public AccountVO(Account account) {
        this.account_id = account.getId();
        this.document_number = account.getDocumentNumber();
    }

}
