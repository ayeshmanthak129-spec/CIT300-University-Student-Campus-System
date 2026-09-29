package university.linkedlist;

import university.model.Student;

public class StudentLinkedList {

    private Node head;

    private static class Node {
        Student student;
        Node next;

        Node(Student student) {
            this.student = student;
            this.next = null;
        }
    }

    public boolean addStudent(Student student) {

        if (student == null) {
            return false;
        }

        if (searchStudent(student.getStudentId()) != null) {
            return false;
        }

        Node newNode = new Node(student);

        if (head == null) {
            head = newNode;
            return true;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        return true;
    }

    public Student searchStudent(int studentId) {

        Node current = head;

        while (current != null) {

            if (current.student.getStudentId() == studentId) {
                return current.student;
            }

            current = current.next;
        }

        return null;
    }

    public boolean updateStudent(int studentId,
                                 String name,
                                 String programme,
                                 double marks) {

        Student student = searchStudent(studentId);

        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setProgramme(programme);
        student.setMarks(marks);

        return true;
    }

    public boolean deleteStudent(int studentId) {

        if (head == null) {
            return false;
        }

        if (head.student.getStudentId() == studentId) {
            head = head.next;
            return true;
        }

        Node current = head;

        while (current.next != null) {

            if (current.next.student.getStudentId() == studentId) {
                current.next = current.next.next;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public void displayStudents() {

        if (head == null) {
            System.out.println("No student records found.");
            return;
        }

        Node current = head;

        while (current != null) {
            System.out.println(current.student);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {

        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }
}