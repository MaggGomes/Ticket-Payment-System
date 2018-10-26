package com.cmov.server.domain;

public class Voucher {
    private final String id;
    private final float value;

    public Voucher(String id, float value){
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
