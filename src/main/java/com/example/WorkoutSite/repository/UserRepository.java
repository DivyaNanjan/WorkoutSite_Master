package com.example.WorkoutSite.repository;

import com.example.WorkoutSite.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT NEW User(US.userId,US.userName,US.password,US.emailId) FROM User US where US.emailId=:emailId")
    User findUserByEmailId(@Param("emailId") String emailId);



}
