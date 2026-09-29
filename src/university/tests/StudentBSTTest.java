package university.tests;

import university.bst.StudentBST;
import university.model.Student;

public class StudentBSTTest {

    public static void main(String[] args) {

        StudentBST bst = new StudentBST();

        Student student1 =
                new Student(1003, "Amal Fernando", "SE", 67);

        Student student2 =
                new Student(1001, "Nimal Silva", "IT", 78);

        Student student3 =
                new Student(1005, "Kamal Perera", "CS", 85);

        Student student4 =
                new Student(1002, "Saman Perera", "IT", 72);

        System.out.println("=== INSERT STUDENTS ===");

        System.out.println("1003: " + bst.insert(student1));
        System.out.println("1001: " + bst.insert(student2));
        System.out.println("1005: " + bst.insert(student3));
        System.out.println("1002: " + bst.insert(student4));

        System.out.println();

        System.out.println("=== BST IN-ORDER DISPLAY ===");

        bst.displayInOrder();

        System.out.println();

        System.out.println("=== SEARCH STUDENT 1005 ===");

        Student found = bst.search(1005);

        if (found != null) {
            System.out.println("Student found:");
            System.out.println(found);
        } else {
            System.out.println("Student not found.");
        }

        System.out.println();

        System.out.println("=== SEARCH STUDENT 9999 ===");

        Student missing = bst.search(9999);

        if (missing == null) {
            System.out.println("Student 9999 not found.");
        }

        System.out.println();

        System.out.println("=== DELETE STUDENT 1003 ===");

        if (bst.delete(1003)) {
            System.out.println("Student 1003 deleted successfully.");
        }

        System.out.println();

        System.out.println("=== BST AFTER DELETE ===");

        bst.displayInOrder();

        System.out.println();

        System.out.println("=== DUPLICATE TEST ===");

        Student duplicate =
                new Student(1001, "Another Student", "IT", 50);

        System.out.println(
                "Duplicate added: " + bst.insert(duplicate)
        );
    }
}