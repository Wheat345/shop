package com.linxi.shop.models;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface CustomerDao extends CrudRepository<Customer, Long> {

  /**
   * Return the user having the passed email or null if no user is found.
   * 
   * @param email the user email.
   */
  public Customer findByEmail(String email);

} // class UserDao
