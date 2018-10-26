package com.cmov.server.domain;

import java.util.Date;

public class Show {
    private final String id;
    private final String name;
    private final String description;
    private final Date date;

    public Show(String id, String name, String description, Date date){
        this.id = id;
        this.name = name;
        this.description = description;
        this.date= date;
    }

    public String getId(){
        return this.id;
    }

    public Date getDate(){
        return this.date;
    }
}
