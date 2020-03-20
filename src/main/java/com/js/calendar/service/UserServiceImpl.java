package com.js.calendar.service;

import com.js.calendar.entities.User;
import com.js.calendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(Integer id, User user) {
        Optional<User> foundUser = userRepository.findById(id);

        if (foundUser.isPresent()){
            user.setId(foundUser.get().getId());
        }

        userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(Integer id) {
        return  userRepository.findById(id);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
