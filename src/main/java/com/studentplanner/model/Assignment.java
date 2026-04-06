package com.studentplanner.model;

import com.studentplanner.interfaces.Notifiable;
import com.studentplanner.interfaces.Trackable;

// Inheritance  — extends AcademicItem
// Interfaces   — implements Notifiable and Trackable
// Polymorphism — overrides getType() and getDeadline()
public class Assignment extends AcademicItem implements Notifiable, Trackable {

    private String dueDate;
    private boolean completed;

    public Assignment() {}

    public Assignment(String id, String title, String subjectName,String userId, String dueDate){
        super(id, title, subjectName, userId);
        this.dueDate   = dueDate;
        this.completed = false;
    }

    @Override
    public String getType() { return "Assignment"; }

    @Override
    public String getDeadline() { return dueDate; }

    @Override
    public String getNotificationMessage() {
        return "Assignment due: " + getTitle() + " (" + getSubjectName() + ") on " + dueDate;
    }

    @Override
    public boolean isCompleted() { return completed; }

    @Override
    public void markComplete() { this.completed = true; }

    public String getDueDate()             { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public void setCompleted(boolean c)    { this.completed = c; }
}