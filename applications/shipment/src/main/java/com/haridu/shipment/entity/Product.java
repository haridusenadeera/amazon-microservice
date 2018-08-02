package com.haridu.shipment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Product {
  private String name;
  private String description;
  private byte[] image;
  private double price;

}
