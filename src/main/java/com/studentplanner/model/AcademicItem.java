package com.studentplanner.model;

// Abstract class — cannot be created directly
// Assignment and Exam both extend this

public abstract class AcademicItem {
    private String id;
    private String title;
    private String subjectName;
    private String userId;

    public AcademicItem(){}

    public AcademicItem(String id, String title, String subjectName, String userId){
        this.id = id;
        this.title = title;
        this.subjectName = subjectName;
        this.userId = userId;
    }

    // Abstract methods — every subclass MUST implement these
    public abstract String getType();
    public abstract String getDeadline();

    public String getId()                { return id; }
    public String getTitle()             { return title; }
    public String getSubjectName()       { return subjectName; }
    public String getUserId()            { return userId; }
    public void setId(String id)         { this.id = id; }
    public void setTitle(String title)   { this.title = title; }
    public void setSubjectName(String s) { this.subjectName = s; }
    public void setUserId(String userId) { this.userId = userId; }
}
