package edu.grinnell.csc207.texteditor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.googlecode.lanterna.TerminalPosition;
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
        String path = args[0];
        // String path = "./ello.txt";
        Path realPath = Paths.get(path); // get real
        // if (!Files.exists(realPath) || !Files.isRegularFile(realPath)) {
        // throw new FileNotFoundException(); // intellisense guess i swear
        // }
        System.out.format("Loading %s...\n", path);
        boolean isRunning = true;
        GapBuffer buf;
        if (Files.isRegularFile(Paths.get(path))) {
            buf = new GapBuffer(Files.readString(Paths.get(path)));
        } else {
            System.out.println("Creating new file...");
            buf = new GapBuffer(5);
        }

        screen.startScreen();
        drawBuffer(buf, screen);
        while (isRunning) {
            KeyStroke stroke = screen.readInput();
            if (stroke.getKeyType().equals(KeyType.Character)) {
                buf.insert(stroke.getCharacter());
            } else if (stroke.getKeyType().equals(KeyType.Backspace)) {
                buf.delete();
            } else if (stroke.getKeyType().equals(KeyType.ArrowLeft)) {
                buf.moveLeft();
            } else if (stroke.getKeyType().equals(KeyType.ArrowRight)) {
                buf.moveRight();
            } else if (stroke.getKeyType().equals(KeyType.Escape)) {
                isRunning = false;
                Files.writeString(Paths.get(path), buf.toString());
                System.exit(0);
            }
            drawBuffer(buf, screen);
            screen.refresh();
        }
    }

    public static void drawBuffer(GapBuffer buf, TerminalScreen screen) {
        screen.clear();
        boolean stop = false;
        for (int i = 0; i <= buf.getSize() / 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (30 * i + j == buf.toString().length()) {
                    stop = true;
                    break;
                }
                // String bfTsr = buf.toString();
                // System.out.println(buf.toString());

                screen.setCharacter(j, i, TextCharacter.fromCharacter(buf.getChar(30 * i + j))[0]);
            }
            screen.setCursorPosition(new TerminalPosition(buf.startIndex % 30, buf.startIndex / 30));
            if (stop) {
                break;
            }

        }
    }
}
// buf.getChar(30 * i + j)