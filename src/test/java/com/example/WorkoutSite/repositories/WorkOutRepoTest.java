package com.example.WorkoutSite.repositories;

import com.example.WorkoutSite.model.User;
import com.example.WorkoutSite.model.WorkOut;
import com.example.WorkoutSite.repository.WorkOutRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)

public class WorkOutRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WorkOutRepository workOutRepository;

    User demoUser = new User(1, "password", "userName","userEmailId");

    WorkOut demoWorkout= new WorkOut(1, (double)123, "Cycling", demoUser);

    @Test
    public void findWorkOutByIdTest(){
        entityManager.merge(demoWorkout);
        Iterable<WorkOut> response = workOutRepository.getWorkOutList(1);
        assertNotNull(response);


    }


}
