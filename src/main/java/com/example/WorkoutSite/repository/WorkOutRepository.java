package com.example.WorkoutSite.repository;

import com.example.WorkoutSite.model.WorkOut;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WorkOutRepository extends CrudRepository<WorkOut , Integer> {

    @Query("SELECT WO.workoutId,WO.title,WO.calBurntPerUnitTime,WO.unitTime,WO.user.userId,WO.user.userName,WO.user.password,WO.user.emailId FROM WorkOut WO WHERE WO.user.userId = :userId")
    Iterable<WorkOut> getWorkOutList(@Param("userId") Integer userId);
}
