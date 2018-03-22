package com.example.WorkoutSite.controller;

import com.example.WorkoutSite.model.WorkOutTransaction;
import com.example.WorkoutSite.service.WorkOutTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import java.time.Duration;

@RestController
@RequestMapping("/workOutTransaction")
public class WorkOutTransactionController {

    @Autowired
    private WorkOutTransactionService workOutTransactionService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<WorkOutTransaction> saveTransaction(@RequestBody WorkOutTransaction workOutTransaction){
        workOutTransaction.setDuration(Duration.between(workOutTransaction.getStartTime(),workOutTransaction.getEndTime()));
        workOutTransaction.setCalsBurnt(caloriesBurnt(workOutTransaction.getDuration(),workOutTransaction.getWorkOut().getCalBurntPerUnitTime()));
        WorkOutTransaction savedTransaction = workOutTransactionService.defineWorkOutTransaction(workOutTransaction);
        return new ResponseEntity<WorkOutTransaction>(savedTransaction, HttpStatus.OK);
    }

    public Double caloriesBurnt(Duration duration, Double calsBurntPerUnitTime){
        long nanos = (long) duration.toNanos();
        Double calBurnt = (calsBurntPerUnitTime) * (NANOSECONDS.toSeconds(nanos));
        return calBurnt;
    }

    @RequestMapping(method = RequestMethod.GET, value="ofWorkout/{workOutId}")
    public ResponseEntity<Iterable<WorkOutTransaction>> getTransactions(@PathVariable Integer workOutId) {
        Iterable<WorkOutTransaction> savedTransactionList = workOutTransactionService.getWorkOutTransactionList(workOutId);
        return new ResponseEntity<Iterable<WorkOutTransaction>>(savedTransactionList, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value="/{transactionId}")
    public ResponseEntity<WorkOutTransaction> getTransactionsById(@PathVariable Integer transactionId) {
        WorkOutTransaction responseTransaction = workOutTransactionService.getWorkOutTransactionById(transactionId);
        return new ResponseEntity<WorkOutTransaction>(responseTransaction, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.PUT, value="/{transactionId}")
    public ResponseEntity<WorkOutTransaction> updateTransaction(@RequestBody WorkOutTransaction workOutTransaction,@PathVariable Integer transactionId) {
        workOutTransaction.setDuration(Duration.between(workOutTransaction.getStartTime(),workOutTransaction.getEndTime()));
        workOutTransaction.setCalsBurnt(caloriesBurnt(workOutTransaction.getDuration(),workOutTransaction.getWorkOut().getCalBurntPerUnitTime()));
        WorkOutTransaction savedTransaction = workOutTransactionService.updateWorkOutTransaction(workOutTransaction,transactionId);
        return new ResponseEntity<WorkOutTransaction>(savedTransaction, HttpStatus.OK);

    }
    @RequestMapping(method = RequestMethod.DELETE , value="/{transactionId}")
    public ResponseEntity<String> deleteWorkout(@PathVariable Integer transactionId){
        workOutTransactionService.deleteWorkOutTransactionById(transactionId);
        return new ResponseEntity<String>("Success",HttpStatus.OK);
    }



}
