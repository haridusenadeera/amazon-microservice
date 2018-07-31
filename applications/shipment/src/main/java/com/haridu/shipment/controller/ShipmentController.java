package com.haridu.shipment.controller;

import com.haridu.shipment.entity.Shipment;
import com.haridu.shipment.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RepositoryRestController
public class ShipmentController {

  @Autowired
  private ShipmentRepository shipmentRepository;

  @RequestMapping(value = "/shipments", method = RequestMethod.GET)
  public @ResponseBody List<Shipment> getAllShipments(
      @RequestParam(value = "accountId", required = false) Long accountId) {

    if(accountId != null){
      return shipmentRepository.findByAccountIdOrderByDeliveryDate(accountId);
    }
    return shipmentRepository.findAll();
  }
}
