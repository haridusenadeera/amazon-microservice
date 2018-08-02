package com.haridu.order.service;

import com.haridu.order.entity.*;
import com.haridu.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductService productService;

  @Autowired
  private AddressService addressService;

  @Autowired
  private ShipmentService shipmentService;

  public OrderDetails retrieveDetailsForOrder(long orderNumber){

    OrderDetails orderDetails = new OrderDetails();

    Optional<Order> orderOptional = orderRepository.findById(orderNumber);
    if(!orderOptional.isPresent()){
      return null;
    }

    Order order = orderOptional.get();

    orderDetails.setOrderNumber(order.getOrderNumber());
    orderDetails.setTotalPrice(order.getTotalPrice());
    orderDetails.setShippingAddress(
        addressService.getShippingAddressById(order.getShippingAddressId())
    );

    // for each orderLineItems in an order,
    // get productId from OrderLineItem object and call product service to get the product name
    // then add a list of LineItem objects (that contain productName and quantity) to orderDetails
    List<OrderLineItem> orderLineItems =order.getOrderLineItems();

    //Line Items for an order
    List<LineItem> lineItems = new ArrayList<>();

    // store distinct shipmentIds from OrderLineItems,
    // so you can call shipment service to get shipment details
    HashMap<Long, List<OrderLineItem>> distinctShipmentIds = new HashMap<>();

    for(OrderLineItem orderLineItem: orderLineItems){

      // create a LineItem for each OrderLineItem present and add it to lineItems list
      LineItem lineItem = new LineItem();
      lineItem.setQuantity(orderLineItem.getQuantity());

      // call product service to get the product name
      lineItem.setProductName(
          productService.getNameByProductId(orderLineItem.getProductId()).getName()
      );

      lineItems.add(lineItem);

      if(distinctShipmentIds.get(orderLineItem.getShipmentId()) == null){
        // if key(shipmentId) is not present in the hashmap,
        // then create it and add a new list with the current orderLineItem as it's value

        List<OrderLineItem> orderLineItemsForShipment = new ArrayList<>();
        orderLineItemsForShipment.add(orderLineItem);

        distinctShipmentIds.put(orderLineItem.getShipmentId(), orderLineItemsForShipment);
      } else {
        // if shipmentId (key) is already present in the hashmap,
        // then add orderLineItem to it's value(list of OrderLineItem)
        distinctShipmentIds.get(orderLineItem.getShipmentId()).add(orderLineItem);
      }
    }

    orderDetails.setLineItems(lineItems);

    // shipments for an order
    List<Shipment> shipments = new ArrayList<>();

    distinctShipmentIds.forEach((key, value) -> {
      Shipment shipment = new Shipment();
      shipment.setOrderLineItems(value);

      // to get the delivery date and shipped date, call the shipment service
      ShipmentDAO shipmentDAO;
      shipmentDAO = shipmentService.getShipmentById(key);

      if (shipmentDAO != null) {
        shipment.setDeliveryDate(shipmentDAO.getDeliveryDate());
        shipment.setShippedDate(shipmentDAO.getShippedDate());
      }

      shipments.add(shipment);

    });

    orderDetails.setShipments(shipments);

    return orderDetails;
  }

  public List<Order> findByAccountId(long accountId) {
    return orderRepository.findByAccountIdOrderByDate(accountId);
  }

  public List<Order> getAllOrders() {
    return orderRepository.findAllByOrderByOrderNumber();
  }

}
