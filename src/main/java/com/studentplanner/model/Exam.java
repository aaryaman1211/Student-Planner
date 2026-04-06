package com.studentplanner.model;

import com.studentplanner.interfaces.Notifiable;
import java.util.ArrayList;
import java.util.List;

// Inheritance — extends AcademicItem
// Composition — has a List<Chapter>
// Interface   — implements Notifiable
public class Exam extends AcademicItem implements Notifiable {

    private String examDate;
    private List<Chapter> portion;

    public Exam() {
        this.portion = new ArrayList<>();
    }

    public Exam(String id, String title, String subjectName,
                String userId, String examDate, List<Chapter> portion) {
        super(id, title, subjectName, userId);
        this.examDate = examDate;
        this.portion  = portion != null ? portion : new ArrayList<>();
    }

    @Override
    public String getType() { return "Exam"; }

    @Override
    public String getDeadline() { return examDate; }

    @Override
    public String getNotificationMessage() {
        return "Exam coming up: " + getTitle() + " (" + getSubjectName() + ") on " + examDate;
    }

    public String getExamDate()              { return examDate; }
    public List<Chapter> getPortion()        { return portion; }
    public void setExamDate(String examDate) { this.examDate = examDate; }
    public void setPortion(List<Chapter> p)  { this.portion = p; }

    public void markChapterComplete(int index) {
        if (index >= 0 && index < portion.size()) {
            portion.get(index).setCompleted(true);
        }
    }

    public String getProgress() {
        long done = portion.stream().filter(Chapter::isCompleted).count();
        return done + "/" + portion.size() + " chapters done";
    }
}