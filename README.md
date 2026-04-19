# 📚 Student Planner App

A full-stack mobile web application built for students to track assignments and exams, with Google Classroom integration. Built entirely in Java using OOP principles, hosted on GitHub Pages (frontend) and Railway (backend).

**🌐 Live App:** https://aaryaman1211.github.io/student-planner/login.html  
**⚙️ Backend API:** https://web-production-dc086.up.railway.app  
**💻 GitHub Repo:** https://github.com/aaryaman1211/Student-Planner

---

## 👨‍💻 Developer

**Name:** Aaryaman Parulekar  
**Email:** aaryaman.parulekar.btech2028@atlasskilltech.university  
**Course:** BTech 2028 — Atlas SkillTech University

---

## 📱 What the App Does

Student Planner is a progressive web app (PWA) that students can add to their iPhone or Android home screen and use like a native app. It helps you:

- Track assignments with due dates and mark them complete
- Track upcoming exams with a chapter-by-chapter preparation checklist
- See everything in one dashboard sorted by deadline
- Get reminders for items due in the next 3 days
- Automatically import assignments from Google Classroom
- Access your data from any device since everything is synced to Firebase

---

## ✨ Features

| Feature | Description |
|---|---|
| 🔐 Login / Signup | Firebase Authentication with "Keep me signed in" toggle |
| 📝 Assignments | Add, edit, delete, complete assignments with due dates |
| 📅 Exams | Add exams with a chapter checklist and progress bar |
| ✏️ Edit Exams | Add or remove chapters, edit title/subject/date inline |
| 🏠 Dashboard | All upcoming assignments and exams sorted by date |
| 🔔 Reminders | Yellow banner for anything due within 3 days |
| 🎓 Google Classroom | Auto-import assignments from Google Classroom |
| 👤 Account Page | View profile, total stats, update display name |
| 📲 PWA Support | Add to iPhone home screen, runs fullscreen like a native app |
| 🌙 Dark Theme | Full dark UI optimised for iPhone with safe area support |

---

## ☕ Java OOP Concepts

This project demonstrates all six core OOP concepts through the Java Spring Boot backend.

### 1. Encapsulation
Every model class uses private fields with public getters and setters. No field is ever accessed directly from outside the class.

```java
public class User {
    private String uid;
    private String email;
    private String name;

    public String getUid()             { return uid; }
    public void setUid(String uid)     { this.uid = uid; }
    public String getEmail()           { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName()            { return name; }
    public void setName(String name)   { this.name = name; }
}
```

### 2. Abstraction
`AcademicItem` is an abstract class that defines the shared structure for all academic items. It cannot be instantiated directly — only its subclasses can.

```java
public abstract class AcademicItem {
    private String id;
    private String title;
    private String subjectName;
    private String userId;

    // Subclasses MUST implement these
    public abstract String getType();
    public abstract String getDeadline();
}
```

### 3. Inheritance
`Assignment` and `Exam` both extend `AcademicItem`. They inherit all common fields (id, title, subjectName, userId) and add their own specific ones.

```java
// Assignment inherits from AcademicItem
public class Assignment extends AcademicItem implements Notifiable, Trackable {
    private String dueDate;
    private boolean completed;

    @Override
    public String getType()     { return "Assignment"; }

    @Override
    public String getDeadline() { return dueDate; }
}

// Exam also inherits from AcademicItem
public class Exam extends AcademicItem implements Notifiable {
    private String examDate;
    private List<Chapter> portion;

    @Override
    public String getType()     { return "Exam"; }

    @Override
    public String getDeadline() { return examDate; }
}
```

### 4. Polymorphism
`NotificationService` calls `getNotificationMessage()` on both `Assignment` and `Exam` objects through the `Notifiable` interface — the same method call produces different output depending on the actual type.

```java
// Polymorphism in action — same interface, different behaviour
List<Assignment> assignments = assignmentService.getAssignments(userId);
for (Assignment a : assignments) {
    if (!a.isCompleted()) {
        reminders.add(a.getNotificationMessage());
        // Output: "Assignment due: Maths HW (Mathematics) on 2026-04-20"
    }
}

List<Exam> exams = examService.getExams(userId);
for (Exam e : exams) {
    reminders.add(e.getNotificationMessage());
    // Output: "Exam coming up: Physics Test (Physics) on 2026-04-25"
}
```

### 5. Interfaces
Two interfaces define contracts that are implemented across classes:

```java
// Notifiable — anything that can send a reminder
public interface Notifiable {
    String getNotificationMessage();
    String getDeadline();
}

// Trackable — anything that can be marked complete
public interface Trackable {
    boolean isCompleted();
    void markComplete();
}
```

`Assignment` implements both `Notifiable` and `Trackable`.  
`Exam` implements `Notifiable` only.

### 6. Composition
`Exam` has a `List<Chapter>` — this is a has-a relationship (composition). `Chapter` is its own class with its own fields and methods.

