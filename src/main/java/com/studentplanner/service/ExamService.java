package com.studentplanner.service;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.studentplanner.model.Exam;
import com.studentplanner.model.Chapter;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ExamService {

    private static final String COLLECTION = "exams";

    public Exam addExam(Exam exam) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        String id = UUID.randomUUID().toString();
        exam.setId(id);
        db.collection(COLLECTION).document(id).set(exam).get();
        return exam;
    }

    public List<Exam> getExams(String userId) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        List<Exam> list = new ArrayList<>();
        db.collection(COLLECTION)
          .whereEqualTo("userId", userId)
          .get().get()
          .getDocuments()
          .forEach(doc -> list.add(doc.toObject(Exam.class)));
        return list;
    }

    public Exam getExam(String id) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        DocumentSnapshot doc = db.collection(COLLECTION).document(id).get().get();
        return doc.toObject(Exam.class);
    }

    // Tick or untick a chapter
    public void toggleChapter(String examId, int chapterIndex) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        Exam exam = getExam(examId);
        List<Chapter> portion = exam.getPortion();
        if (chapterIndex >= 0 && chapterIndex < portion.size()) {
            Chapter ch = portion.get(chapterIndex);
            ch.setCompleted(!ch.isCompleted());
        }
        db.collection(COLLECTION).document(examId)
          .update("portion", portion).get();
    }

    public void deleteExam(String id) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION).document(id).delete().get();
    }
}