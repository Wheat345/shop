package com.linxi.shop.models;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    public Product findByName(String name);
    
    
    @Query("select p from Product p where p.userid = ?1")
    public List<Product> findAllbyUserId(long userid);
    
}


