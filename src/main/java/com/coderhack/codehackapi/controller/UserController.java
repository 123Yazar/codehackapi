package com.coderhack.codehackapi.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.coderhack.codehackapi.entities.User;
import com.coderhack.codehackapi.service.UserServiceInterface;

import jakarta.validation.Valid;

@Validated
@RestController
public class UserController {

    @Autowired
    private UserServiceInterface userService;
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> data = userService.getAll();
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id){
        Optional<User> data = userService.getUser(id);
        if(data.isPresent()){
            return ResponseEntity.ok().body(data.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found");
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        if(user.getId().isEmpty() || user.getUsername().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User id and username required.");
        }
        Optional<User> data = userService.getUser(user.getId());
        if(data.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User id already exist.");
        }else{
            User res = userService.addUser(user);
            return ResponseEntity.ok().body(res);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id,@RequestBody User user){
        Optional<User> data = userService.getUser(id);
        if(data.isPresent()){
            if(user.getScore() > 0 && user.getScore() <= 100){
                Optional<User> res = userService.updateUser(id,user);
                return ResponseEntity.ok().body(res.get());
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Score should be between 1 - 100");  
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found");
        }
        
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> removeUser(@PathVariable("id") String id){
        Optional<User> user = userService.getUser(id); 
        if(user.isPresent()){
            return ResponseEntity.ok().body(userService.removeUser(id));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found.");
        }
    }
    
}
