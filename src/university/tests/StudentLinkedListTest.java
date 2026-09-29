package university.tests;

import university.model.Student;
import university.linkedlist.StudentLinkedList;

public class StudentLinkedListTest {

    public static void main(String[] args) {

        StudentLinkedList list = new StudentLinkedList();

        Student student1 =
                new Student(1001, "Nimal Silva", "IT", 78);

        Student student2 =
                new Student(1002, "Kamal Perera", "CS", 85);

        Student student3 =
                new Student(1003, "Amal Fernando", "SE", 67);

        Student duplicate = new Student(1001, "Another Student", "IT", 50);
        boolean added = list.add(duplicate);
        System.out.println("Duplicate student added: " + added);

        System.out.println("=== ADD STUDENTS ===");

        list.add(student1);
        list.add(student2);
        list.add(student3);

        System.out.println();
        System.out.println("=== DISPLAY STUDENTS ===");

        list.display();

        System.out.println();
        System.out.println("=== SEARCH STUDENT 1002 ===");

        Student found = list.search(1002);

        if (found != null) {
            System.out.println("Student found:");
            System.out.println(found);
        } else {
            System.out.println("Student not found.");
        }

        System.out.println();
        System.out.println("=== UPDATE STUDENT 1002 ===");

        boolean updated =
                list.update(1002, "Kamal Perera", "IT", 90);

        if (updated) {
            System.out.println("Student updated successfully.");
        }

        list.display();

        System.out.println();
        System.out.println("=== DELETE STUDENT 1003 ===");

        Student removed = list.remove(1003);

        if (removed != null) {
            System.out.println("Student deleted:");
            System.out.println(removed);
        }

        System.out.println();
        System.out.println("=== FINAL LIST ===");

        list.display();

        System.out.println();
        System.out.println("=== SEARCH MISSING STUDENT 9999 ===");

        Student missing = list.search(9999);

        if (missing == null) {
            System.out.println("Student 9999 not found.");
        }
    }
}