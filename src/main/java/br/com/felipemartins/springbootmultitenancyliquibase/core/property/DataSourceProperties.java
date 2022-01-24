package br.com.felipemartins.springbootmultitenancyliquibase.core.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 10/01/2022 19:18
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring")
public class DataSourceProperties {

    private List<DataSourceProperty> dataSources = new ArrayList<>();
}
