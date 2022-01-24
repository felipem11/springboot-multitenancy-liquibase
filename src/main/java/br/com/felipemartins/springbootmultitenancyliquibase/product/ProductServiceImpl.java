package br.com.felipemartins.springbootmultitenancyliquibase.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 12/01/2022 01:55
 **/
@Service
@AllArgsConstructor
class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public Page<Product> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }
}
