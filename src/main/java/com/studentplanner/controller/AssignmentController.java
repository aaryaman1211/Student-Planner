package com.studentplanner.controller;

import com.studentplanner.model.Assignment;
import com.studentplanner.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/assignments")
@CrossOrigin(origins = "*")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Assignment assignment) {
        try {
            return ResponseEntity.ok(assignmentService.addAssignment(assignment));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAll(@PathVariable String userId) {
        try {
            List<Assignment> list = assignmentService.getAssignments(userId);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> markComplete(@PathVariable String id) {
        try {
            assignmentService.markComplete(id);
            return ResponseEntity.ok("Marked complete");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            assignmentService.deleteAssignment(id);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}