package com.coderhack.codehackapi.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhack.codehackapi.entities.User;
import com.coderhack.codehackapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        Collections.sort(users, (a,b) ->
            b.getScore() - a.getScore()
        );
        return users;
    }

    @Override
    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateUser(String id, User user) {
        Optional<User> res = userRepository.findById(id);
        if(res.isPresent()){
            int temp = user.getScore();
            res.get().setScore(temp);
            HashSet<String> badges = res.get().getBadge();
            if(badges == null){
                badges = new HashSet<>();
            }
            if(temp >= 1 && temp < 30){
                if(!badges.contains("Code Ninja")){
                    badges.add("Code Ninja");
                }
            }
            else if(temp >= 30 && temp < 60){
                if(!badges.contains("Code Champ")){
                    badges.add("Code Champ");
                }
            }
            else if(temp >= 60 && temp <= 100){
                if(!badges.contains("Code Master")){
                    badges.add("Code Master");
                }
            }
            res.get().setBadge(badges);
            userRepository.save(res.get());
            return res;
        }
        return null;
    }

    @Override
    public String removeUser(String id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.delete(user.get());
        return "User removed.";
    }
    
}
