package com.haridu.shipment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderDAO {
  private long orderNumber;
  private double totalPrice;
  private List<LineItem> lineItems;
  private Object shippingAddress;
  private List<?> shipments;
}
