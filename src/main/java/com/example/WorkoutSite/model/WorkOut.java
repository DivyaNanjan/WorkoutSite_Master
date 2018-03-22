package com.example.WorkoutSite.model;

import javax.persistence.*;

@Entity
@Table(name = "WorkOut")
public class WorkOut {

    private interface Table {
        String WORKOUT_ID = "WORKOUT_ID";
        String TITLE = "TITLE";
        String CALBURNTPERUNITTIME = "CALBURNTPERUNITTIME";
        String UNIT_TIME = "UNIT_TIME";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Table.WORKOUT_ID)
    private Integer workoutId;

    @Column(name = Table.TITLE)
    private String title;

    @Column(name = Table.CALBURNTPERUNITTIME)
    private Double calBurntPerUnitTime;

    @Enumerated(EnumType.STRING)
    @Column(name = Table.UNIT_TIME)
    private UnitTime unitTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    public WorkOut() {
        super();
    }

    public Integer getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Integer workoutId) {
        this.workoutId = workoutId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UnitTime getUnitTime() {
        return unitTime;
    }

    public void setUnitTime(UnitTime unitTime) {
        this.unitTime = unitTime;
    }

    public Double getCalBurntPerUnitTime() {
        return calBurntPerUnitTime;
    }

    public void setCalBurntPerUnitTime(Double calBurntPerUnitTime) {
        this.calBurntPerUnitTime = calBurntPerUnitTime;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WorkOut(Integer workoutId, Double calBurntPerUnitTime, String title, UnitTime unitTime) {
        super();
        this.workoutId = workoutId;
        this.calBurntPerUnitTime = calBurntPerUnitTime;
        this.title = title;
        this.unitTime = unitTime;
    }

    public WorkOut(Integer workoutId, Double calBurntPerUnitTime, String title, User user) {
        super();
        this.workoutId = workoutId;
        this.title = title;
        this.calBurntPerUnitTime = calBurntPerUnitTime;
        this.user = user;
    }


}