package com.haridu.shipment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderLineItem {
  private long productId;

  private int quantity;

  private double price;

  private double totalPrice;

  private long shipmentId;

  private long orderNumber;
}
