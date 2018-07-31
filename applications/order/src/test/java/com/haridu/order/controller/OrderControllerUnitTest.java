package com.haridu.order.controller;

import com.haridu.order.OrderApplicationTests;
import com.haridu.order.config.SecurityConfig;
import com.haridu.order.entity.Order;
import com.haridu.order.entity.OrderDetails;
import com.haridu.order.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(
    classes = {SecurityConfig.class, OrderApplicationTests.class})
@WebMvcTest
public class OrderControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OrderDetails orderDetails;

  @MockBean
  private OrderRepository orderRepository;

  @Test
  public void testGetAllOrdersForAccount() throws Exception {
    mockMvc
        .perform(get("/orders/?accountId=1"))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
  }

  @Test
  public void testGetAllOrders() throws Exception {

    Order order = new Order();
    order.setTotalPrice(100);

    Order order1 = new Order();
    order1.setTotalPrice(200);

    List<Order> orderList = new ArrayList<>();
    orderList.add(order);
    orderList.add(order1);

    when(orderRepository.findAllByOrderByOrderNumber()).thenReturn(orderList);

    mockMvc
        .perform(get("/orders"))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
  }

  @Test
  public void testOrderDetailsForAccount() throws Exception {
    mockMvc
        .perform(get("/orders/1"))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
  }
}
