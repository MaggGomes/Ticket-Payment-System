package com.cmov.server.domain;

public class Customer {
    private final String id;
    private final String name;

    public Customer(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
