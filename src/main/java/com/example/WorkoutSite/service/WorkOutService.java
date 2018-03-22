package com.example.WorkoutSite.service;

import com.example.WorkoutSite.model.WorkOut;
import com.example.WorkoutSite.repository.WorkOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkOutService {

    @Autowired
    private WorkOutRepository workOutRepo;

    public WorkOut createWorkout(WorkOut workOut){
        workOutRepo.save(workOut);
        return workOut;
    }

    public Iterable<WorkOut> getWorkoutListByUserId(Integer userId){
        Iterable<WorkOut> workOutList = workOutRepo.getWorkOutList(userId);
        return workOutList;
    }

    public WorkOut updateWorkOut(WorkOut workOut,Integer workoutId)
    {
        WorkOut findWorkOut = workOutRepo.findOne(workoutId);
        findWorkOut.setCalBurntPerUnitTime(workOut.getCalBurntPerUnitTime());
        findWorkOut.setTitle(workOut.getTitle());
        findWorkOut.setUnitTime(workOut.getUnitTime());
        findWorkOut.setUser(workOut.getUser());
        return createWorkout(findWorkOut);
    }


    public void deleteWorkOut(Integer workoutId)
    {
        workOutRepo.delete(workoutId);
    }
    public WorkOut getWorkOutById(Integer workoutId){
        return workOutRepo.findOne(workoutId);
    }
}
