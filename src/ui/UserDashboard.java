package ui;

import javax.swing.*;

public class UserDashboard extends JFrame {

    public UserDashboard() {
        setTitle("User");
        setSize(200, 150);
        setLocationRelativeTo(null);
        add(new JLabel("User Dashboard", JLabel.CENTER));
        setVisible(true);
    }
}
