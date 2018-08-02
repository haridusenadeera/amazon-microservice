package com.haridu.order.controller;

import com.haridu.order.entity.Order;
import com.haridu.order.entity.OrderLineItem;
import com.haridu.order.entity.OrderLineItemList;
import com.haridu.order.repository.OrderLineItemRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RepositoryRestController
public class OrderLineItemController {

  @Autowired
  private OrderLineItemRepository orderLineItemRepository;

  @RequestMapping(value = "/orderLineItems", method = GET)
  public ResponseEntity getAllOrderLineItems(
      @RequestParam(value = "shipmentId", required = false) Long shipmentId) {

    if(shipmentId != null){


      OrderLineItemList orderLineItemList = new OrderLineItemList(
          orderLineItemRepository.findByShipmentId(shipmentId)
      );

      return new ResponseEntity<>(orderLineItemList, HttpStatus.OK);
    }

    List<OrderLineItem> response = orderLineItemRepository.findAll();

    return new ResponseEntity<>(response, HttpStatus.OK);

  }
}
