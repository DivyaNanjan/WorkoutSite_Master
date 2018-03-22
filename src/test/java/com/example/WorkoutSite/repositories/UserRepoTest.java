package com.example.WorkoutSite.repositories;


import com.example.WorkoutSite.model.User;
import com.example.WorkoutSite.repository.UserRepository;
import com.example.WorkoutSite.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class UserRepoTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;



    User demoUser = new User(1,"userName", "password", "userEmailId");

    String emailId = "userEmailId";
    String expectedUserName = "userName";


    @Test
    public void findUserByIdTest(){
        entityManager.merge(demoUser);
        User response = (User) userRepository.findUserByEmailId(emailId);
        String userName = response.getUserName();
        assertEquals(userName, expectedUserName);

    }


}
