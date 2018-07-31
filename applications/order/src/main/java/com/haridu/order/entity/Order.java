package com.haridu.order.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long orderNumber;

  private long accountId;

  private Date date;

  private long shippingAddressId;

  @OneToMany
  @JoinColumn(name = "order_id")
  private List<OrderLineItem> orderLineItems;

  private double totalPrice;
}
