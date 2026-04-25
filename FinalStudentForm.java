import java.awt.*;
import java.awt.event.*;

public class FinalStudentForm extends Frame implements ActionListener {

    TextField nameField, contactField;
    TextArea addressArea, resultArea;
    Checkbox male, female;
    Choice courseChoice;
    Button submitBtn, resetBtn;

    FinalStudentForm() {

        setTitle("Student Registration Form");
        setLayout(null);
        setBackground(new Color(240, 240, 240));
        setExtendedState(Frame.MAXIMIZED_BOTH);

        // HEADER
        Label header = new Label("Student Registration Form");
        header.setBounds(0, 40, 1500, 60);
        header.setBackground(new Color(0, 102, 204));
        header.setForeground(Color.WHITE);
        header.setAlignment(Label.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        add(header);

        // LEFT PANEL
        Panel formPanel = new Panel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBounds(200, 150, 500, 500);
        add(formPanel);

        Label formTitle = new Label("Registration Form");
        formTitle.setBounds(20, 20, 200, 30);
        formPanel.add(formTitle);

        Label name = new Label("Full Name:");
        name.setBounds(20, 70, 100, 30);
        formPanel.add(name);

        nameField = new TextField();
        nameField.setBounds(150, 70, 300, 30);
        formPanel.add(nameField);

        Label contact = new Label("Contact:");
        contact.setBounds(20, 110, 100, 30);
        formPanel.add(contact);

        contactField = new TextField();
        contactField.setBounds(150, 110, 300, 30);
        formPanel.add(contactField);

        Label gender = new Label("Gender:");
        gender.setBounds(20, 150, 100, 30);
        formPanel.add(gender);

        CheckboxGroup cbg = new CheckboxGroup();
        male = new Checkbox("Male", cbg, false);
        female = new Checkbox("Female", cbg, false);

        male.setBounds(150, 150, 70, 30);
        female.setBounds(230, 150, 90, 30);
        formPanel.add(male);
        formPanel.add(female);

        Label course = new Label("Course:");
        course.setBounds(20, 190, 100, 30);
        formPanel.add(course);

        courseChoice = new Choice();
        courseChoice.add("BTech");
        courseChoice.add("BCA");
        courseChoice.add("MCA");
        courseChoice.add("BSc");
        courseChoice.setBounds(150, 190, 300, 30);
        formPanel.add(courseChoice);

        Label address = new Label("Address:");
        address.setBounds(20, 230, 100, 30);
        formPanel.add(address);

        // 🔥 NO SCROLL
        addressArea = new TextArea("", 3, 30, TextArea.SCROLLBARS_NONE);
        addressArea.setBounds(150, 230, 300, 80);
        formPanel.add(addressArea);

        // BUTTONS
        submitBtn = new Button("Submit");
        submitBtn.setBounds(150, 350, 120, 40);
        submitBtn.setBackground(new Color(0, 102, 204));
        submitBtn.setForeground(Color.WHITE);
        formPanel.add(submitBtn);

        resetBtn = new Button("Reset");
        resetBtn.setBounds(300, 350, 120, 40);
        resetBtn.setBackground(new Color(0, 102, 204));
        resetBtn.setForeground(Color.WHITE);
        formPanel.add(resetBtn);

        // RIGHT PANEL
        Panel resultPanel = new Panel();
        resultPanel.setLayout(null);
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setBounds(800, 150, 500, 500);
        add(resultPanel);

        Label resultTitle = new Label("Registration Result");
        resultTitle.setBounds(20, 20, 200, 30);
        resultPanel.add(resultTitle);

        // 🔥 NO SCROLL
        resultArea = new TextArea("", 10, 50, TextArea.SCROLLBARS_NONE);
        resultArea.setBounds(20, 70, 450, 380);
        resultPanel.add(resultArea);

        // ACTIONS
        submitBtn.addActionListener(this);
        resetBtn.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitBtn) {

            String gender = male.getState() ? "Male" : "Female";

            resultArea.append(
                    "Name: " + nameField.getText() + "\n" +
                            "Contact: " + contactField.getText() + "\n" +
                            "Gender: " + gender + "\n" +
                            "Course: " + courseChoice.getSelectedItem() + "\n" +
                            "Address: " + addressArea.getText() + "\n" +
                            "-----------------------------\n");

            nameField.setText("");
            contactField.setText("");
            addressArea.setText("");
        }

        if (e.getSource() == resetBtn) {
            nameField.setText("");
            contactField.setText("");
            addressArea.setText("");
            resultArea.setText("");
        }
    }

    public static void main(String[] args) {
        new FinalStudentForm();
    }
}