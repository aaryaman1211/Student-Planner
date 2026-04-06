package com.studentplanner.model;

// Composition — Exam has a List<Chapter>
public class Chapter {

    private String name;
    private boolean completed;

    public Chapter() {}

    public Chapter(String name) {
        this.name      = name;
        this.completed = false;
    }

    public String getName()             { return name; }
    public boolean isCompleted()        { return completed; }
    public void setName(String name)    { this.name = name; }
    public void setCompleted(boolean c) { this.completed = c; }
}