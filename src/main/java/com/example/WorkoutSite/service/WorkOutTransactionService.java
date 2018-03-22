package com.example.WorkoutSite.service;

import com.example.WorkoutSite.model.WorkOutTransaction;
import com.example.WorkoutSite.repository.WorkOutTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkOutTransactionService {

    @Autowired
    private WorkOutTransactionRepository workOutTransactionRepo;

    public WorkOutTransaction defineWorkOutTransaction(WorkOutTransaction workOutTransaction) {
        WorkOutTransaction enteredWorkOutTransaction = workOutTransactionRepo.save(workOutTransaction);
        return enteredWorkOutTransaction;
    }

    public Iterable<WorkOutTransaction> getWorkOutTransactionList(Integer workOutId){
        Iterable<WorkOutTransaction> workOutTransactionList = workOutTransactionRepo.findTransactionByWorkOutId(workOutId);
        return workOutTransactionList;
    }

    public WorkOutTransaction updateWorkOutTransaction(WorkOutTransaction workOutTransaction,Integer workOutTransactionId){
        WorkOutTransaction findWorkOutTransaction = workOutTransactionRepo.findOne(workOutTransactionId);
        findWorkOutTransaction.setCalsBurnt(workOutTransaction.getCalsBurnt());
        findWorkOutTransaction.setDuration(workOutTransaction.getDuration());
        findWorkOutTransaction.setStartTime(workOutTransaction.getStartTime());
        findWorkOutTransaction.setEndTime(workOutTransaction.getEndTime());
        findWorkOutTransaction.setWorkOut(workOutTransaction.getWorkOut());
        return defineWorkOutTransaction(findWorkOutTransaction);
    }

    public WorkOutTransaction getWorkOutTransactionById(Integer workoutTransactionId){
        return workOutTransactionRepo.findOne(workoutTransactionId);
    }

    public void deleteWorkOutTransactionById(Integer workoutTransactionId){
        workOutTransactionRepo.delete(workoutTransactionId);
    }
}
