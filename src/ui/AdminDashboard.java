package ui;

import javax.swing.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin");
        setSize(200, 150);
        setLocationRelativeTo(null);
        add(new JLabel("Admin Dashboard", JLabel.CENTER));
        setVisible(true);
    }
}
