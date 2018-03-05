package com.example.geeko.todoapp;

/**
 * Created by Geeko on 2018/01/13.
 */

public class ToDo {

    private String title;
    private String description;
    private String date;
    private long id;

    public ToDo(String title, String description, String date){
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getID(){
        return date;
    }

    public void setId(long id){
        this.id = id;
    }
}
