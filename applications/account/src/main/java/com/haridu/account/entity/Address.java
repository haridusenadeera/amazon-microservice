package com.haridu.account.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String street;
  private String apt;
  private String city;
  private String state;
  private int zip;
  private String country;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;
}
