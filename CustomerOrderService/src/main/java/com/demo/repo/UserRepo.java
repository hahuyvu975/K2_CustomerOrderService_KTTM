package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.User;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long> {

	
     public User findByName(String name) ;
     
     public User findByUsername(String username) ;
     
}

