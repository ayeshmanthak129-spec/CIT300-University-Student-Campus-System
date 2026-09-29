package university.hashing;

import university.model.Student;

public class StudentHashTable {

    private static class Entry {
        Student data;
        Entry next;

        Entry(Student data) {
            this.data = data;
        }
    }

    private final Entry[] table;

    public StudentHashTable(int size) {
        table = new Entry[size];
    }

    private int hash(int studentId) {
        return Math.abs(studentId) % table.length;
    }

    public boolean put(Student student) {

        if (get(student.getStudentId()) != null) {
            return false;
        }

        int index = hash(student.getStudentId());

        Entry newEntry = new Entry(student);

        if (table[index] == null) {
            table[index] = newEntry;
            return true;
        }

        Entry current = table[index];

        while (current.next != null) {
            current = current.next;
        }

        current.next = newEntry;

        return true;
    }

    public Student get(int studentId) {

        int index = hash(studentId);

        Entry current = table[index];

        while (current != null) {

            if (current.data.getStudentId() == studentId) {
                return current.data;
            }

            current = current.next;
        }

        return null;
    }

    public boolean remove(int studentId) {

        int index = hash(studentId);

        Entry current = table[index];
        Entry previous = null;

        while (current != null) {

            if (current.data.getStudentId() == studentId) {

                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    public void display() {

        for (int i = 0; i < table.length; i++) {

            System.out.print("Bucket " + i + ": ");

            Entry current = table[i];

            if (current == null) {
                System.out.println("empty");
                continue;
            }

            while (current != null) {

                System.out.print(
                        current.data.getStudentId()
                                + " -> "
                );

                current = current.next;
            }

            System.out.println("null");
        }
    }
}