package br.com.felipemartins.springbootmultitenancyliquibase.core.config;

import br.com.felipemartins.springbootmultitenancyliquibase.core.bean.MultiTenantDataSourceSpringLiquibase;
import br.com.felipemartins.springbootmultitenancyliquibase.core.property.DataSourceProperties;
import liquibase.integration.spring.SpringLiquibase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 11/01/2022 04:34
 **/
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "spring.liquibase", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(LiquibaseProperties.class)
@AllArgsConstructor
public class LiquibaseConfiguration {

    private LiquibaseProperties properties;
    private DataSourceProperties dataSourceProperties;
    private final Environment env;

    @Bean
    @DependsOn("tenantRoutingDataSource")
    public MultiTenantDataSourceSpringLiquibase liquibaseMultiTenancy(Map<Object, Object> dataSources,
                                                                      @Qualifier("taskExecutor") TaskExecutor taskExecutor) {
        // to run changeSets of the liquibase asynchronous
        MultiTenantDataSourceSpringLiquibase liquibase = new MultiTenantDataSourceSpringLiquibase(taskExecutor);
        dataSources.forEach((tenant, dataSource) -> liquibase.addDataSource((String) tenant, (DataSource) dataSource));
        dataSourceProperties.getDataSources().forEach(dbProperty -> {
            if (dbProperty.getLiquibase() != null) {
                liquibase.addLiquibaseProperties(dbProperty.getTenantId(), dbProperty.getLiquibase());
            }
        });

        liquibase.setContexts(properties.getContexts());
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        return liquibase;
    }

    @Bean
    public SpringLiquibase liquibase(@Qualifier("taskExecutor") TaskExecutor taskExecutor,
                                     DataSource dataSource, LiquibaseProperties liquibaseProperties) {

        // Use liquibase.integration.spring.SpringLiquibase if you don't want Liquibase to start asynchronously
        SpringLiquibase liquibase = new AsyncSpringLiquibase(taskExecutor, env);
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/master/changelog/db.changelog-master.yaml");
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setChangeLogParameters(liquibaseProperties.getParameters());
        if (env.acceptsProfiles("SPRING_PROFILE_NO_LIQUIBASE")) {
            liquibase.setShouldRun(false);
        } else {
            liquibase.setShouldRun(liquibaseProperties.isEnabled());
            log.debug("Configuring Liquibase");
        }
        return liquibase;
    }

}
