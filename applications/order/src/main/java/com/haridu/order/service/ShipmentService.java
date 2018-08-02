package com.haridu.order.service;

import com.haridu.order.entity.ShipmentDAO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

@Service
public class ShipmentService {

  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "shipmentFallback")
  public ShipmentDAO getShipmentById(long shipmentId) {

    String url = "//shipment/shipments/" + shipmentId;

    return restTemplate.getForEntity(url, ShipmentDAO.class).getBody();
  }

  public ShipmentDAO shipmentFallback(long shipmentId) {
    ShipmentDAO shipmentDAO = new ShipmentDAO();
    shipmentDAO.setShippedDate(Date.valueOf("2000-01-01"));
    shipmentDAO.setDeliveryDate(Date.valueOf("2000-01-01"));

    return shipmentDAO;
  }
}
