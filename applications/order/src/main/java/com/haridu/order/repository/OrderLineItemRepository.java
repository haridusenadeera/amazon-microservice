package com.haridu.order.repository;

import com.haridu.order.entity.OrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {
  List<OrderLineItem> findByShipmentId(long shipmentId);
}
