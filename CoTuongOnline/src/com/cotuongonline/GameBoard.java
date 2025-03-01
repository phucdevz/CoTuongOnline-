package com.cotuongonline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameBoard extends JPanel {
    private final int BOARD_SIZE = 9;  // Kích thước bàn cờ
    private final int ROWS = 10;
    private final int CELL_SIZE = 60;

    private Piece[][] board;
    private boolean isPlayerOneTurn = true;
    private JLabel turnLabel;
    private Image boardImage;

    // Hình ảnh quân cờ
    private Image redPawnImage, blackPawnImage, redHorseImage, blackHorseImage, redRookImage, blackRookImage;
    private Image redAdvisorImage, blackAdvisorImage, redCannonImage, blackCannonImage, redElephantImage, blackElephantImage;
    private Image redKingImage, blackKingImage;

    public GameBoard(JLabel turnLabel) {
        this.turnLabel = turnLabel;
        board = new Piece[ROWS][BOARD_SIZE];
        setPreferredSize(new Dimension(CELL_SIZE * BOARD_SIZE, CELL_SIZE * ROWS));

        // Tải hình ảnh bàn cờ
        boardImage = new ImageIcon(getClass().getResource("/resources/board.png")).getImage();

        // Tải hình ảnh quân cờ
        redPawnImage = new ImageIcon(getClass().getResource("/resources/PawnR.png")).getImage();
        blackPawnImage = new ImageIcon(getClass().getResource("/resources/PawnB.png")).getImage();
        redHorseImage = new ImageIcon(getClass().getResource("/resources/HorseR.png")).getImage();
        blackHorseImage = new ImageIcon(getClass().getResource("/resources/HorseB.png")).getImage();
        redRookImage = new ImageIcon(getClass().getResource("/resources/RookR.png")).getImage();
        blackRookImage = new ImageIcon(getClass().getResource("/resources/RookB.png")).getImage();
        redAdvisorImage = new ImageIcon(getClass().getResource("/resources/AdvisorR.png")).getImage();
        blackAdvisorImage = new ImageIcon(getClass().getResource("/resources/AdvisorB.png")).getImage();
        redCannonImage = new ImageIcon(getClass().getResource("/resources/CannonR.png")).getImage();
        blackCannonImage = new ImageIcon(getClass().getResource("/resources/CannonB.png")).getImage();
        redElephantImage = new ImageIcon(getClass().getResource("/resources/ElephantR.png")).getImage();
        blackElephantImage = new ImageIcon(getClass().getResource("/resources/ElephantB.png")).getImage();
        redKingImage = new ImageIcon(getClass().getResource("/resources/KingR.png")).getImage();
        blackKingImage = new ImageIcon(getClass().getResource("/resources/KingB.png")).getImage();

        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int startX = e.getX() / CELL_SIZE;
                int startY = e.getY() / CELL_SIZE;

                // Tính toán tọa độ kết thúc di chuyển (ví dụ bạn có thể tính theo cách khác)
                int endX = startX + 1;  // Giả sử bạn muốn di chuyển sang phải 1 ô
                int endY = startY;

                // Gọi phương thức movePiece với đầy đủ tham số
                movePiece(startX, startY, endX, endY);
            }
        });
        initializeBoard();
    }

    private void initializeBoard() {
        // Hàng 0 và 9 cho Người Chơi 1 (Đỏ) và Người Chơi 2 (Đen)
        board[0][0] = new Piece("Rook", "Red");
        board[0][1] = new Piece("Horse", "Red");
        board[0][2] = new Piece("Elephant", "Red");
        board[0][3] = new Piece("Advisor", "Red");
        board[0][4] = new Piece("King", "Red");
        board[0][5] = new Piece("Advisor", "Red");
        board[0][6] = new Piece("Elephant", "Red");
        board[0][7] = new Piece("Horse", "Red");
        board[0][8] = new Piece("Rook", "Red");

        board[9][0] = new Piece("Rook", "Black");
        board[9][1] = new Piece("Horse", "Black");
        board[9][2] = new Piece("Elephant", "Black");
        board[9][3] = new Piece("Advisor", "Black");
        board[9][4] = new Piece("King", "Black");
        board[9][5] = new Piece("Advisor", "Black");
        board[9][6] = new Piece("Elephant", "Black");
        board[9][7] = new Piece("Horse", "Black");
        board[9][8] = new Piece("Rook", "Black");
//        board[2][1] = new Piece("Cannon", "Red");  // Pháo Đỏ
//        board[2][7] = new Piece("Cannon", "Red");  // Pháo Đỏ
//        board[7][1] = new Piece("Cannon", "Black"); // Pháo Đen
//        board[7][7] = new Piece("Cannon", "Black"); // Pháo Đen

        for (int i = 1; i < BOARD_SIZE; i=i+6) {
            board[2][i] = new Piece("Cannon", "Red");  // Pháo Đỏ
            board[7][i] = new Piece("Cannon", "Black"); // Pháo Đen
        }
        for (int i = 0; i < BOARD_SIZE; i=i+2) {
            board[3][i] = new Piece("Pawn", "Red");
            board[6][i] = new Piece("Pawn", "Black");
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Vẽ hình ảnh bàn cờ lên JPanel
        g.drawImage(boardImage, 0, 0, getWidth(), getHeight(), this);
        drawPieces(g);  // Vẽ các quân cờ lên bàn cờ
    }

    private void drawPieces(Graphics g) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Piece piece = board[i][j];
                if (piece != null) {
                    // Tính toán vị trí quân cờ chính xác
                    int xPos = j * (getWidth() / BOARD_SIZE) + (getWidth() / BOARD_SIZE - 30) / 2;  // Căn giữa theo chiều ngang
                    int yPos = i * (getHeight() / ROWS) + (getHeight() / ROWS - 30) / 2;  // Căn giữa theo chiều dọc

                    // Kích thước quân cờ
                    int imageWidth = getWidth() / BOARD_SIZE - 30;
                    int imageHeight = getHeight() / ROWS - 30;

                    // Vẽ quân cờ tùy theo loại
                    switch (piece.getName()) {
                        case "Pawn":
                            g.drawImage(piece.getColor().equals("Red") ? redPawnImage : blackPawnImage, xPos, yPos, imageWidth, imageHeight, this);
                            break;
                        case "Horse":
                            g.drawImage(piece.getColor().equals("Red") ? redHorseImage : blackHorseImage, xPos, yPos, imageWidth, imageHeight, this);
                            break;
                        case "Rook":
                            g.drawImage(piece.getColor().equals("Red") ? redRookImage : blackRookImage, xPos, yPos, imageWidth, imageHeight, this);
                            break;
                        case "Advisor":
                            g.drawImage(piece.getColor().equals("Red") ? redAdvisorImage : blackAdvisorImage, xPos, yPos, imageWidth, imageHeight, this);
                            break;
                        case "King":
                            g.drawImage(piece.getColor().equals("Red") ? redKingImage : blackKingImage, xPos, yPos, imageWidth, imageHeight, this);
                            break;
                        case "Cannon":
                            g.drawImage(piece.getColor().equals("Red") ? redCannonImage : blackCannonImage, xPos, yPos, imageWidth, imageHeight, this);
                            break;
                        case "Elephant":
                            g.drawImage(piece.getColor().equals("Red") ? redElephantImage : blackElephantImage, xPos, yPos, imageWidth, imageHeight, this);
                            break;
                    }
                }
            }
        }
    }



    private void movePiece(int startX, int startY, int endX, int endY) {
        Piece piece = board[startY][startX];
        if (piece != null && PieceMovement.isValidMove(piece, startX, startY, endX, endY, board)) {
            // Di chuyển quân cờ nếu hợp lệ
            board[endY][endX] = piece;
            board[startY][startX] = null;  // Xóa quân cờ ở vị trí ban đầu
            isPlayerOneTurn = !isPlayerOneTurn;  // Chuyển lượt người chơi
            updateTurnLabel();  // Cập nhật nhãn lượt chơi
            checkGameOver();  // Kiểm tra kết thúc trò chơi
        } else {
            // Thông báo lỗi nếu di chuyển không hợp lệ
            JOptionPane.showMessageDialog(this, "Di chuyển không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        repaint();  // Vẽ lại bàn cờ
    }

    private void updateTurnLabel() {
        if (isPlayerOneTurn) {
            turnLabel.setText("Player 1 (Quân Đỏ)");
        } else {
            turnLabel.setText("Player 2 (Quân Đen)");
        }
    }

    private void checkGameOver() {
        boolean isGameOver = false;
        String winner = "";

        // Kiểm tra xem Vua của một bên có bị bắt chưa
        boolean redKingCaptured = true;
        boolean blackKingCaptured = true;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Piece piece = board[i][j];
                if (piece != null) {
                    if (piece.getName().equals("King") && piece.getColor().equals("Red")) {
                        redKingCaptured = false;
                    }
                    if (piece.getName().equals("King") && piece.getColor().equals("Black")) {
                        blackKingCaptured = false;
                    }
                }
            }
        }

        if (redKingCaptured) {
            winner = "Player 2 (Quân Đen)";
            isGameOver = true;
        } else if (blackKingCaptured) {
            winner = "Player 1 (Quân Đỏ)";
            isGameOver = true;
        }

        if (isGameOver) {
            JOptionPane.showMessageDialog(this, winner + " là người chiến thắng!", "Kết thúc trò chơi!", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public boolean isValidMove(Piece piece, int startX, int startY, int endX, int endY, Piece[][] board) {
        switch (piece.getName()) {
            case "King":
                return validKingMove(startX, startY, endX, endY);
            case "Advisor":
                return validAdvisorMove(startX, startY, endX, endY);
            case "Elephant":
                return validElephantMove(startX, startY, endX, endY);
            case "Horse":
                return validHorseMove(startX, startY, endX, endY);
            case "Rook":
                return validRookMove(startX, startY, endX, endY, board);
            case "Cannon":
                return validCannonMove(startX, startY, endX, endY, board);
            case "Pawn":
                return validPawnMove(startX, startY, endX, endY);
            default:
                return false;
        }
    }


    // Implement each type of move validation here
    private boolean validKingMove(int startX, int startY, int endX, int endY) {
        // Logic for King move
        return Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1;
    }

    private boolean validAdvisorMove(int startX, int startY, int endX, int endY) {
        // Logic for Advisor move
        return Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 1;
    }

    private boolean validElephantMove(int startX, int startY, int endX, int endY) {
        // Logic for Elephant move
        return Math.abs(startX - endX) == 2 && Math.abs(startY - endY) == 2;
    }

    private boolean validHorseMove(int startX, int startY, int endX, int endY) {
        // Logic for Horse move
        return (Math.abs(startX - endX) == 2 && Math.abs(startY - endY) == 1) ||
                (Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 2);
    }

    private boolean validRookMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        // Logic for Rook move
        // Rook can move horizontally or vertically, but not through other pieces
        if (startX == endX) { // Move vertically
            for (int i = Math.min(startY, endY) + 1; i < Math.max(startY, endY); i++) {
                if (board[i][startX] != null) return false;
            }
            return true;
        } else if (startY == endY) { // Move horizontally
            for (int i = Math.min(startX, endX) + 1; i < Math.max(startX, endX); i++) {
                if (board[startY][i] != null) return false;
            }
            return true;
        }
        return false;
    }

    private boolean validCannonMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        // Logic for Cannon move
        // Cannon can move like Rook, but needs an intermediate piece to capture
        if (startX == endX || startY == endY) { // Same row or column
            int count = 0;
            for (int i = Math.min(startX, endX) + 1; i < Math.max(startX, endX); i++) {
                if (board[startY][i] != null) count++;
            }
            for (int i = Math.min(startY, endY) + 1; i < Math.max(startY, endY); i++) {
                if (board[i][startX] != null) count++;
            }
            return count == 1; // Only one piece in between
        }
        return false;
    }

    private boolean validPawnMove(int startX, int startY, int endX, int endY) {
        // Logic for Pawn move
        Piece piece = board[startY][startX];
        if (piece.getColor().equals("Red")) {
            return endY == startY + 1 && endX == startX; // Red Pawn moves forward only
        } else {
            return endY == startY - 1 && endX == startX; // Black Pawn moves forward only
        }
    }
}