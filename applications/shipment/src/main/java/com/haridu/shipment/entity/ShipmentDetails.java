package com.haridu.shipment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ShipmentDetails {
  private long orderNumber;
  private Date shipmentDate;
  private Date deliveryDate;
  private List<LineItem> orderLineItems;
}
