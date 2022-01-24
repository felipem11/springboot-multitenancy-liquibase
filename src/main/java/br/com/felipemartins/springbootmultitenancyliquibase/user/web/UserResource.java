package br.com.felipemartins.springbootmultitenancyliquibase.user.web;

import br.com.felipemartins.springbootmultitenancyliquibase.user.User;
import br.com.felipemartins.springbootmultitenancyliquibase.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="felipemartins.github.io" target="_blank">Felipe Martins</a>
 * @date 12/01/2022 02:06
 **/
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping
    public User save(@RequestBody User user) {
        return this.userService.save(user);
    }

    @GetMapping
    public Page<User> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                              @RequestParam(defaultValue = "10", required = false) Integer size) {
        return this.userService.findAll(PageRequest.of(page, size));
    }
}
