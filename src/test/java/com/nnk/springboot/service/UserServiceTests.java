package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
    void userServiceTest() {
        User user = new User("testuser", "password", "Test User", "USER");

        // Save
        user = userService.save(user);
        assertNotNull(user.getId());
        assertEquals("testuser", user.getUsername());

        // Update
        user.setUsername("updatedusername");
        user = userService.update(user.getId(), user);
        assertEquals("updatedusername", user.getUsername());

        // Find
        List<User> listResult = userService.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = user.getId();
        userService.delete(id);
        assertThrows(IllegalArgumentException.class, () -> userService.findById(id));
    }
}
