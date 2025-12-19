package edu.grinnell.csc207.texteditor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GapBufferTests {
    GapBuffer buf = new GapBuffer(3);

    @Test
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
        buf = new GapBuffer(3);
        buf.insert('H');
        buf.insert('i');
        buf.moveRight();
        buf.moveRight();
        buf.moveRight();
        buf.insert('!');
        assertEquals("Hi!", buf.toString());
    }

    @Test
    public void doubleBuffer() {
        buf.insert('H');
        buf.insert('i');
        buf.insert('!');
        assertEquals(6, buf.buffer.length);
    }
}
