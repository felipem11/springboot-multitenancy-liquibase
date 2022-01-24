package br.com.felipemartins.springbootmultitenancyliquibase.product;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 12/01/2022 01:55
 **/
interface ProductRepository extends JpaRepository<Product, Long> {
}
