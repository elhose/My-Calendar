package com.js.calendar.service;

import com.js.calendar.entities.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> getUsers();
    void addUser(User user);
    void updateUser(Long id, User user);
    Optional<User> getUser(Long id);
    void deleteUser(Long id);
}
