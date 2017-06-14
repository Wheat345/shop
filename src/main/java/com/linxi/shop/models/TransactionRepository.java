package com.linxi.shop.models;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    //public Transaction findByTransactionId(String transactionid);
    
    
    @Query("select t from Transaction t where t.userid = ?1")
    public List<Transaction> findAllbyUserId(long userid);
    
}


