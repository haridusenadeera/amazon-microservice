package com.haridu.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderLineItemList {
  private List<OrderLineItem> orderLineItems;
}
