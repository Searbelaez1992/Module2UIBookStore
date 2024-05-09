package com.example.BookStore.controller;

import com.example.BookStore.models.Users;
import com.example.BookStore.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest(controllers = UsersController.class)
public class UsersControllerTest {

    @MockBean
    private UsersService usersService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Users firstUser;
    private Users secondUser;

    @BeforeEach
    void init(){
        firstUser = new Users();
        firstUser.setId(1l);
        firstUser.setName("simon");
        firstUser.setAddress("Street 55");
        firstUser.setEmail("simon@gmail.com");
        firstUser.setPhone("3109999999");
        firstUser.setRoleId(1l);
        firstUser.setLogin("simon332");
        firstUser.setPassword("Testrre1");

        secondUser = new Users();
        secondUser.setId(2l);
        secondUser.setName("Raul");
        secondUser.setAddress("Street 44");
        secondUser.setEmail("raul@gmail.com");
        secondUser.setPhone("3155555555");
        secondUser.setRoleId(1L);
        secondUser.setLogin("raul65");
        secondUser.setPassword("Test554");
    }

    @Test
    @DisplayName("It Should save the User object to the database")
    void shouldCreateNewUser() throws Exception {

        when(usersService.save(any(Users.class))).thenReturn(firstUser);

        this.mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",is(firstUser.getName())))
                .andExpect(jsonPath("$.address",is(firstUser.getAddress())))
                .andExpect(jsonPath("$.email",is(firstUser.getEmail())))
                .andExpect(jsonPath("$.phone",is(firstUser.getPhone())))
                .andExpect(jsonPath("$.login",is(firstUser.getLogin())))
                .andExpect(jsonPath("$.password",is(firstUser.getPassword())));

    }

    @Test
    @DisplayName("It Should Return all the Users object form the database")
    void ShouldFetchAllUsers() throws Exception {

        List<Users> list = new ArrayList<>();
        list.add(firstUser);
        list.add(secondUser);

        when(usersService.getUsers()).thenReturn(list);

        this.mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(list.size())));
    }

    @Test
    @DisplayName("It Should Return one Users object by id from the database")
    void ShouldFetchOneUserById() throws Exception {

        when(usersService.findUsersById(anyLong())).thenReturn(firstUser);

        this.mockMvc.perform(get("/api/users/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(firstUser.getName())))
                .andExpect(jsonPath("$.address",is(firstUser.getAddress())))
                .andExpect(jsonPath("$.email",is(firstUser.getEmail())))
                .andExpect(jsonPath("$.phone",is(firstUser.getPhone())))
                .andExpect(jsonPath("$.login",is(firstUser.getLogin())))
                .andExpect(jsonPath("$.password",is(firstUser.getPassword())));

    }

    @Test
    @DisplayName("It Should delete a Users object from the database")
    void ShouldDeleteUser() throws Exception {

        doNothing().when(usersService).deleteUsers(anyLong());

        this.mockMvc.perform(delete("/api/users/{id}",1l))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("It Should update a Users from the database")
    void ShouldUpdateProduct() throws Exception {

        when(usersService.updateUsers(anyLong(),any(Users.class))).thenReturn(firstUser);

        this.mockMvc.perform(post("/api/users/{id}",1l)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(firstUser.getName())))
                .andExpect(jsonPath("$.address",is(firstUser.getAddress())))
                .andExpect(jsonPath("$.email",is(firstUser.getEmail())))
                .andExpect(jsonPath("$.phone",is(firstUser.getPhone())))
                .andExpect(jsonPath("$.login",is(firstUser.getLogin())))
                .andExpect(jsonPath("$.password",is(firstUser.getPassword())));
    }


}
