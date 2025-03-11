import java.util.Scanner;

class Patient {
    String name;
    int id;
    int age;
    String gender;
    String condition;
    int severity;

    public Patient(String name, int id, int age, String gender, String condition, int severity) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.condition = condition;
        this.severity = severity;
    }

    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Gender: " + gender +
                ", Condition: " + condition + ", Severity: " + severity;
    }
}

class Node {
    Patient patient;
    Node next;

    public Node(Patient patient) {
        this.patient = patient;
        this.next = null;
    }
}

class LinkedList {
    private Node head;

    public void insert(String name, int id, int age, String gender, String condition, int severity) {
        Patient newPatient = new Patient(name, id, age, gender, condition, severity);
        Node newNode = new Node(newPatient);

        if (head == null || head.patient.severity > severity) {
            newNode.next = head;
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null && temp.next.patient.severity <= severity) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    public Patient delete() {
        if (head == null) {
            System.out.println("Queue is empty");
            return null;
        }
        Patient removedPatient = head.patient;
        head = head.next;
        return removedPatient;
    }

    public void display() {
        if (head == null) {
            System.out.println("Queue is empty");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.patient + " --> ");
            temp = temp.next;
        }
        System.out.println("None");
    }
}

class PriorityQueue {
    private LinkedList queue = new LinkedList();
    private Scanner scanner = new Scanner(System.in);

    public void enqueue() {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Patient Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Patient Condition: ");
        String condition = scanner.nextLine();
        System.out.print("Enter Severity Level (1 for high severity and 3 for low severity): ");
        int severity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        queue.insert(name, id, age, gender, condition, severity);
    }

    public void dequeue() {
        Patient removedPatient = queue.delete();
        if (removedPatient != null) {
            System.out.println("Removed Patient: " + removedPatient);
        }
    }

    public void display() {
        queue.display();
    }
}

public class HospitalQueueManagement {
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        pq.enqueue();
        pq.display();
        pq.enqueue();
        pq.enqueue();
        pq.display();
    }
}


