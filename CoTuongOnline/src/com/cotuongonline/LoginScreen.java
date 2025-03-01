package com.cotuongonline;
import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    public LoginScreen() {
        // Cấu hình cửa sổ đăng nhập
        setTitle("Đăng Nhập");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel usernameLabel = new JLabel("Tên tài khoản:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Đăng nhập");

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginScreen login = new LoginScreen();
                login.setVisible(true);
            }
        });
    }
}
