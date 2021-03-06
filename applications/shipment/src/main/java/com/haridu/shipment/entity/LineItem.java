package com.haridu.shipment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LineItem {
  private String productName;
  private int quantity;
}
