package com.studentplanner.controller;

import com.studentplanner.model.User;
import com.studentplanner.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.studentplanner.service.NotificationService;
import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody Map<String, String> body) {
        try {
            String idToken = body.get("token");
            User user = authService.verifyToken(idToken);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid token");
        }
    }

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/reminders/{userId}")
    public ResponseEntity<?> getReminders(@PathVariable String userId) {
        try {
            List<String> reminders = notificationService.getReminders(userId, 3);
            return ResponseEntity.ok(reminders);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }   
}