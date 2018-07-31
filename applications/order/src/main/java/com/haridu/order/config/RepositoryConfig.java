package com.haridu.order.config;

import com.haridu.order.entity.Order;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;


/**
 * Spring Data REST does not expose IDs by default through the REST endpoint.
 * Therefore, customize Order entity to expose it's ID.
 */
@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(Order.class);
  }
}
