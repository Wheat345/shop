package com.linxi.shop.models;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public Customer findByEmail(String email);
    
    
    @Query("select c from Customer c where c.userid = ?1")
    public List<Customer> findAllbyUserId(long userid);
    
}


