package com.coderhack.codehackapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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
import com.coderhack.codehackapi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void UserSortingTest() throws Exception{
        List<User> users = new ArrayList<>();

        users.add(new User("1","Amal"));
        users.add(new User("2","Abin"));
        users.get(0).setScore(60);
        users.get(1).setScore(80);

        //when
        Mockito.doReturn(users).when(userRepository).findAll();

        //then
        URI uri = UriComponentsBuilder.fromUriString("/users").build().toUri();
        MockHttpServletResponse response = mockMvc.perform(get(uri).accept("Application/json")).andReturn().getResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        String respString = response.getContentAsString();
        User[] userList = objectMapper.readValue(respString, User[].class);

        List<User> usersListFromService = new ArrayList<>();
        for(User val : userList){
            usersListFromService.add(val);
        }

        assertEquals("2", usersListFromService.get(0).getId());

        Mockito.verify(userRepository,times(1)).findAll();
    }

}
