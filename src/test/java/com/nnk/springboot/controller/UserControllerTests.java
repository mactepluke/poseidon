package com.nnk.springboot.controller;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTests {

    private static final int ID = 1;
    private User user;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @BeforeAll
    void setUp() {
        user = new User("username", "password", "fullname", "role");
    }

    @Test
    void userEndpointTest() throws Exception {

        when(userService.findById(ID)).thenReturn(user);
        when(userService.update(ID, user)).thenReturn(user);
        when(userService.delete(ID)).thenReturn(user);
        when(userService.delete(ID)).thenReturn(user);
        when(userService.save(user)).thenReturn(user);

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/user/validate"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/user/update/" + ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/update/" + ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/delete/" + ID))
                .andExpect(status().isFound());
    }
}
