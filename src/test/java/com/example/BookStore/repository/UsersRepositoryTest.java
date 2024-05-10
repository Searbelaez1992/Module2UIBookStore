package com.example.BookStore.repository;

import com.example.BookStore.models.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    private Users firstUser;
    private Users secondUser;

    @BeforeEach
    void init(){
        firstUser = new Users();
        firstUser.setName("simon");
        firstUser.setAddress("Street 55");
        firstUser.setEmail("simon@gmail.com");
        firstUser.setPhone("3109999999");
        firstUser.setRoleId(1);
        firstUser.setLogin("simon332");
        firstUser.setPassword("Testrre1");

        secondUser = new Users();
        secondUser.setName("Raul");
        secondUser.setAddress("Street 44");
        secondUser.setEmail("raul@gmail.com");
        secondUser.setPhone("3155555555");
        secondUser.setRoleId(1);
        secondUser.setLogin("raul65");
        secondUser.setPassword("Test554");
    }

    @Test
    @DisplayName("It Should save the user to the database")
    void save(){

        Users newUser = usersRepository.save(firstUser);
        //Assert
        assertNotNull(newUser);
        assertThat(newUser.getId()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("It should return the Users list with size of 5")
    void getAllUsers(){

        usersRepository.save(firstUser);
        usersRepository.save(secondUser);

        List<Users> list = usersRepository.findAll();

        assertNotNull(list);
        assertThat(list).isNotNull();
        assertEquals(5,list.size());
    }

    @Test
    @DisplayName("It should return the user by its id")
    void getUserById(){

        usersRepository.save(firstUser);

        Users existingProduct = usersRepository.findById(firstUser.getId()).get();

        assertNotNull(existingProduct);
        assertEquals("simon",existingProduct.getName());
    }

    @Test
    @DisplayName("It should updated the User with the phone 3213333333")
    void updateUser(){

        usersRepository.save(firstUser);

        Users existingUser = usersRepository.findById(firstUser.getId()).get();
        existingUser.setPhone("3213333333");

        Users newUser = usersRepository.save(existingUser);

        assertEquals("3213333333",newUser.getPhone());
        assertEquals("simon",newUser.getName());
    }

    @Test
    @DisplayName("It should delete the existing User")
    void deleteUser(){


        usersRepository.save(firstUser);
        long id = firstUser.getId();

        usersRepository.save(secondUser);

        usersRepository.delete(firstUser);
        Optional<Users> existingUser = usersRepository.findById(id);
        List<Users> list = usersRepository.findAll();

        assertEquals(4,list.size());
        assertThat(existingUser).isEmpty();

    }
}
