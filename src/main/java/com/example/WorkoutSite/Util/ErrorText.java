package com.example.WorkoutSite.Util;

public class ErrorText {


    public ErrorText (){}

    public ErrorText(String message, String status) {
        this.message = message;
        this.status = status;
    }

    private String message;
    private String status;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
