package com.haridu.shipment.controller;

import com.haridu.shipment.entity.Shipment;
import com.haridu.shipment.entity.ShipmentDetails;
import com.haridu.shipment.repository.ShipmentRepository;
import com.haridu.shipment.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
public class ShipmentController {

  @Autowired
  private ShipmentService shipmentService;

  @RequestMapping(value = "/shipments", method = RequestMethod.GET)
  public @ResponseBody List<?> getAllShipments(
      @RequestParam(value = "accountId", required = false) Long accountId) {

    if(accountId != null){
      return shipmentService.getShipmentsByAccountId(accountId);
    }
    return shipmentService.getAllShipments();
  }

//  @RequestMapping(value = "shipments/{id}", method = RequestMethod.GET)
//  public @ResponseBody ShipmentDetails getShipmentDetails(
//      @PathVariable(name = "id") Long shipmentId) {
//
//    return shipmentService.retrieveShipmentDetails(shipmentId);
//  }
}
