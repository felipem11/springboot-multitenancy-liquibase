package br.com.felipemartins.springbootmultitenancyliquibase.core.config;

import br.com.felipemartins.springbootmultitenancyliquibase.core.property.DataSourceProperties;
import br.com.felipemartins.springbootmultitenancyliquibase.core.tenant.TenantRoutingDataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 10/01/2022 19:23
 **/
@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = { "br.com.felipemartins.springbootmultitenancyliquibase" })
@EnableJpaRepositories(basePackages = { "br.com.felipemartins.springbootmultitenancyliquibase" })
public class DataSourceConfiguration {

    @Bean(name = "dataSources")
    @Primary
    public Map<Object, Object> getDataSources(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.getDataSources().stream().map(dataSourceProperty -> {
            DataSource dataSource = DataSourceBuilder.create()
                    .url(dataSourceProperty.getUrl())
                    .username(dataSourceProperty.getUsername())
                    .password(dataSourceProperty.getPassword())
                    .driverClassName(dataSourceProperty.getDriverClassName())
                    .build();
            return new TenantIdDataSource(dataSourceProperty.getTenantId(), dataSource);
        }).collect(Collectors.toMap(TenantIdDataSource::getTenantId, TenantIdDataSource::getDataSource));
    }

    @Bean(name = "tenantRoutingDataSource")
    @DependsOn("dataSources")
    public DataSource dataSource(Map<Object, Object> dataSources) {
        AbstractRoutingDataSource tenantRoutingDataSource = new TenantRoutingDataSource();
        tenantRoutingDataSource.setTargetDataSources(dataSources);
        tenantRoutingDataSource.setDefaultTargetDataSource(dataSources.get("db1"));
        tenantRoutingDataSource.afterPropertiesSet();
        return tenantRoutingDataSource;
    }

    @Data
    @AllArgsConstructor
    private class TenantIdDataSource {
        private Object tenantId;
        private Object dataSource;
    }
}
