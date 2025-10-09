package edu.grinnell.csc207.texteditor;

import java.util.Arrays;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {

    char[] buffer = new char[5];
    int startIndex = 1;
    int endIndex = 3;

    public void doubleBuffer(){
        int bufferLength = buffer.length - (endIndex - startIndex);
        int lengthOfStringTwo = Arrays.copyOfRange(buffer, endIndex, buffer.length).length;
        char[] newBuffer = Arrays.copyOf(buffer, 2 * buffer.length);
        System.arraycopy(buffer, endIndex, newBuffer, newBuffer.length -1 - lengthOfStringTwo, lengthOfStringTwo);
        endIndex = newBuffer.length - lengthOfStringTwo - 1;
    }

    public void insert(char ch) {
        if(endIndex - startIndex == 0){
            this.doubleBuffer();
        }

        buffer[startIndex] = ch;
        startIndex++;

    }

    public void delete() {
        startIndex--;
    }

    public int getCursorPosition() {
        return startIndex;
    }

    public void moveLeft() {
        startIndex--;
        endIndex--;
        buffer[endIndex] = buffer[startIndex + 1];
    }

    public void moveRight() {
        startIndex++;
        endIndex++;
        buffer[startIndex] = buffer[endIndex - 1];
    }

    public int getSize() {
        return buffer.length - (endIndex - startIndex);
    }

    public char getChar(int i) {
        String str = this.toString();
        return str.charAt(i);
    }

    public String toString() {
        String result = "";
        String half1 = new String(Arrays.copyOfRange(buffer, 0, startIndex));
        String half2 = new String(Arrays.copyOfRange(buffer, endIndex, buffer.length-1));
        return half1 + half2;
    }
}
