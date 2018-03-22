package com.example.WorkoutSite.services;

import com.example.WorkoutSite.Util.ErrorText;
import com.example.WorkoutSite.model.User;
import com.example.WorkoutSite.repository.UserRepository;
import com.example.WorkoutSite.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    User demoUser = new User(1,"userName","password","userEmailId");

    ErrorText errorTextRegister = new ErrorText("User registered successfully","Success");

    ErrorText errorTextAuthenticate = new ErrorText("User logged in successfully","Success");

    @Test
    public void createUserTest(){
        when(userRepository.save(demoUser)).thenReturn(demoUser);
        User response=userService.createUser(demoUser);
        assertThat(response.equals(demoUser));
    }

    @Test
    public void registerUserTest(){
        when(userRepository.findUserByEmailId(demoUser.getEmailId())).thenReturn(demoUser);
        ErrorText errorTextdemo=userService.registerUser(demoUser);
        assertThat(errorTextdemo.equals(errorTextRegister));
    }

    @Test
    public void authenticateUserTest(){
        when(userRepository.findUserByEmailId(demoUser.getEmailId())).thenReturn(demoUser);
        ErrorText errorTextdemo=userService.registerUser(demoUser);
        assertThat(errorTextdemo.equals(errorTextAuthenticate));
    }




}
