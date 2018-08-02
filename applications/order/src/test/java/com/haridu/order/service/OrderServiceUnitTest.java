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

  @Test
  public void testGetShipmentById() {
    ShipmentDAO shipmentDAOMock = new ShipmentDAO();
    shipmentDAOMock.setAddressId(23);
    ResponseEntity<ShipmentDAO> responseEntity =
        new ResponseEntity<>(shipmentDAOMock, HttpStatus.OK);

    String url = "//shipment/shipments/1";
    when(restTemplate.getForEntity(url, ShipmentDAO.class)).thenReturn(responseEntity);
    ShipmentDAO shipmentDAO = orderService.getShipmentById(1);

    assertNotNull(shipmentDAO);
    assertEquals(23, shipmentDAO.getAddressId());
  }

  @Test(expected = NullPointerException.class)
  public void testGetShipmentByIdEmptyResponse() throws Exception{
    ResponseEntity<ShipmentDAO> responseEntity =
        new ResponseEntity<>(HttpStatus.OK);

    String url = "//shipment/shipments/1";
    when(restTemplate.getForEntity(url, ShipmentDAO.class)).thenReturn(responseEntity);
    ShipmentDAO shipmentDAO = orderService.getShipmentById(1);

    assertNull(shipmentDAO);
  }

  @Test
  public void testGetNameByProductId() {
    Product productMock = new Product();
    productMock.setName("TestProduct");

    ResponseEntity<Product> responseEntity =
        new ResponseEntity<>(productMock, HttpStatus.OK);

    String url = "//product/products/1";
    when(restTemplate.getForEntity(url, Product.class)).thenReturn(responseEntity);
    String result = orderService.getNameByProductId(1);

    assertEquals("TestProduct", result);
  }

  @Test(expected = NullPointerException.class)
  public void testGetNameByProductIdEmptyResponse() {
    ResponseEntity<Product> responseEntity =
        new ResponseEntity<>(HttpStatus.OK);

    String url = "//product/products/1";
    when(restTemplate.getForEntity(url, Product.class)).thenReturn(responseEntity);
    String result = orderService.getNameByProductId(1);

    assertNull(result);
  }

  @Test
  public void testGetShippingAddressById() {
    Address addressMock = new Address();
    addressMock.setCity("Chicago");

    ResponseEntity<Address> responseEntity =
        new ResponseEntity<>(addressMock, HttpStatus.OK);

    String url = "//account/addresses/1";
    when(restTemplate.getForEntity(url, Address.class)).thenReturn(responseEntity);
    Address result = orderService.getShippingAddressById(1);

    assertEquals("Chicago", result.getCity());
  }

  @Test(expected = NullPointerException.class)
  public void testGetShippingAddressByIdEmptyResponse() {
    ResponseEntity<Address> responseEntity =
        new ResponseEntity<>(HttpStatus.OK);

    String url = "//account/addresses/1";
    when(restTemplate.getForEntity(url, Address.class)).thenReturn(responseEntity);
    Address result = orderService.getShippingAddressById(1);

    assertNull(result);
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
