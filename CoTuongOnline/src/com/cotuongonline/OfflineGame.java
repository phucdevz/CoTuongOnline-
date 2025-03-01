package com.cotuongonline;

import javax.swing.*;
import java.awt.*;

public class OfflineGame extends JFrame {

    public OfflineGame() {
        setTitle("Chơi Cờ Offline");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // JLabel hiển thị lượt người chơi
        JLabel turnLabel = new JLabel("Player 1 (Đỏ)", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(turnLabel, BorderLayout.NORTH);  // Thêm turnLabel lên trên

        // Tạo GameBoard và truyền turnLabel vào GameBoard
        GameBoard gameBoard = new GameBoard(turnLabel);
        add(gameBoard, BorderLayout.CENTER); // Thêm GameBoard vào giữa cửa sổ
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                OfflineGame game = new OfflineGame();
                game.setVisible(true);  // Hiển thị cửa sổ
            }
        });
    }
}
