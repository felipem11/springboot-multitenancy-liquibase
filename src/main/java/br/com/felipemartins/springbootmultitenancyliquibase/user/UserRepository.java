package br.com.felipemartins.springbootmultitenancyliquibase.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 12/01/2022 02:03
 **/
interface UserRepository extends JpaRepository<User, Long> {
}
