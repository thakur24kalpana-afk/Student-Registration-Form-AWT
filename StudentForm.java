import java.awt.*;
import java.awt.event.*;

public class StudentForm extends Frame implements ActionListener {

    Label l1, l2, l3, l4, l5;
    TextField t1, t2;
    Checkbox c1, c2;
    CheckboxGroup cbg;
    Choice course;
    TextArea ta;
    Button b1, b2;

    StudentForm() {

        setTitle("Student Registration Form");
        setSize(400, 500);
        setLayout(new BorderLayout());

        // Title
        Label title = new Label("Student Registration Form", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // Form Panel
        Panel p = new Panel();
        p.setLayout(new GridLayout(6, 2, 10, 10));

        l1 = new Label("Name:");
        t1 = new TextField();

        l2 = new Label("Roll No:");
        t2 = new TextField();

        l3 = new Label("Gender:");
        cbg = new CheckboxGroup();
        c1 = new Checkbox("Male", cbg, false);
        c2 = new Checkbox("Female", cbg, false);

        Panel genderPanel = new Panel();
        genderPanel.add(c1);
        genderPanel.add(c2);

        l4 = new Label("Course:");
        course = new Choice();
        course.add("BCA");
        course.add("BBA");
        course.add("B.Tech");
        course.add("MCA");

        l5 = new Label("Address:");
        ta = new TextArea(3, 20);

        p.add(l1);
        p.add(t1);
        p.add(l2);
        p.add(t2);
        p.add(l3);
        p.add(genderPanel);
        p.add(l4);
        p.add(course);
        p.add(l5);
        p.add(ta);

        add(p, BorderLayout.CENTER);

        // Buttons
        Panel btn = new Panel();
        b1 = new Button("Submit");
        b2 = new Button("Reset");

        btn.add(b1);
        btn.add(b2);

        add(btn, BorderLayout.SOUTH);

        // Events
        b1.addActionListener(this);
        b2.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1) {
            System.out.println("Submitted:");
            System.out.println("Name: " + t1.getText());
            System.out.println("Roll No: " + t2.getText());
            System.out.println("Course: " + course.getSelectedItem());
            System.out.println("Address: " + ta.getText());
        }

        if (e.getSource() == b2) {
            t1.setText("");
            t2.setText("");
            ta.setText("");
            cbg.setSelectedCheckbox(null);
        }
    }

    public static void main(String[] args) {
        new StudentForm();
    }
}
