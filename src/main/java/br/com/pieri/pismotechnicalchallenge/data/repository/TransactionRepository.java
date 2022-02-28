package br.com.pieri.pismotechnicalchallenge.data.repository;

import br.com.pieri.pismotechnicalchallenge.data.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
