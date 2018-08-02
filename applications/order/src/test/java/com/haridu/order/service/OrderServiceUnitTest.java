package com.haridu.order.service;

import com.haridu.order.entity.*;
import com.haridu.order.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class OrderServiceUnitTest {

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private OrderService orderService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testOrderNumberNull() {
    Optional<Order> orderOptional = Optional.empty();

    when(orderRepository.findById(anyLong())).thenReturn(orderOptional);

    OrderDetails response = orderService.retrieveDetailsForOrder(1);
    assertNull("Return null if orderNumber does not exist", response);
  }

  @Test
  public void testFindByAccountId() {

    List<Order> orderList = new ArrayList<>();
    Order order = new Order();
    order.setTotalPrice(200.00);
    orderList.add(order);

    when(orderRepository.findByAccountIdOrderByDate(anyLong())).thenReturn(orderList);

    List<Order> response = orderService.findByAccountId(1);

    assertNotNull(response);
  }

  @Test
  public void testGetAllOrders() {

    List<Order> orderList = new ArrayList<>();
    Order order = new Order();
    order.setTotalPrice(200.00);
    orderList.add(order);

    when(orderRepository.findAllByOrderByOrderNumber()).thenReturn(orderList);

    List<Order> response = orderService.getAllOrders();

    assertNotNull(response);
  }

//  @Test(expected = RestClientException.class)
//  public void testGetShippingAddressByIdRestError() {
//    ResponseEntity<Address> responseEntity =
//        new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//    String url = "//account/addresses/1";
//    when(restTemplate.getForEntity(url, Address.class)).thenThrow(new RestClientException("Error"));
//    Address result = orderService.getShippingAddressById(1);
//
////    assertNull(result);
//  }

}
