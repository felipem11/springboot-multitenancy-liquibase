package br.com.felipemartins.springbootmultitenancyliquibase.core.tenant;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 10/01/2022 20:36
 **/
@Configuration
public class TenantConfiguration {

    @Bean
    public FilterRegistrationBean dawsonApiFilter(){
        FilterRegistrationBean<TenantFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new TenantFilter());
        return registration;
    }
}
