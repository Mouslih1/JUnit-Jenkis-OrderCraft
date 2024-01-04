package com.exemple.service.impl;

import com.exemple.config.DispatcherServletInit;
import com.exemple.config.PersistenceJPAConfig;
import com.exemple.entity.User;
import com.exemple.service.InterfaceUserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {DispatcherServletInit.class, PersistenceJPAConfig.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Autowired
    private InterfaceUserService interfaceUserService;

    private User testUser;

    @BeforeEach
    void setUp() {
        // Create a new user before each test
        testUser = new User();
        testUser.setName("test");
        testUser.setEmail("test@example.com");
        testUser.setPassword("testpass");
        testUser = interfaceUserService.addUser(testUser);
    }

    @AfterEach
    void tearDown() {

        if (interfaceUserService.getById(testUser.getId()) != null) {
            interfaceUserService.deleteUser(testUser.getId());
        }
    }

    @Test
    void addUser() {
        assertNotNull(testUser);
        assertEquals("test", testUser.getName());
    }

    @Test
    void getUsers() {
        List<User> users = interfaceUserService.getUsers();
        assertNotNull(users);

    }

    @Test
    void deleteUser() {
        interfaceUserService.deleteUser(testUser.getId());
        User deletedUser = interfaceUserService.getById(testUser.getId());
        assertNull(deletedUser);
    }

    @Test
    void getById() {
        User retrievedUser = interfaceUserService.getById(testUser.getId());
        assertNotNull(retrievedUser);
    }

    @Test
    void updateUser() {
        assertNotNull(testUser.getId(), "User ID should not be null after saving");
        testUser.setName("updatedName");

        User updatedUser = interfaceUserService.updateUser(testUser);
        assertNotNull(updatedUser);

        assertEquals("updatedName", updatedUser.getName());
    }

    @Test
    void authenticate() {
        User authenticatedUser = interfaceUserService.authenticate("test@example.com", "testpass");
        assertNotNull(authenticatedUser);
    }

    @Test
    void getByEmail() {
        User retrievedUser = interfaceUserService.getByEmail("test@example.com");
        assertNotNull(retrievedUser, "Retrieved user should not be null");

    }
}
