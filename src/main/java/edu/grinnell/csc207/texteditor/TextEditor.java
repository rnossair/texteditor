package edu.grinnell.csc207.texteditor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    /**
     * The main entry point for the TextEditor application.
     * 
     * @param args command-line arguments.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        DefaultTerminalFactory factory = new DefaultTerminalFactory();
        TerminalScreen screen = factory.createScreen();
        // String path = args[0];
        String path = "ello.txt";
        Path realPath = Paths.get(path); // get real
        if (!Files.exists(realPath) || !Files.isRegularFile(realPath)) {
            throw new FileNotFoundException(); // intellisense guess i swear
        }
        System.out.format("Loading %s...\n", path);
        boolean isRunning = true;
        GapBuffer buf = new GapBuffer();
        screen.startScreen();
        while (isRunning) {
            KeyStroke stroke = screen.readInput();
            if (stroke.equals(KeyType.Character)) {
                buf.insert(stroke.getCharacter());
            } else if (stroke.equals(KeyType.Backspace)) {
                buf.delete();
            } else if (stroke.equals(KeyType.ArrowLeft)) {
                buf.moveLeft();
            } else if (stroke.equals(KeyType.ArrowRight)) {
                buf.moveRight();
            } else if (stroke.equals(KeyType.Escape)) {
                isRunning = false;
            }
            drawBuffer(buf, screen);
            screen.refresh();
        }
    }

    public static void drawBuffer(GapBuffer buf, TerminalScreen screen) {
        boolean stop = false;
        for (int i = 0; i < buf.getSize(); i++) {
            for (int j = 0; j < 1; j++) {
                if (30 * i + j == buf.toString().length()) {
                    stop = true;
                    break;
                }
                screen.setCharacter(i, j, TextCharacter.fromCharacter(buf.getChar(30 * i + j))[0]);
            }
            if (stop) {
                break;
            }
        }
    }
}
