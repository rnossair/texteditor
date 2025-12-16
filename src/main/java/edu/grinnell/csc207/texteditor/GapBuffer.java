package edu.grinnell.csc207.texteditor;

import java.util.Arrays;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {

    char[] buffer = new char[3];
    int startIndex = 0;
    int endIndex = 2;

    public GapBuffer(int size){
        buffer = new char[size];
        startIndex = 0;
        endIndex = size - 1;
    }

    public GapBuffer(String str){   
        char[] strArr = str.toCharArray();
        buffer = Arrays.copyOf(strArr, str.length() + 8);
        startIndex = str.length();
        endIndex = startIndex + 7;
    }
    /**
     * Doubles the size of the buffer from its current size.
     */
    public void doubleBuffer() {
        int bufferLength = buffer.length - (endIndex - startIndex);
        int lengthOfStringTwo = buffer.length - endIndex;
        char[] newBuffer = Arrays.copyOf(buffer, 2 * buffer.length);
        System.arraycopy(buffer, endIndex, newBuffer, newBuffer.length - lengthOfStringTwo, lengthOfStringTwo);
        buffer = newBuffer;
        endIndex = newBuffer.length - lengthOfStringTwo;
    }

    /**
     * Inserts a character at the current index in the buffer.
     * @param ch the character
     */
    public void insert(char ch) {
        if (endIndex - startIndex == 0) {
            this.doubleBuffer();
        }

        buffer[startIndex] = ch;
        startIndex++;

    }

    /**
     * Deletes a character at the current index
     */
    public void delete() {
        if(startIndex == 0){
            return;
        }
        startIndex--;
    }

    /**
     * Returns the current index
     * @return currents index
     */
    public int getCursorPosition() {
        return startIndex;
    }
    
    /**
     * Moves the index to the left.
     */
    public void moveLeft() {
        if(startIndex == 0){
            return;
        }   
        if(startIndex == endIndex){
            doubleBuffer();
        }

        buffer[endIndex] = buffer[startIndex - 1];
        startIndex--;
        endIndex--;
        
        System.out.println("hi");
    }

    /**
     * Moves the index to the right.
     */
    public void moveRight() {
        if(startIndex == endIndex){
            doubleBuffer();
        }
        if(endIndex == buffer.length - 1){
            return;
        }
        endIndex++;
        buffer[startIndex] = buffer[endIndex];
        startIndex++;
        
        
    }

    /**
     * Returns text size
     * @return
     */
    public int getSize() {
        return buffer.length - (endIndex - startIndex);
    }

    /**
     * 
     * @param i
     * @return The character at a certain index
     */
    public char getChar(int i) {
        String str = this.toString();
        return str.charAt(i);
    }

    /**
     * Returns the text in its entirety
     */

    public String toString() {
        String result = "";
        String half1 = new String(Arrays.copyOfRange(buffer, 0, startIndex));
        String half2 = new String(Arrays.copyOfRange(buffer, endIndex + 1, buffer.length));
        return half1 + half2;
    }
}
