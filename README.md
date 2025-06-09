# 🎓 Biotech Student Management System (Java)

A GUI-based Java application for managing student records — designed to help track, view, and evaluate academic data with ease. Built with Swing, it's lightweight, fast, and easy to use.

---

## ✨ Features of the application

- Add new students with name, roll number, and marks
- View all students in a dynamic table
- Search by roll number
- Calculate average marks
- Display grade distribution

---

## 🖼️ Screenshots

### 🔐 Login Screen Page
![login-screen png](https://github.com/user-attachments/assets/f1fe23b7-24de-4958-991e-27f0b510446f)


### 🧾 Main Interface UI  
![ui-main png](https://github.com/user-attachments/assets/8a885155-4e92-4adb-a53a-84a9d11b9e28)


---

## 📁 Project Structure

This repository follows a clean, professional layout with a **single main branch** containing all necessary project files organized in folders:
````
student-management-system/
├── src/
│ ├── Student.java
│ └── StudentManagementSystem.java
├── assets/
│ └── screenshots/
│ ├── login-screen.png
│ └── ui-main.png
├── presentation/
│ └── student-management-system-presentation.pptx
├── README.md
````

## 📊 Project Presentation

The project presentation is included in the `presentation/` folder for explanation of application features and operations.

---

## 🛠️ Technologies Used

- Java (JDK 17 or above recommended)
- Swing (Java GUI)
- AWT
- File-based storage using `.dat` files (no external database or JDBC)
- IntelliJ IDEA / Eclipse (IDE)

---

## 📋 Requirements

Before running the project, make sure you have the following installed and set up on your machine:

- **Java Development Kit (JDK)** version 17 or above  
  _Download from [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or [OpenJDK](https://openjdk.org/)_

- **IDE (Optional but Recommended):** IntelliJ IDEA, Eclipse, or any Java-supporting IDE for easier development and execution.

- **Git** (for cloning the repository)  
  _Download and install from [Git SCM](https://git-scm.com/)_


---

## 💾 Data Storage & Persistence

This project uses file-based storage with `.dat` files to persist student data locally. The application reads and writes student records directly to these files, eliminating the need for external database setup or JDBC connectivity. This approach keeps the system lightweight and easy to deploy, while ensuring data persists between sessions.

---
## 🚀 How to Run the Project

1. **Clone the repository**
   ```
   git clone https://github.com/your-username/student-management-system.git
   cd student-management-system
   ```
2. Open the project in IntelliJ IDEA or Eclipse

3. Run the file
     ```Compile and run StudentManagementSystem.java```

---

## 📸 Local Screenshots
These screenshots are also available inside the project folder under assets/screenshots/, for offline access.

````
assets/screenshots/login-screen.png  
assets/screenshots/ui-main.png
````
---

## 🔮 Future Improvements

- Integration with a relational database (MySQL, SQLite) using JDBC for scalable data management.
- Implement DAO pattern for cleaner code separation and easier maintenance.
- Enhance UI responsiveness and add more analytics features.

---

## 👨‍💻 Team

1. Kshitiz -
   B.Tech in Biotechnology
2. Esha Eharma -
   B.Tech in Biotechnology
3. Suryansh Pratap Singh -
   B.Tech in Biotechnology
4. Gyanendra Pandey -
   B.Tech in Biotechnology    

---

## 📜 License
This project is open-source and free to use for educational and personal learning purposes.

---
