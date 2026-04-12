package com.studentplanner.controller;

import com.studentplanner.model.Exam;
import com.studentplanner.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/exams")
@CrossOrigin(origins = "*")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Exam exam) {
        try {
            return ResponseEntity.ok(examService.addExam(exam));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAll(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(examService.getExams(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id) {
        try {
            return ResponseEntity.ok(examService.getExam(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{examId}/chapter/{index}")
    public ResponseEntity<?> toggleChapter(@PathVariable String examId,
                                           @PathVariable int index) {
        try {
            examService.toggleChapter(examId, index);
            return ResponseEntity.ok("Chapter toggled");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            examService.deleteExam(id);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Exam exam) {
        try {
            return ResponseEntity.ok(examService.updateExam(id, exam));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}