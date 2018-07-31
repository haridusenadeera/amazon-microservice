package com.haridu.order.repository;

import com.haridu.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findByAccountIdOrderByDate(long accountId);
  List<Order> findAllByOrderByOrderNumber();

}
