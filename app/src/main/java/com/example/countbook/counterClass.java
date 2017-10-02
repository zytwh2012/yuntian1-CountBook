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
    private String initialCount;
    private Date date;
    private String name;
    private String comment;

    public counterClass(String initialCount, String count, String name, String comment) {
        this.initialCount = initialCount;
        this.count = count;
        this.date = new Date();
        this.name = name;
        this.comment = comment;
    }

    public counterClass(Date date, String initialCount, String count, String name, String comment) {
        this.initialCount = initialCount;
        this.count = count;
        this.date = date;
        this.name = name;
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "Last modified " + date.toString() + "  " + "Name:" + name + "  " +
                "Initial Value:"+ initialCount + "  " + "Value:" +
                count + "  " + "Comment:" + comment;
    }

}


