# 🏋️ Fitness Diet Recommendation System

This is a web-based fitness and diet management platform that allows students to submit their personal information, bind with a coach, record workout data, and receive automatically generated meal plans based on daily activity. Coaches can manage student requests, upload training records, and view diet plans.

---

## 📦 Tech Stack

### Backend – Spring Boot

- Java 17 + Spring Boot 3.x
- Spring Security (JWT-based authentication)
- Spring Data JPA (Hibernate)
- RESTFul API design
- MySQL database
- Maven for build and dependency management

### Frontend – Vue 3 + Quasar Framework

- Vue 3 + Composition API
- Quasar UI framework
- Axios for API requests
- Vue Router for routing
- Token storage via localStorage
- Fully responsive design

---

## 🗂️ Project Structure

```
diet/
│
├── diet-boot/       # Spring Boot backend
│   ├── src/
│   └── pom.xml
│
├── diet-frontend/   # Quasar frontend project
│   ├── src/
│   └── quasar.config.js
│
└── README.md
```

---

## 🚀 How to Run the Project

### ✅ Start Backend

```bash
cd diet-core
mvn clean install
cd diet-boot
mvn clean install
nohup java -jar target/diet-boot-0.0.1.jar > app.log 2>&1 &
```

By default, the backend runs on `http://localhost:8055`.

> ⚠️ Make sure your database connection is configured in `application.yml` or `.properties`.

---

### ✅ Start Frontend

```bash
cd diet-frontend
npm i -g @quasar/cli
npm install
npm start
```

The frontend will be available at: `http://localhost:9000`

---

## 🧪 Core Features

### 🎓 Student Side

- Fill in personal fitness profile (age, weight, goal)
- Send binding request to a coach
- Record additional workout activities
- Generate daily meal plan based on previous activity
- View recommended breakfast, lunch, and dinner

### 🧑‍🏫 Coach Side

- View and manage student binding requests
- View list of bound students
- Upload workout records for each student
- View each student’s meal plan

---

## ⚙️ Environment Requirements

- Node.js ≥ 18
- Java 17+
- Maven ≥ 3.8
- Quasar CLI (install via `npm install -g @quasar/cli`)
