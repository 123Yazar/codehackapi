package com.coderhack.codehackapi.service;

import java.util.List;
import java.util.Optional;

import com.coderhack.codehackapi.entities.User;

public interface UserServiceInterface {
    List<User> getAll();
    Optional<User> getUser(String id);
    User addUser(User user);
    Optional<User> updateUser(String id,User user);
    String removeUser(String id);

}
