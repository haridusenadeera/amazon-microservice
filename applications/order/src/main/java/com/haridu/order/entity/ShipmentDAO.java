package com.haridu.order.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class ShipmentDAO {

  private long id;

  private long accountId;

  private long addressId;

  private Date shippedDate;

  private Date deliveryDate;

}
