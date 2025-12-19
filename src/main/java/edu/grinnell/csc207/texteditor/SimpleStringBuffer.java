package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    private String buffer;
    private int index;

    /**
     * Creates a simple string buffer.
     */
    public SimpleStringBuffer() {
        buffer = "";
        index = 0;
    }

    /**
     * Inserts a character at the current index in the buffer.
     * 
     * @param ch the character
     */
    public void insert(char ch) {
        String half1 = buffer.substring(0, index);
        String half2 = buffer.substring(index);
        buffer = half1 + ch + half2;
        index++;
    }

    /**
     * Deletes a character at the current index
     */
    public void delete() {
        if (index == 0) {
            return;
        }
        String half1 = buffer.substring(0, index - 1);
        String half2 = buffer.substring(index);
        buffer = half1 + half2;
        index--;
    }

    /**
     * Returns the current index
     * 
     * @return currents index
     */
    public int getCursorPosition() {
        return index;
    }

    /**
     * Moves the index to the left.
     */
    public void moveLeft() {
        if (index == 0) {
            return;
        }
        index--;
    }

    /**
     * Moves the index to the right.
     */
    public void moveRight() {
        if (index >= buffer.length() - 1) {
            return;
        }
        index++;
    }

    /**
     * Returns text size
     * 
     * @return
     */
    public int getSize() {
        return buffer.length();
    }

    /**
     * 
     * @param i
     * @return The character at a certain index
     */
    public char getChar(int i) {
        return buffer.charAt(i);
    }

    /**
     * Returns the text in its entirety
     */
    @Override
    public String toString() {
        return buffer;
    }
}
