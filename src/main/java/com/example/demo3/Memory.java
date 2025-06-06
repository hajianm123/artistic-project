package com.example.demo3;

import java.util.ArrayList;

public class Memory {
    String subject;
    String date;
    String description;
    static ArrayList<Memory> memories = new ArrayList<Memory>();
    static int id = 0;
    Memory(){
        id++;
    }

    @Override
    public String toString() {
        return date + " " + subject+  " " +description;
    }
}
