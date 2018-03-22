package com.example.WorkoutSite.repositories;


import com.example.WorkoutSite.model.User;
import com.example.WorkoutSite.model.WorkOut;
import com.example.WorkoutSite.model.WorkOutTransaction;
import com.example.WorkoutSite.repository.WorkOutTransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class WorkOutTransactionRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WorkOutTransactionRepository workOutTransactionRepository;

    User demoUser = new User(1, "password", "userName","userEmailId");

    WorkOut demoWorkout= new WorkOut(1, (double)123, "Cycling", demoUser);
    WorkOutTransaction demoTransaction = new WorkOutTransaction(1,demoWorkout, LocalDateTime.now(), LocalDateTime.now() );

    @Test
    public void findWorkOutTransactionByIdTest(){
        entityManager.merge(demoTransaction);
        Iterable<WorkOutTransaction> response = workOutTransactionRepository.findTransactionByWorkOutId(1);
        assertNotNull(response);


    }

}
