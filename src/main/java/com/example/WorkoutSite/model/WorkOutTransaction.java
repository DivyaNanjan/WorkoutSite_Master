package com.example.WorkoutSite.model;

import com.example.WorkoutSite.Util.LocalDateTimeAttributeConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "WorkOutTransaction")
public class WorkOutTransaction {

    private interface Table {
        String TRANSACTION_ID = "TRANSACTION_ID";
        String START_TIME = "START_TIME";
        String END_TIME = "END_TIME";
        String DURATION = "DURATION";
        String CALSBURNT = "CALSBURNT";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Table.TRANSACTION_ID)
    private Integer transactionId;

    @Column(name = Table.START_TIME)
    private LocalDateTime startTime;

    @Column(name = Table.END_TIME)
    private LocalDateTime endTime;

    @Column(name = Table.DURATION)
    private Duration duration;

    @Column(name = Table.CALSBURNT)
    private Double calsBurnt;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "workoutId")
    private WorkOut workOut;

    public Integer getTransaction_Id() {
        return transactionId;
    }

    public void setTransaction_Id(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public WorkOut getWorkOut() {
        return workOut;
    }

    public void setWorkOut(WorkOut workOut) {
        this.workOut = workOut;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Double getCalsBurnt() {
        return calsBurnt;
    }

    public void setCalsBurnt(Double calsBurnt) {
        this.calsBurnt = calsBurnt;
    }

    public WorkOutTransaction(Integer transactionId, LocalDateTime startTime, LocalDateTime endTime, Duration duration, Double calsBurnt) {
        super();
        this.transactionId = transactionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.calsBurnt = calsBurnt;
    }

    public WorkOutTransaction(Integer transactionId, WorkOut workOut, LocalDateTime startTime, LocalDateTime endTime) {
        super();
        this.transactionId = transactionId;
        this.workOut = workOut;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.calsBurnt = calsBurnt;
    }
    public WorkOutTransaction(){

    }
}
