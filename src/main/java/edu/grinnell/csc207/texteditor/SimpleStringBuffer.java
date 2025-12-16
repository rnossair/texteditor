package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    private String buffer;
    private int index;

    public SimpleStringBuffer() {
        buffer = "";
        index = 0;
    }

    public void insert(char ch) {
        String half1 = buffer.substring(0, index);
        String half2 = buffer.substring(index);
        buffer = half1 + ch + half2;
        index++;
    }

    public void delete() {
        if(index == 0){
            return;
        }
        String half1 = buffer.substring(0, index - 1);
        String half2 = buffer.substring(index);
        buffer = half1 + half2;
        index--;
    }

    public int getCursorPosition() {
        return index;
    }

    public void moveLeft() {
        if (index == 0) {
            return;
        }
        index--;
    }

    public void moveRight() {
        if(index >= buffer.length() - 1){
            return;
        }
        index++;
    }

    public int getSize() {
        return buffer.length();
    }

    public char getChar(int i) {
        return buffer.charAt(i);
    }

    @Override
    public String toString() {
        return buffer;
    }
}
