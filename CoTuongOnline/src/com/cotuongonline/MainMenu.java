package com.cotuongonline;
import com.cotuongonline.LeaderboardScreen;
import com.cotuongonline.LoginScreen;
import com.cotuongonline.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {
        // Cấu hình cửa sổ chính
        setTitle("Cờ Tướng Online");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Tạo các nút
        JButton offlineButton = new JButton("Chơi Offline");
        JButton onlineButton = new JButton("Chơi Online");
        JButton leaderboardButton = new JButton("Xem Bảng Xếp Hạng");

        // Xử lý sự kiện khi người dùng bấm nút
        offlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đóng cửa sổ MainMenu và mở cửa sổ chơi offline
                dispose();
                OfflineGame game = new OfflineGame();
                game.setVisible(true);
            }
        });

        onlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đóng cửa sổ MainMenu và mở cửa sổ đăng nhập online
                dispose();
                LoginScreen login = new LoginScreen();
                login.setVisible(true);
            }
        });

        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đóng cửa sổ MainMenu và mở cửa sổ bảng xếp hạng
                dispose();
                LeaderboardScreen leaderboard = new LeaderboardScreen();
                leaderboard.setVisible(true);
            }
        });

        // Thêm các nút vào cửa sổ
        add(offlineButton);
        add(onlineButton);
        add(leaderboardButton);
    }

    public static void main(String[] args) {
        // Tạo và hiển thị giao diện
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainMenu menu = new MainMenu();
                menu.setVisible(true);
            }
        });
    }
}
