package com.haridu.order.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderDetails {

  private long orderNumber;
  private Address shippingAddress;
  private double totalPrice;
  private List<LineItem> lineItems;
  private List<Shipment> shipments;

}
