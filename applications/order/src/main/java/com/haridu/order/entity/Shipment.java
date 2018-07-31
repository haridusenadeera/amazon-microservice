package com.haridu.order.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Shipment {

  private List<OrderLineItem> orderLineItems;
  private Date shippedDate;
  private Date deliveryDate;

}
