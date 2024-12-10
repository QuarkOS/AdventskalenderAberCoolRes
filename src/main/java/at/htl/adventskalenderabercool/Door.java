package at.htl.adventskalenderabercool;

import javafx.scene.paint.Color;

public class Door {
    private int number;
    private String content;
    private Color backgroundColor;
    private boolean isOpened;  // Neue Zustandsvariable

    public Door(int number, String content) {
        this.number = number;
        this.content = content;
        this.isOpened = false;  // Standardmäßig auf nicht geöffnet setzen
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }
}
