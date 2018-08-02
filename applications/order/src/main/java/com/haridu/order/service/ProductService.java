package com.haridu.order.service;

import com.haridu.order.entity.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "productFallback")
  public Product getNameByProductId(long productId) {

    String url = "//product/products/" + productId;

    return restTemplate.getForEntity(url, Product.class).getBody();
  }

  public Product productFallback(long productId, Throwable throwable) {
    Product product = new Product();
    product.setName("Product-Name-Placeholder");
    return product;
  }
}
