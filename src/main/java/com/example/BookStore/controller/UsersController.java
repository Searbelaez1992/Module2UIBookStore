package com.example.BookStore.controller;


import com.example.BookStore.models.Users;
import com.example.BookStore.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> getUsers() {
        return usersService.getUsers();
    }

    @GetMapping("/{id}")
    public Users getUser(@PathVariable Long id) {

        return usersService.findUsersById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Users saveUsers(@RequestBody Users users) {
        return usersService.save(users);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Users updateProduct(@PathVariable long id, @RequestBody Users users) {
         return usersService.updateUsers(id,users);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id)
    {
        usersService.deleteUsers(id);

        return ResponseEntity.noContent().build();
    }
}
