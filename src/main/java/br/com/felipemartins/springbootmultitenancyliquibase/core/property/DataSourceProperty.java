package br.com.felipemartins.springbootmultitenancyliquibase.core.property;

import lombok.Data;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 10/01/2022 19:19
 **/
@Data
public class DataSourceProperty {

    private String tenantId;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private LiquibaseProperties liquibase;
}