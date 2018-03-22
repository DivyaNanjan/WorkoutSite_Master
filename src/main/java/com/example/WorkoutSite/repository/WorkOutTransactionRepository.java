package com.example.WorkoutSite.repository;

import com.example.WorkoutSite.model.WorkOutTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WorkOutTransactionRepository extends CrudRepository<WorkOutTransaction , Integer> {

    @Query("SELECT WOT.transactionId,WOT.workOut.title,WOT.startTime,WOT.endTime,WOT.duration,WOT.calsBurnt FROM WorkOutTransaction WOT WHERE WOT.workOut.workoutId = :workOutId")
    Iterable<WorkOutTransaction> findTransactionByWorkOutId(@Param("workOutId") Integer workOutId);
}
