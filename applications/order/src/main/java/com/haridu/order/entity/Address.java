package com.haridu.order.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
public class Address {

  private String street;
  private String apt;
  private String city;
  private String state;
  private int zip;
  private String country;

}
