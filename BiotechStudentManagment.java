import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Student implements Serializable {
    private String name, rollNo;
    private Map<String, String> subjectGrades;

    public Student(String name, String rollNo, Map<String, String> subjectGrades) {
        this.name = name;
        this.rollNo = rollNo;
        this.subjectGrades = subjectGrades;
    }

    public String getName() { return name; }
    public String getRollNo() { return rollNo; }
    public Map<String, String> getSubjectGrades() { return subjectGrades; }

    public void setName(String name) { this.name = name; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public void setSubjectGrades(Map<String, String> subjectGrades) { this.subjectGrades = subjectGrades; }

    @Override
    public String toString() {
        return name + " (" + rollNo + ")";
    }
}

public class StudentManagementSystem extends JFrame {
    private List<Student> students = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> studentJList = new JList<>(listModel);

    private JTextField nameField = new JTextField(15);
    private JTextField rollField = new JTextField(15);
    private JTextField subject1Field = new JTextField(15);
    private JTextField subject2Field = new JTextField(15);
    private JTextField subject3Field = new JTextField(15);

    private JButton addButton = new JButton("Add Student");
    private JButton editButton = new JButton("Edit");
    private JButton deleteButton = new JButton("Delete");
    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");

    private boolean isTeacher = false;

    public StudentManagementSystem() {
        super("Student Management System");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        roleSelection();

        // UI Layout
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new TitledBorder("Student Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addInputRow(inputPanel, gbc, 0, "Name:", nameField, "Roll No:", rollField);
        addInputRow(inputPanel, gbc, 1, "Maths Grade:", subject1Field, "Physics Grade:", subject2Field);
        addInputRow(inputPanel, gbc, 2, "Chemistry Grade:", subject3Field, "", null);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton); buttonPanel.add(editButton);
        buttonPanel.add(deleteButton); buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        JScrollPane listScroll = new JScrollPane(studentJList);
        listScroll.setBorder(new TitledBorder("Student Records"));

        add(inputPanel, BorderLayout.NORTH);
        add(listScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Listeners
        addButton.addActionListener(e -> addStudent());
        editButton.addActionListener(e -> editStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        saveButton.addActionListener(e -> saveData());
        loadButton.addActionListener(e -> loadData());
        studentJList.addListSelectionListener(e -> fillFields());

        // Role-based button access
        setButtonsEnabled(isTeacher);
    }

    private void roleSelection() {
        String[] options = { "Teacher", "Student" };
        int choice = JOptionPane.showOptionDialog(this, "Are you a teacher or a student?", "Select Role",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            // Teacher - login
            String user = JOptionPane.showInputDialog(this, "Enter Admin Username:");
            String pass = JOptionPane.showInputDialog(this, "Enter Admin Password:");
            if ("admin".equals(user) && "1234".equals(pass)) {
                isTeacher = true;
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials. Running in student mode.");
            }
        } else {
            isTeacher = false;
        }
    }

    private void addInputRow(JPanel panel, GridBagConstraints gbc, int row,
                             String label1, JTextField field1, String label2, JTextField field2) {
        gbc.gridy = row;
        gbc.gridx = 0;
        if (!label1.isEmpty()) panel.add(new JLabel(label1), gbc);
        gbc.gridx = 1;
        if (field1 != null) panel.add(field1, gbc);
        gbc.gridx = 2;
        if (!label2.isEmpty()) panel.add(new JLabel(label2), gbc);
        gbc.gridx = 3;
        if (field2 != null) panel.add(field2, gbc);
    }

    private void setButtonsEnabled(boolean enabled) {
        addButton.setEnabled(enabled);
        editButton.setEnabled(enabled);
        deleteButton.setEnabled(enabled);
        saveButton.setEnabled(enabled);
        nameField.setEditable(enabled);
        rollField.setEditable(enabled);
        subject1Field.setEditable(enabled);
        subject2Field.setEditable(enabled);
        subject3Field.setEditable(enabled);
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();
        String math = subject1Field.getText().trim();
        String phy = subject2Field.getText().trim();
        String chem = subject3Field.getText().trim();

        if (name.isEmpty() || roll.isEmpty() || math.isEmpty() || phy.isEmpty() || chem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all fields.");
            return;
        }

        Map<String, String> subjectGrades = new HashMap<>();
        subjectGrades.put("Maths", math);
        subjectGrades.put("Physics", phy);
        subjectGrades.put("Chemistry", chem);

        Student student = new Student(name, roll, subjectGrades);
        students.add(student);
        listModel.addElement(student.toString());
        clearFields();
    }

    private void editStudent() {
        int index = studentJList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Select a student to edit.");
            return;
        }

        Student student = students.get(index);
        student.setName(nameField.getText());
        student.setRollNo(rollField.getText());

        Map<String, String> subjectGrades = new HashMap<>();
        subjectGrades.put("Maths", subject1Field.getText());
        subjectGrades.put("Physics", subject2Field.getText());
        subjectGrades.put("Chemistry", subject3Field.getText());
        student.setSubjectGrades(subjectGrades);

        listModel.set(index, student.toString());
        clearFields();
    }

    private void deleteStudent() {
        int index = studentJList.getSelectedIndex();
        if (index == -1) return;

        students.remove(index);
        listModel.remove(index);
        clearFields();
    }

    private void fillFields() {
        int index = studentJList.getSelectedIndex();
        if (index == -1) return;

        Student student = students.get(index);
        nameField.setText(student.getName());
        rollField.setText(student.getRollNo());

        Map<String, String> grades = student.getSubjectGrades();
        subject1Field.setText(grades.get("Maths"));
        subject2Field.setText(grades.get("Physics"));
        subject3Field.setText(grades.get("Chemistry"));
    }

    private void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            out.writeObject(students);
            JOptionPane.showMessageDialog(this, "Data Saved!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Save Error: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.dat"))) {
            students = (List<Student>) in.readObject();
            listModel.clear();
            for (Student s : students) listModel.addElement(s.toString());
            clearFields();
            JOptionPane.showMessageDialog(this, "Data Loaded!");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Load Error: " + ex.getMessage());
        }
    }

    private void clearFields() {
        nameField.setText(""); rollField.setText("");
        subject1Field.setText(""); subject2Field.setText(""); subject3Field.setText("");
        studentJList.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagementSystem().setVisible(true));
    }
}
