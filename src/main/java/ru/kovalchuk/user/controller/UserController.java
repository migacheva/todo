package ru.kovalchuk.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.kovalchuk.user.dao.UserService;
import ru.kovalchuk.user.model.AddUserRequest;
import ru.kovalchuk.user.model.DeleteUserRequest;
import ru.kovalchuk.user.model.User;

import javax.validation.Valid;
import java.util.List;

@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("account")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@Valid @RequestBody AddUserRequest request) {
        userService.saveUser(request);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteUser(@RequestBody DeleteUserRequest request){
        userService.deleteUser(request.getId());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<User> getAllUsers(){
        return userService.allUsers();
    }
}