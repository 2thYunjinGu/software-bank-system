package View;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Login");
        setSize(500, 200); // 设置窗口大小
        setLocationRelativeTo(null); // 窗口居中
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // 不使用现成布局

        // 添加组件
        JPanel p_1 = new JPanel();
        JPanel p_2 = new JPanel();
        JPanel p_3 = new JPanel();

        p_1.setBounds(0, 0, 500, 50);
        p_2.setBounds(0, 50, 500, 50);
        p_3.setBounds(0, 100, 500, 50);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        Dimension dim = new Dimension(400,35);
        usernameField.setPreferredSize(dim);
        passwordField.setPreferredSize(dim);

        p_1.add(usernameLabel);
        p_1.add(usernameField);
        p_2.add(passwordLabel);
        p_2.add(passwordField);
        p_3.add(loginButton);
        p_3.add(registerButton);

        add(p_1);
        add(p_2);
        add(p_3);


        setVisible(true);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}