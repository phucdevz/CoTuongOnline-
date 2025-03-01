package com.cotuongonline;
import com.cotuongonline.Piece;
public class PieceMovement {

    // Phương thức tĩnh để kiểm tra di chuyển hợp lệ
    public static boolean isValidMove(Piece piece, int startX, int startY, int endX, int endY, Piece[][] board) {
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
                return validPawnMove(startX, startY, endX, endY, piece);
            default:
                return false;
        }
    }

    // Các phương thức di chuyển quân cờ (static)
    public static boolean validKingMove(int startX, int startY, int endX, int endY) {
        return Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1;
    }

    public static boolean validAdvisorMove(int startX, int startY, int endX, int endY) {
        return Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 1;
    }

    public static boolean validElephantMove(int startX, int startY, int endX, int endY) {
        return Math.abs(startX - endX) == 2 && Math.abs(startY - endY) == 2;
    }

    public static boolean validHorseMove(int startX, int startY, int endX, int endY) {
        return (Math.abs(startX - endX) == 2 && Math.abs(startY - endY) == 1) ||
                (Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 2);
    }

    public static boolean validRookMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        if (startX == endX) { // Di chuyển theo chiều dọc
            for (int i = Math.min(startY, endY) + 1; i < Math.max(startY, endY); i++) {
                if (board[i][startX] != null) return false; // Nếu có quân cờ khác thì không hợp lệ
            }
            return true;
        } else if (startY == endY) { // Di chuyển theo chiều ngang
            for (int i = Math.min(startX, endX) + 1; i < Math.max(startX, endX); i++) {
                if (board[startY][i] != null) return false; // Nếu có quân cờ khác thì không hợp lệ
            }
            return true;
        }
        return false;
    }

    public static boolean validCannonMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        if (startX == endX || startY == endY) {  // Cùng hàng hoặc cột
            int count = 0;

            // Kiểm tra có quân cờ nào nằm giữa không
            for (int i = Math.min(startX, endX) + 1; i < Math.max(startX, endX); i++) {
                if (board[startY][i] != null) count++;
            }
            for (int i = Math.min(startY, endY) + 1; i < Math.max(startY, endY); i++) {
                if (board[i][startX] != null) count++;
            }

            return count == 1;  // Pháo chỉ có thể bắt quân nếu có đúng một quân ở giữa
        }
        return false;
    }

    public static boolean validPawnMove(int startX, int startY, int endX, int endY, Piece piece) {
        String color = piece.getColor();  // Lấy màu của quân cờ từ đối tượng Piece
        if (color.equals("Red")) {
            if (startY < 5) {
                return endY == startY + 1 && endX == startX;  // Trước sông, Tốt Đỏ chỉ tiến thẳng
            } else {
                return (endY == startY + 1 || endX == startX + 1 || endX == startX - 1) && endY > startY;  // Sau sông, Tốt Đỏ có thể đi ngang
            }
        } else {
            if (startY > 4) {
                return endY == startY - 1 && endX == startX;  // Trước sông, Tốt Đen chỉ tiến thẳng
            } else {
                return (endY == startY - 1 || endX == startX + 1 || endX == startX - 1) && endY < startY;  // Sau sông, Tốt Đen có thể đi ngang
            }
        }
    }
}
