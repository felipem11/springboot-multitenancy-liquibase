package br.com.felipemartins.springbootmultitenancyliquibase.product.web;

import br.com.felipemartins.springbootmultitenancyliquibase.product.Product;
import br.com.felipemartins.springbootmultitenancyliquibase.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 12/01/2022 01:57
 **/
@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return this.productService.save(product);
    }

    @GetMapping
    public Page<Product> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                 @RequestParam(defaultValue = "10", required = false) Integer size) {
        return this.productService.findAll(PageRequest.of(page, size));
    }
}
