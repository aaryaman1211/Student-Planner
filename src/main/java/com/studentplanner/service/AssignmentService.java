package com.studentplanner.service;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.studentplanner.model.Assignment;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AssignmentService {

    private static final String COLLECTION = "assignments";

    public Assignment addAssignment(Assignment assignment) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        String id = UUID.randomUUID().toString();
        assignment.setId(id);
        db.collection(COLLECTION).document(id).set(assignment).get();
        return assignment;
    }

    public List<Assignment> getAssignments(String userId) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        List<Assignment> list = new ArrayList<>();
        db.collection(COLLECTION)
          .whereEqualTo("userId", userId)
          .get().get()
          .getDocuments()
          .forEach(doc -> list.add(doc.toObject(Assignment.class)));
        return list;
    }

    public void markComplete(String id) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION).document(id)
          .update("completed", true).get();
    }

    public void deleteAssignment(String id) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION).document(id).delete().get();
    }
}