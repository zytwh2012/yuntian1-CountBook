/*
 * Copyright (c) YuntianZhang(dingding) CMPUT 301 University of Alberta. All Rights Reserved. you may
 * use,distribute or modify this code under term and conditions of Code of Students Behavior
 * at University of Alberta.
 */

package com.example.countbook;

import java.util.Date;

/**
 * Created by dingding on 2017/9/30.
 */

public class counterClass {
    private String count;
    private Date date;
    private String name;
    private String comment;

    public counterClass(String InitialCount,String name,String comment){
        this.count = InitialCount;
        this.date = new Date();
        this.name = name;
        this.comment = comment;
    }
    public counterClass(Date date,String InitialCount,String name,String comment){
        this.count = InitialCount;
        this.date = date;
        this.name = name;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Last modified "+date.toString() + "  " +"Name:" + name + "  " + "Value:" + count +
                "  "
                +"Comment:" +comment ;
    }

    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return date;
    }

    public void setCount(String count){
        this.count = count;
    }
    public String getCount(){
        return count;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
    public String  getComment(){
        return comment;
    }
}
