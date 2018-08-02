package com.haridu.shipment.service;

import com.haridu.shipment.entity.*;
import com.haridu.shipment.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ShipmentService {

  @Autowired
  private ShipmentRepository shipmentRepository;

  @Autowired
  private RestTemplate restTemplate;

  public List<Shipment> getAllShipments() {
    return shipmentRepository.findAll();
  }

  public List<ShipmentDetails> getShipmentsByAccountId(long accountId) {

    List<Shipment> shipmentsForAccount =
        shipmentRepository.findByAccountIdOrderByDeliveryDate(accountId);

    List<ShipmentDetails> shipmentsWithDetailsForAccount = new ArrayList<>();

    for(Shipment shipment: shipmentsForAccount){

      ShipmentDetails shipmentDetails = new ShipmentDetails();

      // line items for one shipment to include in shipmentDetail object
      List<LineItem> lineItems = new ArrayList<>();

      shipmentDetails.setOrderNumber(shipment.getOrderNumber());
      shipmentDetails.setDeliveryDate(shipment.getDeliveryDate());
      shipmentDetails.setShipmentDate(shipment.getShippedDate());

      // get all the orderLineItems for this shipment
      OrderLineItemList orderLineItemList = getOrderLineItemsForShipment(shipment.getId()).getBody();

      if (orderLineItemList != null) {

        for (OrderLineItem orderLineItem : orderLineItemList.getOrderLineItems()) {
          lineItems.add(
              OrderLineItemToLineItem(orderLineItem)
          );
        }

      }

      // set LineItems(it is named "orderLineItems" per assignment guidelines 5.b.iv)
      shipmentDetails.setOrderLineItems(lineItems);


      shipmentsWithDetailsForAccount.add(shipmentDetails);

    } // end of loop, all shipments for accountId

    return shipmentsWithDetailsForAccount;

  }

  protected LineItem OrderLineItemToLineItem(OrderLineItem orderLineItem) {
    LineItem lineItem = new LineItem();
    lineItem.setQuantity(orderLineItem.getQuantity());

    lineItem.setProductName(
        getNameByProductId(orderLineItem.getProductId())
    );

    return lineItem;
  }

  protected ResponseEntity<OrderLineItemList> getOrderLineItemsForShipment(long shipmentId) {
    String url = "//order/orderLineItems?shipmentId=" + shipmentId;

    ResponseEntity<OrderLineItemList> responseEntity;

    return restTemplate.getForEntity(url, OrderLineItemList.class);

  }

  protected String getNameByProductId(long productId) {

    String url = "//product/products/" + productId;

    ResponseEntity<Product> response;

    try {
      response = restTemplate.getForEntity(url, Product.class);
      return Objects.requireNonNull(response.getBody()).getName();
    } catch (RestClientException e) {
      e.printStackTrace();
      return null;
    }
  }
}
