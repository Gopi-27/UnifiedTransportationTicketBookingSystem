package ui;

import javax.swing.*;
import dao.UserDAO;

public class AuthFrame extends JFrame {

    JTextField user;
    JPasswordField pass, confirm;
    JButton login, register, back;
    JLabel l1,l2,l3;
    boolean isRegister = false;

    public AuthFrame() {

        setTitle("Login");
        setSize(300, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        l1 = new JLabel("Username");
        l2 = new JLabel("Password");
        l3 = new JLabel("Confirm");

        user = new JTextField();
        pass = new JPasswordField();
        confirm = new JPasswordField();

        login = new JButton("Login");
        register = new JButton("Register");
        back = new JButton("Back");

        l1.setBounds(30, 30, 80, 25);
        user.setBounds(120, 30, 120, 25);

        l2.setBounds(30, 70, 80, 25);
        pass.setBounds(120, 70, 120, 25);

        l3.setBounds(30, 110, 80, 25);
        confirm.setBounds(120, 110, 120, 25);

        login.setBounds(30, 150, 100, 30);
        register.setBounds(160, 150, 100, 30);
        back.setBounds(30, 150, 100, 30);


        add(l1); add(user);
        add(l2); add(pass);
        add(login); add(register);
        add(l3); add(confirm); add(back);

        l3.setVisible(false);
        confirm.setVisible(false);
        back.setVisible(false);

        login.addActionListener(e -> doLogin());
        register.addActionListener(e -> handleRegister());
        back.addActionListener(e -> backToLogin());

        setVisible(true);
    }

    void doLogin() {

        String u = user.getText();
        String p = new String(pass.getPassword());

        String role = UserDAO.login(u, p);

        if (role == null) {
            JOptionPane.showMessageDialog(this, "Invalid Login");
            return;
        }

        dispose();

        if (role.equals("ADMIN"))
            new AdminDashboard();
        else
            new UserDashboard();
    }

    void handleRegister() {

        if (!isRegister) {
            setTitle("Register");
            isRegister = true;
            l3.setVisible(true);
            confirm.setVisible(true);
            back.setVisible(true);
            login.setVisible(false);
            return;
        }

        String u = user.getText();
        String p = new String(pass.getPassword());
        String c = new String(confirm.getPassword());

        if (u.isEmpty() || p.isEmpty() || c.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields required");
            return;
        }

        if (!p.equals(c)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match");
            return;
        }

        boolean success = UserDAO.register(u, p);

        if (!success) {
            JOptionPane.showMessageDialog(this, "Username already exists");
            return;
        }

        JOptionPane.showMessageDialog(this, "Registration successful");
        backToLogin();
    }

    void backToLogin() {

        setTitle("Login");
        isRegister = false;
        l3.setVisible(false);
        confirm.setVisible(false);
        back.setVisible(false);
        login.setVisible(true);

        user.setText("");
        pass.setText("");
        confirm.setText("");
    }

    public static void main(String[] args) {
        new AuthFrame();
    }
}
