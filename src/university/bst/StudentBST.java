package university.bst;

import university.model.Student;

public class StudentBST {

    private static class Node {
        Student data;
        Node left;
        Node right;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node root;

    public boolean insert(Student student) {
        if (search(student.getStudentId()) != null) {
            return false;
        }

        root = insertRecursive(root, student);
        return true;
    }

    private Node insertRecursive(Node node, Student student) {

        if (node == null) {
            return new Node(student);
        }

        if (student.getStudentId() < node.data.getStudentId()) {
            node.left = insertRecursive(node.left, student);
        } else {
            node.right = insertRecursive(node.right, student);
        }

        return node;
    }

    public Student search(int studentId) {
        Node current = root;

        while (current != null) {

            if (studentId == current.data.getStudentId()) {
                return current.data;
            }

            if (studentId < current.data.getStudentId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public boolean delete(int studentId) {

        if (search(studentId) == null) {
            return false;
        }

        root = deleteRecursive(root, studentId);
        return true;
    }

    private Node deleteRecursive(Node node, int studentId) {

        if (node == null) {
            return null;
        }

        if (studentId < node.data.getStudentId()) {

            node.left = deleteRecursive(node.left, studentId);

        } else if (studentId > node.data.getStudentId()) {

            node.right = deleteRecursive(node.right, studentId);

        } else {

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMinimum(node.right);

            node.data = successor.data;

            node.right =
                    deleteRecursive(
                            node.right,
                            successor.data.getStudentId()
                    );
        }

        return node;
    }

    private Node findMinimum(Node node) {

        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("No students found in BST.");
            return;
        }

        inOrder(root);
    }

    private void inOrder(Node node) {

        if (node == null) {
            return;
        }

        inOrder(node.left);

        System.out.println(node.data);

        inOrder(node.right);
    }
}