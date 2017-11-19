package com.mypieceofcode.evfinder.command;

import java.io.Serializable;

public class ApiKey implements Serializable{
    private String name = "API_KEY";
    private String value = "";

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
