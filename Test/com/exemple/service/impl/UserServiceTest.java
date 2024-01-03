package com.exemple.service.impl;

import com.exemple.config.PersistenceJPAConfig;
import com.exemple.entity.User;
import com.exemple.service.InterfaceUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {PersistenceJPAConfig.class})
@ExtendWith(SpringExtension.class)

class UserServiceTest {

    @Autowired
    private InterfaceUserService interfaceUserService;

    @Test
    void addUser() {

        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setEmail("admin@admin.com");
        user.setPassword("admin");

        User usertest =interfaceUserService.addUser(user);
        assertNotNull(usertest);
        assertEquals("test", user.getName());

    }

    @Test
    void getUsers() {
        List<User> users = interfaceUserService.getUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {

        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setEmail("admin@admin.com");
        user.setPassword("admin");

        interfaceUserService.addUser(user);

        interfaceUserService.deleteUser(1L);

        User deletedUser = interfaceUserService.getById(1L);
        assertNull(deletedUser);
    }

    @Test
    void getById() {
        User retrievedUser = interfaceUserService.getById(1L);
        assertNotNull(retrievedUser);
    }

    @Test
    void authenticate() {

        User authenticatedUser = interfaceUserService.authenticate("admin@admin.com", "123456789");
        assertNotNull(authenticatedUser);
    }

    @Test
    void getByEmail() {
        User retrievedUser = interfaceUserService.getByEmail("admin@admin.com");
        assertNotNull(retrievedUser);

    }
}