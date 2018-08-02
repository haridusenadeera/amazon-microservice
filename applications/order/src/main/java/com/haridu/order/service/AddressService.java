package com.haridu.order.service;

import com.haridu.order.entity.Address;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {

  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "addressFallback")
  public Address getShippingAddressById(long id){

    String url = "//account/addresses/" + id;

    return restTemplate.getForEntity(url, Address.class).getBody();
  }

  public Address addressFallback(long id) {
    Address address = new Address();
    address.setCity("City-PlaceHolder");
    address.setApt("0");
    address.setCountry("Country-Placeholder");
    address.setState("ZZ");
    address.setStreet("PlaceHolder-Street");
    address.setZip(11111);

    return address;
  }
}
