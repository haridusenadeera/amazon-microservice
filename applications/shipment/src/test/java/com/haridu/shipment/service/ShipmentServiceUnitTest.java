package com.haridu.shipment.service;

import com.haridu.shipment.entity.Shipment;
import com.haridu.shipment.repository.ShipmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ShipmentServiceUnitTest {

  @Mock
  private ShipmentRepository shipmentRepository;

  @InjectMocks
  private ShipmentService shipmentService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAllShipments() {

    List<Shipment> shipmentsMock = new ArrayList<>();
    Shipment shipment = new Shipment();
    shipment.setAccountId(1);
    shipmentsMock.add(shipment);

    when(shipmentRepository.findAll()).thenReturn(shipmentsMock);

    List<Shipment> result = shipmentService.getAllShipments();

    assertNotNull(result);
  }

  @Test
  public void testFindByAccountId() {
    List<Shipment> shipmentsMock = new ArrayList<>();
    Shipment shipment = new Shipment();
    shipment.setAccountId(1);
    shipmentsMock.add(shipment);

    when(shipmentRepository.findByAccountIdOrderByDeliveryDate(anyLong()))
        .thenReturn(shipmentsMock);

    List<Shipment> result = shipmentService.getShipmentsByAccountId(1);

    assertNotNull(result);

  }
}
