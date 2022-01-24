package br.com.felipemartins.springbootmultitenancyliquibase.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 12/01/2022 02:02
 **/
@Data
@Entity
@Table(name = "USER_TB")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;
}
