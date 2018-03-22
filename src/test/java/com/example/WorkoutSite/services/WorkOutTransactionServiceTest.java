package com.example.WorkoutSite.services;

import com.example.WorkoutSite.model.User;
import com.example.WorkoutSite.model.WorkOut;
import com.example.WorkoutSite.model.WorkOutTransaction;
import com.example.WorkoutSite.repository.WorkOutTransactionRepository;
import com.example.WorkoutSite.service.WorkOutTransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class WorkOutTransactionServiceTest {

    @InjectMocks
    private WorkOutTransactionService workOutTransactionService;

    @Mock
    private WorkOutTransactionRepository workOutTransactionRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    User demoUser = new User(1,"userName","password","userEmailId");
    WorkOut workout= new WorkOut(1, (double)123, "Running", demoUser);
    WorkOutTransaction workoutTxnResponse= new WorkOutTransaction(1,workout, LocalDateTime.now(), LocalDateTime.now());
    WorkOutTransaction workoutTxnInput= new WorkOutTransaction(1,workout,LocalDateTime.now(), LocalDateTime.now());

    @Test
    public void defineWorkoutTest() {
        when(workOutTransactionRepository.save(workoutTxnInput)).thenReturn(workoutTxnResponse);
        WorkOutTransaction responseWorkout=workOutTransactionService.defineWorkOutTransaction(workoutTxnInput);
        assertThat(responseWorkout.equals(workoutTxnResponse));
    }

    @Test
    public void getWorkoutTransactionListTest() {
        List<WorkOutTransaction> workoutTransactionList= new ArrayList<WorkOutTransaction>();
        workoutTransactionList.add(workoutTxnResponse);
        when(workOutTransactionRepository.findTransactionByWorkOutId(workoutTxnInput.getWorkOut().getWorkoutId())).thenReturn(workoutTransactionList);
        List<WorkOutTransaction> responseList= (List<WorkOutTransaction>) workOutTransactionService.getWorkOutTransactionList(workoutTxnInput.getWorkOut().getWorkoutId());
        assertThat(responseList.equals(responseList));
    }

    @Test
    public void getWorkoutTransactionTest() {
        when(workOutTransactionRepository.findOne(workoutTxnInput.getTransaction_Id())).thenReturn(workoutTxnResponse);
        WorkOutTransaction responseWorkout=workOutTransactionService.getWorkOutTransactionById(workoutTxnInput.getTransaction_Id());
        assertThat(responseWorkout.equals(workoutTxnResponse));
    }


}
