package com.coderhack.codehackapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderhack.codehackapi.entities.User;
import com.coderhack.codehackapi.service.UserServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
  
    @MockBean
    private UserServiceInterface userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void GetUsersTest() throws Exception{
        List<User> users = new ArrayList<>();
        users.add(new User("1","Raj"));
        users.add(new User("2","Abin"));

        Mockito.doReturn(users).when(userService).getAll();

        //when
        URI uri = UriComponentsBuilder.fromPath("/users").build().toUri();
        MockHttpServletResponse response = mockMvc.perform(get(uri).accept("Application/json")).
        andReturn().getResponse();

        //then
        String resString = response.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        User[] userdata= objectMapper.readValue(resString,User[].class);
        List<User> userList = new ArrayList<>();
        for(User val : userdata){
            userList.add(val);
        }

        assertEquals(2, userList.size());
        Mockito.verify(userService,times(1)).getAll();
    }
    @Test
    public void UserFindTest() throws Exception{
        
        Mockito.doReturn(Optional.empty()).when(userService).getUser("1");
        URI uri = UriComponentsBuilder.fromPath("/users/1").build().toUri();
        mockMvc.perform(get(uri)).andExpect(status().isNotFound());
    }
    @Test
    public void UserDeleteTest() throws Exception{
        
        Mockito.doReturn(Optional.empty()).when(userService).getUser("1");
        URI uri = UriComponentsBuilder.fromPath("/users/1").build().toUri();
        mockMvc.perform(get(uri)).andExpect(status().isNotFound());
    }

}
