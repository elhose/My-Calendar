package com.js.calendar.controller;

import com.js.calendar.dto.UserDTO;
import com.js.calendar.dto.UserUpdateDTO;
import com.js.calendar.entities.User;
import com.js.calendar.mappers.UserMapper;
import com.js.calendar.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private UserMapper mapper;
    private UserService service;

    public UserController(UserMapper mapper, UserService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<UserDTO>> getUsers() {
        Iterable<User> users = service.getEntities();
        List<UserDTO> dtos = new ArrayList<>();

        users.forEach(user -> dtos.add(mapper.mapUserToUserDTO(user)));

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Long userId) {
        Optional<User> user = service.getEntity(userId);

        return user.map(value -> new ResponseEntity<>(mapper.mapUserToUserDTO(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new UserDTO(), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity addNewUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        User user = mapper.mapUserUpdateDtoToUser(userUpdateDTO);
        user.setEnabled(true);
        service.addEntity(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable("userId") Long userId, @RequestBody UserUpdateDTO userUpdateDTO) {
        User user = mapper.mapUserUpdateDtoToUser(userUpdateDTO);
        user.setEnabled(true);
        service.updateEntity(userId, user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Long userId) {
        service.deleteEntity(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
