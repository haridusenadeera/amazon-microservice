package com.haridu.order.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class OrderLineItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private long productId;

  private int quantity;

  private double price;

  @Formula("quantity*price")
  private double totalPrice;

  private long shipmentId;

}
