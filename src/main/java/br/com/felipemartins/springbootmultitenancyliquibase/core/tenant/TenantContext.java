package br.com.felipemartins.springbootmultitenancyliquibase.core.tenant;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 10/01/2022 00:50
 **/
public class TenantContext {

    private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

    static String getCurrentTenant() {
        return currentTenant.get();
    }

    static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    static void clear() {
        currentTenant.remove();
    }
}
