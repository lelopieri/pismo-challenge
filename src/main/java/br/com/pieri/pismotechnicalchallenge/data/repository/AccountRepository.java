package br.com.pieri.pismotechnicalchallenge.data.repository;

import br.com.pieri.pismotechnicalchallenge.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
