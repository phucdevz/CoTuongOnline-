package com.cotuongonline;

import javax.swing.*;
import java.awt.*;

public class LeaderboardScreen extends JFrame {

    public LeaderboardScreen() {
        setTitle("Bảng Xếp Hạng");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Bảng Xếp Hạng Người Chơi", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LeaderboardScreen leaderboard = new LeaderboardScreen();
                leaderboard.setVisible(true);
            }
        });
    }
}
