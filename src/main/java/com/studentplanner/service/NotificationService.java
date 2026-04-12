package com.studentplanner.service;

import com.studentplanner.model.Assignment;
import com.studentplanner.model.Exam;
import com.studentplanner.interfaces.Notifiable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private ExamService examService;

    // Returns reminders for items due within the next N days
    // Uses Notifiable interface — polymorphism in action
    public List<String> getReminders(String userId, int days) throws Exception {
        List<String> reminders = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(days);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Check assignments — Notifiable interface
        List<Assignment> assignments = assignmentService.getAssignments(userId);
        for (Assignment a : assignments) {
            if (!a.isCompleted()) {
                LocalDate due = LocalDate.parse(a.getDueDate(), fmt);
                if (!due.isBefore(today) && !due.isAfter(limit)) {
                    // Polymorphism — getNotificationMessage() called on Notifiable
                    reminders.add(a.getNotificationMessage());
                }
            }
        }

        // Check exams — same interface, different class
        List<Exam> exams = examService.getExams(userId);
        for (Exam e : exams) {
            LocalDate examDay = LocalDate.parse(e.getExamDate(), fmt);
            if (!examDay.isBefore(today) && !examDay.isAfter(limit)) {
                reminders.add(e.getNotificationMessage());
            }
        }

        return reminders;
    }
}