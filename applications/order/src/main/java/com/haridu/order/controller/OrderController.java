package com.haridu.order.controller;

import com.haridu.order.entity.Order;
import com.haridu.order.entity.OrderDetails;
import com.haridu.order.repository.OrderRepository;
import com.haridu.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RepositoryRestController
public class OrderController {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderService orderService;

  @RequestMapping(value = "/orders", method = GET)
  public @ResponseBody List<Order> getAllOrders(
      @RequestParam(value = "accountId", required = false) Long accountId) {


    if(accountId != null){
      return orderRepository.findByAccountIdOrderByDate(accountId);
    }

    return orderRepository.findAllByOrderByOrderNumber();
  }

  @RequestMapping( value = "/orders/{id}", method = GET)
  public @ResponseBody OrderDetails getOrderDetails(@PathVariable(name = "id") Long orderNumber) {

    return orderService.retrieveDetailsForOrder(orderNumber);

  }


}
