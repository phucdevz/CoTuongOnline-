package com.cotuongonline;

public class Piece {
    private String name;
    private String color;

    public Piece(String name, String color) {
        this.name = name;
        this.color = color;
    }

    // Getter cho 'name'
    public String getName() {
        return name;
    }

    // Getter cho 'color'
    public String getColor() {
        return color;
    }

    // Setter nếu cần
    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }
}