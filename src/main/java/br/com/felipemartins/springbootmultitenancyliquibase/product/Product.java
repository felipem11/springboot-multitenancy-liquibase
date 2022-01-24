package br.com.felipemartins.springbootmultitenancyliquibase.product;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 12/01/2022 01:52
 **/
@Data
@Entity
@Table(name = "PRODUCT_TB")
public class Product implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Column(name = "VALUE", nullable = false)
    private BigDecimal value;
}
