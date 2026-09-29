package university.tests;

import university.hashing.StudentHashTable;
import university.model.Student;

public class StudentHashTableTest {

    public static void main(String[] args) {

        StudentHashTable hashTable =
                new StudentHashTable(10);

        Student student1 =
                new Student(1001, "Nimal Silva", "IT", 78);

        Student student2 =
                new Student(1002, "Kamal Perera", "CS", 85);

        Student student3 =
                new Student(1011, "Amal Fernando", "SE", 67);

        System.out.println("=== ADD STUDENTS ===");

        System.out.println(
                "1001 added: " + hashTable.put(student1)
        );

        System.out.println(
                "1002 added: " + hashTable.put(student2)
        );

        System.out.println(
                "1011 added: " + hashTable.put(student3)
        );

        System.out.println();

        System.out.println("=== HASH TABLE ===");

        hashTable.display();

        System.out.println();

        System.out.println("=== SEARCH STUDENT 1002 ===");

        Student found = hashTable.get(1002);

        if (found != null) {
            System.out.println("Student found:");
            System.out.println(found);
        } else {
            System.out.println("Student not found.");
        }

        System.out.println();

        System.out.println("=== SEARCH STUDENT 9999 ===");

        Student missing = hashTable.get(9999);

        if (missing == null) {
            System.out.println("Student 9999 not found.");
        }

        System.out.println();

        System.out.println("=== DELETE STUDENT 1002 ===");

        if (hashTable.remove(1002)) {
            System.out.println(
                    "Student 1002 deleted successfully."
            );
        }

        System.out.println();

        System.out.println("=== SEARCH 1002 AFTER DELETE ===");

        if (hashTable.get(1002) == null) {
            System.out.println(
                    "Student 1002 not found after deletion."
            );
        }

        System.out.println();

        System.out.println("=== DUPLICATE TEST ===");

        Student duplicate =
                new Student(1001, "Another Student", "IT", 50);

        System.out.println(
                "Duplicate added: "
                        + hashTable.put(duplicate)
        );
    }
}