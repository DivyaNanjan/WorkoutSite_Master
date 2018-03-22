package com.example.WorkoutSite.controller;


import com.example.WorkoutSite.Util.ErrorText;
import com.example.WorkoutSite.WorkoutSiteApplication;
import com.example.WorkoutSite.model.User;
import com.example.WorkoutSite.service.UserService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WorkoutSiteApplication.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
        MockitoAnnotations.initMocks(this);
    }


    User demoUser = new User(1,"userName","password","userEmailId");

    ErrorText registerErrorText = new ErrorText("User registered successfully","Success");
    ErrorText loginErrorText = new ErrorText("User logged in successfully","Success");



    @Test
    public void userRegisterTest() throws Exception{
        String content = new Gson().toJson(demoUser);
        given(userService.registerUser(Mockito.any(User.class))).willReturn(registerErrorText);
        ResultActions result = mockMvc
                .perform(post("/user/register").content(content).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
        assertThat(result.andReturn().equals(registerErrorText));
    }

    @Test
    public void userLoginTest() throws Exception{
        given(userService.authenticate("userEmailId","password")).willReturn(loginErrorText);
        ResultActions result = mockMvc
                .perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).header("emailId", "divya.n4@cognizant.com")
                        .header("password", "password"))
                .andExpect(status().isOk());
        assertThat(result.andReturn().equals(loginErrorText));

    }






}