```java
// Chapter is a standalone class
public class Chapter {
    private String name;
    private boolean completed;

    public void setCompleted(boolean c) { this.completed = c; }
    public boolean isCompleted()        { return completed; }
}

// Exam CONTAINS a list of chapters — composition
public class Exam extends AcademicItem {
    private List<Chapter> portion;

    public void markChapterComplete(int index) {
        portion.get(index).setCompleted(true);
    }

    public String getProgress() {
        long done = portion.stream().filter(Chapter::isCompleted).count();
        return done + "/" + portion.size() + " chapters done";
    }
}
```

---
## 📷 Photos of App

<img width="1919" height="986" alt="image" src="https://github.com/user-attachments/assets/c7b5a55b-0625-4148-9e72-95ed1cb6d61f" />
<img width="1919" height="986" alt="image" src="https://github.com/user-attachments/assets/4d541644-c15e-4a40-a71b-4ceca25ccb79" />
<img width="1919" height="986" alt="image" src="https://github.com/user-attachments/assets/f0d02d11-e102-4095-ba89-64c56aa25443" />
<img width="1919" height="986" alt="image" src="https://github.com/user-attachments/assets/22cc2315-550e-4f3f-9317-5c845b8c7d51" />
<img width="1919" height="986" alt="image" src="https://github.com/user-attachments/assets/ee1718d3-15c6-4a4c-a9fe-13707f22365e" />

---

## 🌿 Git Branch History

The project was built using feature branching — each feature was developed on its own branch and merged into `main` via a pull request.

| Branch | What was built | Commits | Status |
|---|---|---|---|
| `main` | Production branch | — | ✅ Live |
| `feature/auth` | Firebase login, signup, User model | 5 | ✅ Merged |
| `feature/data-models` | AcademicItem, Assignment, Exam, Chapter, interfaces | 4 | ✅ Merged |
| `feature/assignments` | Assignment CRUD, REST endpoints, HTML screens | 4 | ✅ Merged |
| `feature/exams` | Exam CRUD, chapter toggle endpoint, exam screens | 3 | ✅ Merged |
| `feature/dashboard` | Dashboard, combined view, navigation | 3 | ✅ Merged |
| `feature/notifications` | NotificationService, reminders endpoint | 2 | ✅ Merged |
| `feature/google-classroom` | Google OAuth, Classroom API sync, auto-import | 4 | ✅ Merged |

**Total commits:** 30+  
**Total feature branches:** 7, all merged into main

### Branch Workflow
```
main
 ├── feature/auth              → merged ✅
 ├── feature/data-models       → merged ✅
 ├── feature/assignments       → merged ✅
 ├── feature/exams             → merged ✅
 ├── feature/dashboard         → merged ✅
 ├── feature/notifications     → merged ✅
 └── feature/google-classroom  → merged ✅
```

Each feature branch followed this workflow:
```bash
git checkout -b feature/name   # create branch
# ... write code, make multiple commits ...
git push -u origin feature/name
git checkout main
git merge feature/name         # merge to main
git push origin main
```

---

## 🏗️ Architecture

```
iPhone / Any Browser (Safari)
           │
           │  GitHub Pages — static frontend
           │  aaryaman1211.github.io/student-planner/
           │
           │  fetch() REST API calls over HTTPS
           ▼
  Java Spring Boot Backend
           │  @RestController — exposes REST endpoints
           │  @Service        — business logic + OOP
           │  @Configuration  — Firebase setup
           │  Hosted on Railway.app
           │
           │  Firebase Admin Java SDK
           ▼
     Firebase (Google Cloud)
           ├── Authentication  — login, signup, token verification
           └── Firestore       — cloud NoSQL database

  Google Classroom API
           └── OAuth2 implicit flow → fetch assignments → import to planner
```

---

## 🛠️ Tech Stack

| Layer | Technology | Purpose |
|---|---|---|
| Language | Java 17 | All backend logic |
| Framework | Spring Boot 3.2 | REST API server |
| Database | Firebase Firestore | Cloud NoSQL storage |
| Auth | Firebase Authentication | User login / signup |
| Frontend | HTML5 + CSS3 + Vanilla JS | Mobile web UI |
| Frontend Host | GitHub Pages | Free static hosting |
| Backend Host | Railway.app | Java backend hosting |
| External API | Google Classroom API | Assignment sync |
| Build Tool | Maven | Dependency management |
| Version Control | Git + GitHub | Source control |

---

## 📁 Project Structure

