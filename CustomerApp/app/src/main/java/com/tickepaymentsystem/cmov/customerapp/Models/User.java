package com.tickepaymentsystem.cmov.customerapp.Models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    private String name;
    @SerializedName("keyN")
    private String keyN;
    @SerializedName("keyE")
    private String keyE;
    @SerializedName("password")
    private String password;
    @SerializedName("nif")
    private long nif;
    @SerializedName("cc")
    private CreditCard cc;

    public User(String name, String keyN, String keyE, String password, Long nif, CreditCard cc){
        this.name = name;
        this.keyN = keyN;
        this.keyE = keyE;
        this.password = password;
        this.nif = nif;
        this.cc = cc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyN() {
        return keyN;
    }

    public void setKeyN(String keyN) {
        this.keyN = keyN;
    }

    public String getKeyE() {
        return keyE;
    }

    public void setKeyE(String keyE) {
        this.keyE = keyE;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getNif() {
        return nif;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    public CreditCard getCc() {
        return cc;
    }

    public void setCc(CreditCard cc) {
        this.cc = cc;
    }
}
