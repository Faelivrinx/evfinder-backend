package com.mypieceofcode.evfinder.command;

public class TaskResponse {
    private String task;
    private String value;

    public TaskResponse(String task, String value) {
        this.task = task;
        this.value = value;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setSucces() {
        this.value = "success";
    }

    public void setFailed(){
        this.value="failed";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
