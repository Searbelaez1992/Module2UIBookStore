package com.example.BookStore.service;

import com.example.BookStore.models.Users;
import com.example.BookStore.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {

    @InjectMocks
    private UsersService usersService;

    @Mock
    private UsersRepository usersRepository;

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
        firstUser.setRoleId(1);
        firstUser.setLogin("simon332");
        firstUser.setPassword("Testrre1");

        secondUser = new Users();
        secondUser.setId(2l);
        secondUser.setName("Raul");
        secondUser.setAddress("Street 44");
        secondUser.setEmail("raul@gmail.com");
        secondUser.setPhone("3155555555");
        secondUser.setRoleId(1);
        secondUser.setLogin("raul65");
        secondUser.setPassword("Test554");
    }

    @Test
    @DisplayName("It Should save the User object to the database")
    void save(){

        when(usersRepository.save(any(Users.class))).thenReturn(firstUser);

        Users newUser = usersService.save(firstUser);

        assertNotNull(newUser);
        assertThat(newUser.getName()).isEqualTo("simon");
    }

    @Test
    @DisplayName("It Should return list of User with size 2")
    void getUsers(){

        List<Users> list = new ArrayList<>();
        list.add(firstUser);
        list.add(secondUser);

        when(usersRepository.findAll()).thenReturn(list);

        List<Users> users = usersService.getUsers();

        assertEquals(2,users.size());
        assertNotNull(users);
    }

    @Test
    @DisplayName("It Should return  the User object")
    void findUserById() {

        when(usersRepository.findById(anyLong())).thenReturn(Optional.of(firstUser));

        Users existingUser = usersService.findUsersById(1l);

        assertNotNull(existingUser);
        assertThat(existingUser.getId()).isEqualTo(1l);
    }

    @Test
    @DisplayName("It Should throw a Exception")
    void findUserByIdForException() {

        assertThrows(RuntimeException.class,() -> {
            usersService.findUsersById(2l);
        });
    }

    @Test
    @DisplayName("It Should update a User into the database")
    void updateUser() {

        when(usersRepository.findById(anyLong())).thenReturn(Optional.of(firstUser));
        when(usersRepository.save(any(Users.class))).thenReturn(firstUser);
        firstUser.setAddress("Street 88");

        usersService.updateUsers(1L,firstUser);

        Users updatedUser = usersService.findUsersById(1l);

        assertNotNull(updatedUser);
        assertEquals("Street 88", updatedUser.getAddress());
    }

    @Test
    @DisplayName("It Should delete a User from the database")
    void deleteUser() {

        when(usersRepository.findById(anyLong())).thenReturn(Optional.of(firstUser));
        doNothing().when(usersRepository).delete(any(Users.class));

        usersService.deleteUsers(1l);

        verify(usersRepository,times(1)).delete(firstUser);
    }
}
