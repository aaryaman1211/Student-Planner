package com.studentplanner.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.studentplanner.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public User verifyToken(String idToken) throws Exception {
        FirebaseToken decoded = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid   = decoded.getUid();
        String email = decoded.getEmail();
        String name  = decoded.getName() != null ? decoded.getName() : "Student";
        return new User(uid, email, name);
    }
}