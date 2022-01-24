package br.com.felipemartins.springbootmultitenancyliquibase.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 12/01/2022 01:58
 **/
public interface ProductService {

    Product save(Product product);

    Page<Product> findAll(Pageable pageable);
}
