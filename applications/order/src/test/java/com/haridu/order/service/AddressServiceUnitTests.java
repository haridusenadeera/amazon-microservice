package com.haridu.order.service;

import com.haridu.order.entity.Address;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AddressServiceUnitTests {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private AddressService addressService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetShippingAddressById() {
    Address addressMock = new Address();
    addressMock.setCity("Chicago");

    ResponseEntity<Address> responseEntity =
        new ResponseEntity<>(addressMock, HttpStatus.OK);

    String url = "//account/addresses/1";
    when(restTemplate.getForEntity(url, Address.class)).thenReturn(responseEntity);
    String result = addressService.getShippingAddressById(1).getCity();

    assertEquals("Chicago", result);
  }

  @Test
  public void testGetShippingAddressByIdEmptyResponse() {
    ResponseEntity<Address> responseEntity =
        new ResponseEntity<>(HttpStatus.OK);

    String url = "//account/addresses/1";
    when(restTemplate.getForEntity(url, Address.class)).thenReturn(responseEntity);
    Address result = addressService.getShippingAddressById(1);

    assertNull(result);
  }

  @Test
  public void testFallback() {
    Address address = addressService.addressFallback(1);
    assertEquals("City-PlaceHolder", address.getCity());
  }

}
