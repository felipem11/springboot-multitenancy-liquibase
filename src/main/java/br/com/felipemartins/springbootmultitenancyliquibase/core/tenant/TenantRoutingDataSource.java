package br.com.felipemartins.springbootmultitenancyliquibase.core.tenant;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 10/01/2022 19:21
 **/
public class TenantRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getCurrentTenant();
    }
}
