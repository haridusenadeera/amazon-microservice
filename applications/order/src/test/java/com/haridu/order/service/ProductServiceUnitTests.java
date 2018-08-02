package com.haridu.order.service;

import com.haridu.order.entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProductServiceUnitTests {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private ProductService productService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetNameByProductId() {
    Product productMock = new Product();
    productMock.setName("TestProduct");

    ResponseEntity<Product> responseEntity =
        new ResponseEntity<>(productMock, HttpStatus.OK);

    String url = "//product/products/1";
    when(restTemplate.getForEntity(url, Product.class)).thenReturn(responseEntity);
    String result = productService.getNameByProductId(1).getName();

    assertEquals("TestProduct", result);
  }

  @Test
  public void testGetNameByProductIdEmptyResponse() {
    ResponseEntity<Product> responseEntity =
        new ResponseEntity<>(HttpStatus.OK);

    String url = "//product/products/1";
    when(restTemplate.getForEntity(url, Product.class)).thenReturn(responseEntity);
    Product result = productService.getNameByProductId(1);

    assertNull(result);
  }

  @Test
  public void testFallback() {
    Product product = productService.productFallback(1);
    assertEquals("Product-Name-Placeholder", product.getName());
  }
}
