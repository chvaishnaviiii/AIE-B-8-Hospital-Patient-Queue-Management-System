# 🏥 Hospital Management System with Priority Queue

This project is a **hospital management simulation** that uses a **priority queue (linked list implementation)** to manage patients based on the **severity of their condition**.  
It also assigns doctors dynamically depending on the patient’s condition type.

---

## ✨ Features
- **Patient management** with details like:
  - Name, Age, Gender, Condition, Severity, Condition ID
- **Priority-based queueing system**:
  - Patients with higher severity are prioritized.
- **Doctor assignment** based on medical specialization.
- **Queue operations**:
  - `enqueue` → Add a patient (automatically ordered by severity).
  - `dequeue` → Remove and return the highest-priority patient.
  - `display` → View all patients in the queue (in order).
- Clear **object-oriented design** with `Patient`, `Doctor`, `Hospital`, and `PriorityQueue` classes.


---

## ✅ Example Flow
- Enqueue patients with different severity levels.
- The queue automatically orders them (critical cases first).
- When a patient is dequeued, they are assigned to the relevant doctor.

---

⚡ A simple yet powerful way to simulate **real hospital emergency triage**!
