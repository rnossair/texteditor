package edu.grinnell.csc207.texteditor;

import org.junit.jupiter.api.Test;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleStringBufferTests {
    SimpleStringBuffer buf = new SimpleStringBuffer();

    @Property
    public boolean bufferTest(
            @ForAll("printableStrings") String str) {
        buf = new SimpleStringBuffer();
        for(char c : str.toCharArray()){
            buf.insert(c);
        }
        return buf.toString() != str;
    }

    @Provide
    Arbitrary<String> printableStrings() {
        return Arbitraries.strings()
                .withCharRange('a', 'z')
                .withCharRange('A', 'Z')
                .ofMinLength(5)
                .ofMaxLength(250);
    }

    public void addingChars() {
        buf.insert('H');
        buf.insert('i');
        assertEquals("Hi", buf.toString());
    }

    @Test
    public void deletingChars() {
        buf.insert('H');
        buf.insert('i');
        buf.insert('!');
        buf.insert('@');
        buf.delete();
        assertEquals("Hi!", buf.toString());
    }

    @Test
    public void moveLeft() {
        buf.insert('H');
        buf.insert('i');
        buf.insert('!');
        buf.moveLeft();
        buf.insert('?');
        assertEquals("Hi?!", buf.toString());
    }

    @Test
    public void moveRight() {
        buf.insert('H');
        buf.insert('i');
        buf.insert('!');
        buf.moveLeft();
        buf.moveLeft();
        buf.moveRight();
        buf.insert('?');
        assertEquals("Hi?!", buf.toString());
    }

    @Test
    public void moveLeftStop() {
        buf.insert('i');
        buf.moveLeft();
        buf.moveLeft();
        buf.insert('H');
        assertEquals("Hi", buf.toString());
    }

    @Test
    public void moveRightStop() {
        buf.insert('H');
        buf.insert('i');
        buf.moveRight();
        buf.moveRight();
        buf.moveRight();
        buf.insert('!');
        assertEquals("Hi!", buf.toString());
    }
}