```
student-planner/
│
├── src/main/java/com/studentplanner/
│   │
│   ├── StudentPlannerApplication.java     ← entry point
│   │
│   ├── config/
│   │   └── FirebaseConfig.java            ← Firebase initialisation
│   │
│   ├── model/
│   │   ├── AcademicItem.java              ← abstract base class (OOP)
│   │   ├── Assignment.java                ← extends AcademicItem (OOP)
│   │   ├── Exam.java                      ← extends AcademicItem (OOP)
│   │   ├── Chapter.java                   ← composition inside Exam (OOP)
│   │   └── User.java                      ← encapsulation (OOP)
│   │
│   ├── interfaces/
│   │   ├── Notifiable.java                ← interface (OOP)
│   │   └── Trackable.java                 ← interface (OOP)
│   │
│   ├── service/
│   │   ├── AuthService.java               ← token verification
│   │   ├── AssignmentService.java         ← assignment business logic
│   │   ├── ExamService.java               ← exam + chapter logic
│   │   └── NotificationService.java       ← reminder logic (polymorphism)
│   │
│   └── controller/
│       ├── AuthController.java            ← /auth endpoints
│       ├── AssignmentController.java      ← /assignments endpoints
│       └── ExamController.java            ← /exams endpoints
│
├── src/main/resources/
│   └── application.properties
│
├── frontend/                              ← source HTML files
│   ├── login.html
│   ├── signup.html
│   ├── dashboard.html
│   ├── assignments.html
│   ├── add-assignment.html
│   ├── edit-assignment.html
│   ├── exams.html
│   ├── add-exam.html
│   ├── exam-detail.html
│   ├── account.html
│   └── classroom-callback.html
│
├── docs/                                  ← GitHub Pages deployment
│   └── (same HTML files as frontend/)
│
├── Dockerfile                             ← container deployment
├── Procfile                               ← Railway deployment
├── pom.xml                                ← Maven dependencies
└── README.md
```

---

## 📊 REST API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/auth/verify` | Verify Firebase ID token, return user |
| `GET` | `/auth/reminders/{userId}` | Get items due within 3 days |
| `POST` | `/assignments` | Create a new assignment |
| `GET` | `/assignments/{userId}` | Get all assignments for user |
| `PUT` | `/assignments/{id}/complete` | Mark assignment as complete |
| `PUT` | `/assignments/{id}/update` | Edit assignment details |
| `DELETE` | `/assignments/{id}` | Delete an assignment |
| `POST` | `/exams` | Create a new exam |
| `GET` | `/exams/{userId}` | Get all exams for user |
| `GET` | `/exams/detail/{id}` | Get one exam with chapters |
| `PUT` | `/exams/{id}/chapter/{index}` | Toggle chapter complete/incomplete |
| `PUT` | `/exams/{id}/update` | Edit exam details and chapters |
| `DELETE` | `/exams/{id}` | Delete an exam |

---

## 🗄️ Database Structure (Firestore)

```
Firestore
├── assignments/
│   └── {assignmentId}
│       ├── id:          string
│       ├── title:       string
│       ├── subjectName: string
│       ├── dueDate:     string (YYYY-MM-DD)
│       ├── completed:   boolean
│       └── userId:      string
│
└── exams/
    └── {examId}
        ├── id:          string
        ├── title:       string
        ├── subjectName: string
        ├── examDate:    string (YYYY-MM-DD)
        ├── userId:      string
        └── portion: [
                { name: string, completed: boolean },
                { name: string, completed: boolean }
            ]
```

---

## 🚀 How to Run Locally

### Prerequisites
- Java 17 or higher
- Maven 3.9+
- A Firebase project with Firestore enabled
- `serviceAccountKey.json` from Firebase Console

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/aaryaman1211/Student-Planner.git
cd Student-Planner

# 2. Add your Firebase service account key
cp /path/to/serviceAccountKey.json src/main/resources/

# 3. Run the Spring Boot backend
mvn spring-boot:run

# 4. Open the frontend
open frontend/login.html
```

The backend runs on `http://localhost:8080`.  
The frontend HTML files can be opened directly in any browser.

---

## 🔒 Security

- Firebase Authentication handles all user login — passwords are never stored in our database
- Every API request is scoped to a `userId` — users can only access their own data
- Firebase service account key is stored as an environment variable on Railway, never in source code
- `serviceAccountKey.json` is excluded from git via `.gitignore`
- GitHub push protection is enabled — it blocked an accidental key commit during development
- Google Classroom uses OAuth2 implicit flow — access tokens expire after 1 hour

---

## 📲 How to Add to iPhone Home Screen

1. Open Safari on iPhone
2. Go to `https://aaryaman1211.github.io/student-planner/login.html`
3. Tap the **Share** button (box with arrow)
4. Scroll down and tap **"Add to Home Screen"**
5. Name it **Planner** → tap **Add**

The app opens fullscreen with no browser bar, exactly like a native app.

---

## 🔗 Dependencies (pom.xml)

```xml
<!-- Spring Boot Web — REST API server -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Firebase Admin SDK — Auth + Firestore -->
<dependency>
    <groupId>com.google.firebase</groupId>
    <artifactId>firebase-admin</artifactId>
    <version>9.2.0</version>
</dependency>

<!-- Google Cloud Firestore -->
<dependency>
    <groupId>com.google.cloud</groupId>
    <artifactId>google-cloud-firestore</artifactId>
    <version>3.15.0</version>
</dependency>
```
