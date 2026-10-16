
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    double marks;

    Student(int rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    String getGrade() {
        if (marks >= 90) return "A";
        if (marks >= 75) return "B";
        if (marks >= 60) return "C";
        if (marks >= 40) return "D";
        return "Fail";
    }
}

public class StudentManagement {

    static ArrayList<Student> students = new ArrayList<>();
    static final String FILE_NAME = "students.txt";

    static void loadStudents() {
        Path path = Path.of(FILE_NAME);

        if (!Files.exists(path)) {
            System.out.println("Starting with an empty student list.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|", 3);

                if (data.length != 3) continue;

                try {
                    int roll = Integer.parseInt(data[0]);
                    String name = data[1];
                    double marks = Double.parseDouble(data[2]);

                    if (roll > 0 && !name.isBlank()
                            && !name.contains("|")
                            && Double.isFinite(marks)
                            && marks >= 0 && marks <= 100) {

                        boolean duplicate = false;

                        for (Student s : students) {
                            if (s.rollNo == roll) {
                                duplicate = true;
                                break;
                            }
                        }

                        if (!duplicate) {
                            students.add(new Student(roll, name, marks));
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping an invalid record.");
                }
            }

            System.out.println("Loaded " + students.size() + " student(s).");

        } catch (IOException e) {
            System.out.println("Error loading records: " + e.getMessage());
        }
    }

    static boolean saveStudents() {
        try (BufferedWriter writer = Files.newBufferedWriter(
                Path.of(FILE_NAME))) {

            for (Student s : students) {
                writer.write(s.rollNo + "|" + s.name + "|" + s.marks);
                writer.newLine();
            }

            return true;

        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
            return false;
        }
    }

    static Student findStudent(int rollNo) {
        for (Student s : students) {
            if (s.rollNo == rollNo) {
                return s;
            }
        }
        return null;
    }

    static int readInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            }

            System.out.println("Please enter a valid whole number.");
            sc.nextLine();
        }
    }

    static double readMarks(Scanner sc) {
        while (true) {
            System.out.print("Enter marks (0-100): ");

            if (sc.hasNextDouble()) {
                double marks = sc.nextDouble();
                sc.nextLine();

                if (Double.isFinite(marks)
                        && marks >= 0 && marks <= 100) {
                    return marks;
                }
            } else {
                sc.nextLine();
            }

            System.out.println("Marks must be between 0 and 100.");
        }
    }

    static String readName(Scanner sc) {
        while (true) {
            System.out.print("Enter student name: ");
            String name = sc.nextLine().trim();

            if (!name.isEmpty() && !name.contains("|")) {
                return name;
            }

            System.out.println("Enter a valid name.");
        }
    }

    static void addStudent(Scanner sc) {
        int roll = readInt(sc, "Enter roll number: ");

        if (roll <= 0) {
            System.out.println("Roll number must be positive.");
            return;
        }

        if (findStudent(roll) != null) {
            System.out.println("Roll number already exists.");
            return;
        }

        String name = readName(sc);
        double marks = readMarks(sc);

        students.add(new Student(roll, name, marks));

        if (saveStudents()) {
            System.out.println("Student added and saved successfully!");
        } else {
            students.remove(students.size() - 1);
            System.out.println("Student was not saved.");
        }
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n----- STUDENT RECORDS -----");

        for (Student s : students) {
            System.out.println("Roll Number: " + s.rollNo);
            System.out.println("Name: " + s.name);
            System.out.println("Marks: " + s.marks);
            System.out.println("Grade: " + s.getGrade());
            System.out.println("---------------------------");
        }
    }

    static void searchStudent(Scanner sc) {
        int roll = readInt(sc, "Enter roll number to search: ");
        Student s = findStudent(roll);

        if (s == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Roll Number: " + s.rollNo);
            System.out.println("Name: " + s.name);
            System.out.println("Marks: " + s.marks);
            System.out.println("Grade: " + s.getGrade());
        }
    }

    static void updateStudent(Scanner sc) {
        int roll = readInt(sc, "Enter roll number to update: ");
        Student s = findStudent(roll);

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        String oldName = s.name;
        double oldMarks = s.marks;

        System.out.println("Enter the updated details:");
        s.name = readName(sc);
        s.marks = readMarks(sc);

        if (saveStudents()) {
            System.out.println("Student updated successfully!");
        } else {
            s.name = oldName;
            s.marks = oldMarks;
            System.out.println("Update failed. Original details restored.");
        }
    }

    static void deleteStudent(Scanner sc) {
        int roll = readInt(sc, "Enter roll number to delete: ");
        Student s = findStudent(roll);

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(s);

        if (saveStudents()) {
            System.out.println("Student deleted successfully!");
        } else {
            students.add(s);
            System.out.println("Delete failed. Record restored.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        loadStudents();

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            int choice = readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent(sc);
                    break;
                case 4:
                    updateStudent(sc);
                    break;
                case 5:
                    deleteStudent(sc);
                    break;
                case 6:
                    System.out.println("Thank you! Exiting program.");
                    sc.close();
                    return;
                default:
                    System.out.println("Choose an option from 1 to 6.");
            }
        }
    }
}
