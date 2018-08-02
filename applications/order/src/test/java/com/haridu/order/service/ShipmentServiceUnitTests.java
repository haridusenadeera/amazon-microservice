package com.haridu.order.service;

import com.haridu.order.entity.ShipmentDAO;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ShipmentServiceUnitTests {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private ShipmentService shipmentService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetShipmentById() {
    ShipmentDAO shipmentDAOMock = new ShipmentDAO();
    shipmentDAOMock.setAddressId(23);
    ResponseEntity<ShipmentDAO> responseEntity =
        new ResponseEntity<>(shipmentDAOMock, HttpStatus.OK);

    String url = "//shipment/shipments/1";
    when(restTemplate.getForEntity(url, ShipmentDAO.class)).thenReturn(responseEntity);
    ShipmentDAO shipmentDAO = shipmentService.getShipmentById(1);

    assertNotNull(shipmentDAO);
    assertEquals(23, shipmentDAO.getAddressId());
  }

  @Test
  public void testGetShipmentByIdEmptyResponse() {
    ResponseEntity<ShipmentDAO> responseEntity =
        new ResponseEntity<>(HttpStatus.OK);

    String url = "//shipment/shipments/1";
    when(restTemplate.getForEntity(url, ShipmentDAO.class)).thenReturn(responseEntity);
    ShipmentDAO shipmentDAO = shipmentService.getShipmentById(1);

    assertNull(shipmentDAO);
  }

  @Test
  public void testFallback() {
    ShipmentDAO shipmentDAO = shipmentService.shipmentFallback(1);
    assertEquals("2000-01-01", shipmentDAO.getShippedDate().toString());
  }

}
