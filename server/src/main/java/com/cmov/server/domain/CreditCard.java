package com.cmov.server.domain;

public class CreditCard {
    private final String id;
    private final float value;

    public CreditCard(String id, float value){
        this.id = id;
        this.value = value;
    }

    public String getId(){
        return this.id;
    }

    public float getValue(){
        return this.value;
    }
}