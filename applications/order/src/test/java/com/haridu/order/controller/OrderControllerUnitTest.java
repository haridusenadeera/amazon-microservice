package com.haridu.order.controller;

import com.haridu.order.config.SecurityConfig;
import com.haridu.order.entity.Order;
import com.haridu.order.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
@ContextConfiguration(classes = {SecurityConfig.class})
public class OrderControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OrderService orderService;

  @InjectMocks
  private OrderController orderController;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAllOrders() throws Exception {
    List<Order> orderList = new ArrayList<>();
    Order order = new Order();
    order.setTotalPrice(1200.00);
    orderList.add(order);

    when(orderService.getAllOrders()).thenReturn(orderList);

    mockMvc
        .perform(get("/orders"))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void testGetAllOrdersForAnAccount() throws Exception {

    List<Order> orderList = new ArrayList<>();
    Order order = new Order();
    order.setTotalPrice(1200.00);
    orderList.add(order);

    when(orderService.findByAccountId(anyLong())).thenReturn(orderList);

    mockMvc
        .perform(get("/orders?accountId=1"))
        .andExpect(status().isOk())
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
