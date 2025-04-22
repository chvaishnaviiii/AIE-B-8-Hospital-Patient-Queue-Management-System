import java.awt.*;
import javax.swing.*;

class Patient {
    String name;
    int id;
    int age;
    String gender;
    String condition;
    int severity;
    int conditionId;

    public Patient(String name, int id, int age, String gender, String condition, int severity, int conditionId) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.condition = condition;
        this.severity = severity;
        this.conditionId = conditionId;
    }

    public String toString() {
        return name + " (Age: " + age + ", Gender: " + gender +
                ", Condition: " + condition + ", Severity: " + severity + ")";
    }
}

class Doctor {
    String name;
    String specialization;

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public String toString() {
        return "Dr. " + name + " - " + specialization;
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
    Node head;

    public void insert(Patient p) {
        Node newNode = new Node(p);
        if (head == null || head.patient.severity > p.severity) {
            newNode.next = head;
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null && temp.next.patient.severity <= p.severity) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    public Patient delete() {
        if (head == null) return null;
        Patient p = head.patient;
        head = head.next;
        return p;
    }

    public String display() {
        if (head == null) return "Queue is empty.";
        Node temp = head;
        StringBuilder sb = new StringBuilder();
        while (temp != null) {
            sb.append(temp.patient).append("\n");
            temp = temp.next;
        }
        return sb.toString();
    }
}

public class HospitalQueueSwing extends JFrame {
    JTextField nameField, idField, ageField, genderField, conditionField, severityField, conditionIdField;
    JTextArea outputArea;
    LinkedList queue = new LinkedList();
    Doctor[] doctors = new Doctor[5];

    public HospitalQueueSwing() {
        setTitle("Hospital Queue Management");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(8, 2));
        form.add(new JLabel("Name:")); nameField = new JTextField(); form.add(nameField);
        form.add(new JLabel("ID:")); idField = new JTextField(); form.add(idField);
        form.add(new JLabel("Age:")); ageField = new JTextField(); form.add(ageField);
        form.add(new JLabel("Gender:")); genderField = new JTextField(); form.add(genderField);
        form.add(new JLabel("Condition:")); conditionField = new JTextField(); form.add(conditionField);
        form.add(new JLabel("Severity (1-3):")); severityField = new JTextField(); form.add(severityField);
        form.add(new JLabel("Condition ID (1-4):")); conditionIdField = new JTextField(); form.add(conditionIdField);

        JButton insertBtn = new JButton("Insert Patient");
        JButton serveBtn = new JButton("Serve Patient");
        JButton displayBtn = new JButton("Display Queue");
        form.add(insertBtn); form.add(serveBtn);

        add(form, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(displayBtn, BorderLayout.SOUTH);

        // Initialize doctors
        doctors[1] = new Doctor("Smith", "Cardiology");
        doctors[2] = new Doctor("Johnson", "Neurology");
        doctors[3] = new Doctor("Brown", "Orthopedics");
        doctors[4] = new Doctor("Taylor", "General Medicine");

        // Action Listeners
        insertBtn.addActionListener(e -> insertPatient());
        serveBtn.addActionListener(e -> servePatient());
        displayBtn.addActionListener(e -> displayQueue());
    }

    public void clearFields() {
        nameField.setText("");
        idField.setText("");
        ageField.setText("");
        genderField.setText("");
        conditionField.setText("");
        severityField.setText("");
        conditionIdField.setText("");
    }
    

    public void insertPatient() {
        try {
            String name = nameField.getText();
            int id = Integer.parseInt(idField.getText());
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getText();
            String condition = conditionField.getText();
            int severity = Integer.parseInt(severityField.getText());
            int conditionId = Integer.parseInt(conditionIdField.getText());
    
            Patient p = new Patient(name, id, age, gender, condition, severity, conditionId);
            queue.insert(p);
            outputArea.setText("Patient inserted successfully.\n");
            clearFields(); // clear the form after insertion
        } catch (Exception ex) {
            outputArea.setText("Invalid input. Please check all fields.\n");
        }
    }
    

    public void servePatient() {
        Patient p = queue.delete();
        if (p == null) {
            outputArea.setText("No patient to serve.\n");
            return;
        }

        outputArea.setText("Serving patient: " + p + "\n");
        if (p.conditionId >= 1 && p.conditionId <= 4) {
            outputArea.append("Assigned Doctor: " + doctors[p.conditionId] + "\n");
        } else {
            outputArea.append("No doctor available for condition ID: " + p.conditionId + "\n");
        }
    }

    public void displayQueue() {
        outputArea.setText(queue.display());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HospitalQueueSwing().setVisible(true);
        });
    }
}
