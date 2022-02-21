package com.starskvim.socialnetwork;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starskvim.socialnetwork.controller.dto.UserRegistrationDto;
import com.starskvim.socialnetwork.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class LoginTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void accessDeniedTest() throws Exception {
        this.mockMvc.perform(get("/find"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


    @Test
    public void badCredentials() throws Exception {
        this.mockMvc.perform(post("/login").param("username", "test"))
                .andDo(print())
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    public void registrationUser() throws Exception {

        UserRegistrationDto testUserDto = new UserRegistrationDto();
        testUserDto.setLogin("trueTest");
        testUserDto.setMail("test@gmail.com");
        testUserDto.setPassword("testPassword");
        testUserDto.setFirstName("testUser");
        testUserDto.setLastName("testUser");

        String json = new ObjectMapper().writeValueAsString(testUserDto);

        this.mockMvc.perform(post("/registration") .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test@gmail.com")));

    }

    @AfterEach
    public void deleteTestUser(){
        userService.deleteByLogin("trueTest");
    }


}
