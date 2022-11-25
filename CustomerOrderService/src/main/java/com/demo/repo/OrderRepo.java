package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Order;
@Repository
@Transactional
public interface OrderRepo extends JpaRepository<Order, Long>   {

}
