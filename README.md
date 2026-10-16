Student Management System
-------------------------

A simple Java console-based application to manage student records. The project demonstrates object-oriented programming, CRUD operations, grade calculation, and file handling for permanent data storage.

Features
--------

Add Student: Add a student with a unique roll number, name, and marks.

View Students: Display all student records and their grades.

Search Student: Find a student using their roll number.

Update Student: Modify a student's name and marks.

Delete Student: Remove a student record.

Grade Calculation: Automatically calculate grades based on marks.

File Storage: Save student records in a text file and load them when the application starts.

Input Validation: Validate roll numbers, names, marks, and menu choices.

Technologies Used
-----------------

Java

Object-Oriented Programming (OOP)

Java Collections (ArrayList)

File Handling (BufferedReader, BufferedWriter)

Git and GitHub

Requirements

Java Development Kit (JDK) 11 

Terminal 

How to Run the Project
----------------------

1. Download the project

Clone the repository:

git clone https://github.com/YOUR-USERNAME/Student-Management-System.git

Alternatively, click Code → Download ZIP on GitHub and extract the folder.

2. Open the project folder

Open the extracted project folder in VS Code or a terminal.

3. Compile the Java program

javac StudentManagement.java

4. Run the application

java StudentManagement

Application Menu

===== STUDENT MANAGEMENT SYSTEM =====
1. Add Student
2. View All Students
3. Search Student
4. Update Student
5. Delete Student
6. Exit

Select an option by entering its corresponding number.

Sample Test
-----------

Add a student using the following details:

Field

Value

Roll Number

101

Name

Arun Kumar

Marks

85

Expected Grade

B

After adding the student, select View All Students to verify the record.

You can also test searching, updating, and deleting student records.

Grade Criteria

Marks

Grade

90–100

A

75–89.99

B

60–74.99

C

40–59.99

D

Below 40

Fail

Data Storage

Student records are stored in students.txt using the following format:

101|Arun Kumar|85.0

The application loads existing records when it starts and saves changes when students are added, updated, or deleted.

Keep students.txt in the application's working directory to preserve your data. If the file does not exist, the application starts with an empty student list.

Project Structure
-----------------

Student-Management-System/
├── StudentManagement.java
├── README.md
├── .gitignore
└── students.txt (created automatically)

Future Improvements
-------------------

Connect the application to a MySQL database.

Add a graphical user interface.

Export student reports to CSV.

Add student login and administrator access.
