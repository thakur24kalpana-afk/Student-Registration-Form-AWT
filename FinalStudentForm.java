import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class FinalStudentForm extends JFrame implements ActionListener {

    JTextField nameT, contactT, searchT;
    JTextArea addressT, resultT;
    JRadioButton male, female;
    JComboBox<String> courseC;
    JButton submit, reset, searchBtn, clearSearchBtn;
    List<String> studentRecords;

    // Modern Color Palette
    private static final Color PRIMARY_BLUE = new Color(41, 128, 185);
    private static final Color DARK_BLUE = new Color(22, 82, 144);
    private static final Color LIGHT_BLUE = new Color(174, 214, 241);
    private static final Color VERY_LIGHT_BLUE = new Color(236, 245, 255);
    private static final Color SUCCESS_GREEN = new Color(39, 174, 96);
    private static final Color DANGER_RED = new Color(231, 76, 60);
    private static final Color TEXT_DARK = new Color(44, 62, 80);

    public FinalStudentForm() {
        setTitle("Student Registration Form");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setIconImage(createIcon());

        studentRecords = new ArrayList<>();

        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gradient = new GradientPaint(0, 0, VERY_LIGHT_BLUE,
                        getWidth(), getHeight(), LIGHT_BLUE);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout(30, 30));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Header Panel
        JPanel headerPanel = createHeaderPanel();

        // Content Panel with two columns
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridLayout(1, 2, 25, 0));

        // Left Panel - Form
        JPanel formPanel = createFormPanel();

        // Right Panel - Result
        JPanel resultPanel = createResultPanel();

        contentPanel.add(formPanel);
        contentPanel.add(resultPanel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());

        // Header background with blue gradient
        JPanel headerBg = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(0, 0, PRIMARY_BLUE,
                        getWidth(), getHeight(), DARK_BLUE);
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        headerBg.setOpaque(false);
        headerBg.setLayout(new BorderLayout());
        headerBg.setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Student Registration Form");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Complete your registration with ease");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(LIGHT_BLUE);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitle);

        headerBg.add(titlePanel, BorderLayout.WEST);
        panel.add(headerBg, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(0, 90));

        return panel;
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                // Shadow
                g2d.setColor(new Color(0, 0, 0, 20));
                for (int i = 0; i < 4; i++) {
                    g2d.drawRoundRect(i, i, getWidth() - 2 * i - 1, getHeight() - 2 * i - 1, 15, 15);
                }
            }
        };
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        // Full Name
        addFormField(panel, "Full Name", nameT = new JTextField());

        // Contact Number
        addFormField(panel, "Contact Number", contactT = new JTextField());

        // Gender
        JPanel genderPanel = new JPanel();
        genderPanel.setOpaque(false);
        genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.Y_AXIS));

        JLabel genderL = new JLabel("Gender");
        genderL.setFont(new Font("Segoe UI", Font.BOLD, 12));
        genderL.setForeground(PRIMARY_BLUE);
        genderPanel.add(genderL);
        genderPanel.add(Box.createVerticalStrut(8));

        JPanel radioPanel = new JPanel();
        radioPanel.setOpaque(false);
        radioPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));

        ButtonGroup genderGroup = new ButtonGroup();
        male = new JRadioButton("Male", true);
        female = new JRadioButton("Female");

        styleRadioButton(male);
        styleRadioButton(female);

        genderGroup.add(male);
        genderGroup.add(female);
        radioPanel.add(male);
        radioPanel.add(female);
        genderPanel.add(radioPanel);
        panel.add(genderPanel);
        panel.add(Box.createVerticalStrut(15));

        // Course
        JLabel courseL = new JLabel("Course");
        courseL.setFont(new Font("Segoe UI", Font.BOLD, 12));
        courseL.setForeground(PRIMARY_BLUE);
        panel.add(courseL);
        panel.add(Box.createVerticalStrut(8));

        courseC = new JComboBox<>(new String[] { "BTech", "BCA", "MCA", "MBA" });
        styleComboBox(courseC);
        panel.add(courseC);
        panel.add(Box.createVerticalStrut(15));

        // Address
        JLabel addressL = new JLabel("Address");
        addressL.setFont(new Font("Segoe UI", Font.BOLD, 12));
        addressL.setForeground(PRIMARY_BLUE);
        panel.add(addressL);
        panel.add(Box.createVerticalStrut(8));

        addressT = new JTextArea(4, 20);
        addressT.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        addressT.setLineWrap(true);
        addressT.setWrapStyleWord(true);
        addressT.setBorder(new LineBorder(LIGHT_BLUE, 2));
        addressT.setForeground(TEXT_DARK);
        addressT.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(addressT);
        scrollPane.setBorder(new LineBorder(LIGHT_BLUE, 2));
        scrollPane.getViewport().setBackground(Color.WHITE);
        panel.add(scrollPane);

        panel.add(Box.createVerticalStrut(20));

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        submit = createModernButton("Submit", SUCCESS_GREEN);
        reset = createModernButton("Reset", DANGER_RED);

        submit.addActionListener(this);
        reset.addActionListener(this);

        buttonPanel.add(submit);
        buttonPanel.add(reset);

        panel.add(buttonPanel);

        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                // Shadow
                g2d.setColor(new Color(0, 0, 0, 20));
                for (int i = 0; i < 4; i++) {
                    g2d.drawRoundRect(i, i, getWidth() - 2 * i - 1, getHeight() - 2 * i - 1, 15, 15);
                }
            }
        };
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        // Header with title
        JLabel resultL = new JLabel("Registration Result & Search");
        resultL.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultL.setForeground(PRIMARY_BLUE);

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setOpaque(false);
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel searchLabel = new JLabel("Search by Name/Contact/Course:");
        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        searchLabel.setForeground(TEXT_DARK);

        searchT = new JTextField(20);
        searchT.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        searchT.setBorder(new LineBorder(LIGHT_BLUE, 2));
        searchT.setMargin(new Insets(8, 10, 8, 10));
        searchT.setPreferredSize(new Dimension(200, 35));

        searchBtn = createModernButton("Search", PRIMARY_BLUE);
        searchBtn.addActionListener(this);

        clearSearchBtn = createModernButton("Show All", SUCCESS_GREEN);
        clearSearchBtn.addActionListener(this);

        searchPanel.add(searchLabel);
        searchPanel.add(searchT);
        searchPanel.add(searchBtn);
        searchPanel.add(clearSearchBtn);

        // Top panel with title and search
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(resultL);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(searchPanel);

        resultT = new JTextArea();
        resultT.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        resultT.setEditable(false);
        resultT.setLineWrap(true);
        resultT.setWrapStyleWord(true);
        resultT.setMargin(new Insets(15, 15, 15, 15));
        resultT.setForeground(TEXT_DARK);
        resultT.setBackground(VERY_LIGHT_BLUE);
        resultT.setText("Your registration details will appear here after submission...");

        JScrollPane scrollPane = new JScrollPane(resultT);
        scrollPane.setBorder(new LineBorder(LIGHT_BLUE, 2));
        scrollPane.getViewport().setBackground(VERY_LIGHT_BLUE);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void addFormField(JPanel panel, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(PRIMARY_BLUE);

        panel.add(label);
        panel.add(Box.createVerticalStrut(8));

        field.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        field.setBorder(new LineBorder(LIGHT_BLUE, 2));
        field.setForeground(TEXT_DARK);
        field.setCaretColor(PRIMARY_BLUE);
        field.setMargin(new Insets(8, 10, 8, 10));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        panel.add(field);
        panel.add(Box.createVerticalStrut(15));
    }

    private void styleRadioButton(JRadioButton rb) {
        rb.setOpaque(false);
        rb.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        rb.setForeground(TEXT_DARK);
        rb.setFocusable(false);
    }

    private void styleComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        combo.setBorder(new LineBorder(LIGHT_BLUE, 2));
        combo.setForeground(TEXT_DARK);
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        combo.setBackground(Color.WHITE);
    }

    private JButton createModernButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color drawColor = bgColor;
                if (getModel().isPressed()) {
                    drawColor = new Color(
                            Math.max(0, bgColor.getRed() - 40),
                            Math.max(0, bgColor.getGreen() - 40),
                            Math.max(0, bgColor.getBlue() - 40));
                } else if (getModel().isRollover()) {
                    drawColor = new Color(
                            Math.min(255, bgColor.getRed() + 40),
                            Math.min(255, bgColor.getGreen() + 40),
                            Math.min(255, bgColor.getBlue() + 40));
                }

                g2d.setColor(drawColor);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g2d.drawString(getText(), x, y);
            }
        };

        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(130, 45));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private Image createIcon() {
        BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(PRIMARY_BLUE);
        g2d.fillRect(0, 0, 32, 32);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 20));
        g2d.drawString("S", 8, 24);
        g2d.dispose();
        return img;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String name = nameT.getText().trim();
            String contact = contactT.getText().trim();
            String gender = male.isSelected() ? "Male" : "Female";
            String course = (String) courseC.getSelectedItem();
            String address = addressT.getText().trim();

            if (name.isEmpty() || contact.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields!",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Create student record
            String studentRecord = "Name: " + name + " | Contact: " + contact +
                    " | Gender: " + gender + " | Course: " + course +
                    " | Address: " + address;
            studentRecords.add(studentRecord);

            String result = "Registration Successful!\n\n" +
                    "==========================================\n" +
                    "Name: " + name + "\n" +
                    "Contact: " + contact + "\n" +
                    "Gender: " + gender + "\n" +
                    "Course: " + course + "\n" +
                    "Address: " + address + "\n" +
                    "==========================================\n\n" +
                    "Total Registrations: " + studentRecords.size();

            resultT.setText(result);

            // Clear form fields
            nameT.setText("");
            contactT.setText("");
            addressT.setText("");
            male.setSelected(true);
            searchT.setText("");
        }

        if (e.getSource() == reset) {
            nameT.setText("");
            contactT.setText("");
            addressT.setText("");
            resultT.setText("Your registration details will appear here after submission...");
            male.setSelected(true);
            searchT.setText("");
        }

        if (e.getSource() == searchBtn) {
            performSearch();
        }

        if (e.getSource() == clearSearchBtn) {
            displayAllRecords();
        }
    }

    private void performSearch() {
        String searchQuery = searchT.getText().trim().toLowerCase();

        if (searchQuery.isEmpty()) {
            displayAllRecords();
            return;
        }

        if (studentRecords.isEmpty()) {
            resultT.setText("No student records found. Please register students first.");
            return;
        }

        StringBuilder result = new StringBuilder();
        result.append("SEARCH RESULTS FOR: \"").append(searchQuery).append("\"\n");
        result.append("==========================================\n\n");

        int matchCount = 0;
        for (String record : studentRecords) {
            if (record.toLowerCase().contains(searchQuery)) {
                result.append(record).append("\n");
                result.append("------------------------------------------\n");
                matchCount++;
            }
        }

        if (matchCount == 0) {
            result.append("No students found matching: \"").append(searchQuery).append("\"");
        } else {
            result.append("\nTotal Matches: ").append(matchCount);
        }

        resultT.setText(result.toString());
    }

    private void displayAllRecords() {
        if (studentRecords.isEmpty()) {
            resultT.setText("No student records found. Please register students first.");
            return;
        }

        StringBuilder result = new StringBuilder();
        result.append("ALL REGISTERED STUDENTS\n");
        result.append("==========================================\n\n");

        for (int i = 0; i < studentRecords.size(); i++) {
            result.append((i + 1)).append(". ").append(studentRecords.get(i)).append("\n");
            result.append("------------------------------------------\n");
        }

        result.append("\nTotal Students: ").append(studentRecords.size());
        resultT.setText(result.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FinalStudentForm());
    }
}