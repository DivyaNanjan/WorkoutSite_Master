package com.example.WorkoutSite.controller;

import com.example.WorkoutSite.model.WorkOut;
import com.example.WorkoutSite.service.WorkOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workout")
@CrossOrigin
public class WorkOutController {

    @Autowired
    private WorkOutService workOutService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<WorkOut> createWorkOut(@RequestBody WorkOut workOut){
        WorkOut newWorkOut = workOutService.createWorkout(workOut);
        return new ResponseEntity<WorkOut>(newWorkOut, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value="ofUser/{userId}")
    public ResponseEntity<Iterable<WorkOut>> getWorkOutList(@PathVariable Integer userId)
    {
        Iterable<WorkOut> workOutList = workOutService.getWorkoutListByUserId(userId);
        return new ResponseEntity<Iterable<WorkOut>>(workOutList, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value="/{workoutId}")
    public ResponseEntity<WorkOut> getWorkOutById(@PathVariable Integer workoutId)
    {
        WorkOut workOut = workOutService.getWorkOutById(workoutId);
        return new ResponseEntity<WorkOut>(workOut, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.PUT, value="/{workoutId}")
    public ResponseEntity<WorkOut> updateWorkOut(@RequestBody WorkOut workOut, @PathVariable Integer workoutId)
    {
        WorkOut responseWorkout = workOutService.updateWorkOut(workOut,workoutId);
        return new ResponseEntity<WorkOut>(responseWorkout, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE , value="/{workoutId}")
    public ResponseEntity<String> deleteWorkout(@PathVariable Integer workoutId){
        workOutService.deleteWorkOut(workoutId);
        return new ResponseEntity<String>("Success",HttpStatus.OK);
    }




}
