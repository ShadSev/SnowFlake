package se.academy;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        Terminal terminal;
        terminal = TerminalFacade.createTerminal(System.in,
                System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();


        List<Flake> flakes = new ArrayList<>();

        while (true) {
            Key key;
            do {
                Thread.sleep(5);
                key = terminal.readInput();
            }
            while (key == null);

            terminal.clearScreen();
            UpdateScreen(flakes, terminal);

        }
    }

    private static void UpdateScreen(List<Flake> flakes, Terminal terminal) {
        Random rand = new Random();
        Flake flake = new Flake(rand.nextInt(35), 0);
        flakes.add(flake);
        boolean isFreeSpace = true;
        for (int i = 0; i < flakes.size(); i++) {
            isFreeSpace = true;

            for (int j = 0; j < flakes.size(); j++) {
                if (flakes.get(i).x == flakes.get(j).x && flakes.get(i).y + 1 == flakes.get(j).y) {
                    isFreeSpace = false;
                }
            }
            if(flakes.get(i).y<20 && isFreeSpace) {
                flakes.get(i).y = flakes.get(i).y + 1; }

            terminal.moveCursor(flakes.get(i).x, flakes.get(i).y);
            terminal.applyForegroundColor(255,255,255);
            terminal.putCharacter('\u2588');
            terminal.moveCursor(0, 0);


            }
        }

    }

