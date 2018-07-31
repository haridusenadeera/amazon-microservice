package com.haridu.order.service;

import com.haridu.order.entity.Address;
import com.haridu.order.entity.Order;
import com.haridu.order.entity.OrderDetails;
import com.haridu.order.entity.ShipmentDAO;
import com.haridu.order.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceUnitTest {

  @TestConfiguration
  static class OrderServiceTestContextConfiguration {

    @Bean
    public OrderService employeeService() {
      return new OrderService();
    }
  }

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private OrderRepository orderRepository;

  @InjectMocks
  private OrderService orderService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

//  @Test
//  public void testGetShippingAddressById() {
////    Address address = new Address();
////    address.setCity("Chicago");
//
//    ResponseEntity responseEntity = mock(ResponseEntity.class);
//
//    when(
//        restTemplate.getForEntity(
//            anyString(),
//            any()
//        )
//    ).thenReturn(responseEntity);
//
//    Address addressResponse = orderService.getShippingAddressById(1);
//
////    assertEquals("Chicago", "Chicago");
//    assertThat(addressResponse, isNotNull());
//  }

  @Test
  public void testOrderNumberNull() {
    Optional<Order> orderOptional = Optional.empty();

    when(orderRepository.findById(anyLong())).thenReturn(orderOptional);

    OrderDetails response = orderService.retrieveDetailsForOrder(1);
    assertNull("Return null if orderNumber does not exist", response);
  }

//  @Test
//  public void testProductServiceDown() {
//    ShipmentDAO shipmentDAO = new ShipmentDAO();
//    shipmentDAO.setAccountId(1);
//
//    Address address = new Address();
//    address.setCity("Chicago");
//
//
//  }

}
