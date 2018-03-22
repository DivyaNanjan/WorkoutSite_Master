package com.example.WorkoutSite.services;


import com.example.WorkoutSite.model.User;
import com.example.WorkoutSite.model.WorkOut;
import com.example.WorkoutSite.repository.WorkOutRepository;
import com.example.WorkoutSite.service.WorkOutService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class WorkOutServiceTest {
    @InjectMocks
    private WorkOutService workOutService;

    @Mock
    private WorkOutRepository workOutRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    User demoUser = new User(1,"userName","password","userEmailId");
    WorkOut workout= new WorkOut(1, (double)123, "Running", demoUser);

    @Test
    public void createWorkOutTest() {
        when(workOutRepository.save(workout)).thenReturn(workout);
        WorkOut responseWorkout=workOutService.createWorkout(workout);
        assertThat(responseWorkout.equals(workout));
    }

    @Test
    public void getWorkoutListTest() {
        List<WorkOut> workoutList= new ArrayList<WorkOut>();
        workoutList.add(workout);
        when(workOutRepository.getWorkOutList(workout.getUser().getUserId())).thenReturn(workoutList);
        List<WorkOut> responseList= (List<WorkOut>) workOutService.getWorkoutListByUserId(workout.getUser().getUserId());
        assertThat(responseList.equals(workoutList));
    }

    @Test
    public void getWorkOutTest() {
        when(workOutRepository.findOne(workout.getWorkoutId())).thenReturn(workout);
        WorkOut responseWorkout=workOutService.getWorkOutById(workout.getWorkoutId());
        assertThat(responseWorkout.equals(workout));
    }





}
