package com.haridu.shipment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Shipment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private long accountId;

  private long addressId;

  private Date shippedDate;

  private Date deliveryDate;
}
