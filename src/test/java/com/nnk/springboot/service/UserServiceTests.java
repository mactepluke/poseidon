package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserTests {

    @Autowired
    private IUserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void userServiceTest() {
        User user = new User("testuser", "password", "Test User", "USER");

        // Save
        user = userService.save(user);
        assertNotNull(user.getId());
        assertEquals("testuser", user.getUsername());

        // Update
        user.setUsername("updatedusername");
        user = userService.save(user);
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
