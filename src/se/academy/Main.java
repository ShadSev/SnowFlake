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
        List<StructureBlock> blocks = new ArrayList<>();

        while (true) {
            Key key;
            do {
                Thread.sleep(5);
                key = terminal.readInput();
            }
            while (key == null);

            terminal.clearScreen();
            UpdateScreen(flakes, blocks, terminal);
            addStructure(blocks, terminal);
        }
    }

    private static void UpdateScreen(List<Flake> flakes, List<StructureBlock> blocks, Terminal terminal) {
        Random rand = new Random();
        Flake flake = new Flake(rand.nextInt(35), 0);
        flakes.add(flake);
        boolean isFreeSpace;
        boolean isSideFree;
        for (int i = 0; i < flakes.size(); i++) {
            isFreeSpace = true;
            isSideFree = true;

            for (int j = 0; j < flakes.size(); j++) {
                if (flakes.get(i).x == flakes.get(j).x && flakes.get(i).y + 1 == flakes.get(j).y) {
                    isFreeSpace = false;
                }
                    if (flakes.get(i).x - 1 == flakes.get(j).x && flakes.get(i).y + 1 == flakes.get(j).y) {
                        isSideFree = false;
                    }
            }
            for (int j = 0; j < blocks.size(); j++) {
                if (flakes.get(i).x == blocks.get(j).x && flakes.get(i).y + 1 == blocks.get(j).y) {
                    isFreeSpace = false;}

                    if (flakes.get(i).x - 1 == blocks.get(j).x && flakes.get(i).y + 1 == blocks.get(j).y) {
                        isSideFree = false;
                    }
            }
            if (flakes.get(i).y < 20 && isFreeSpace) {
                    flakes.get(i).y = flakes.get(i).y + 1;
            }
            if(flakes.get(i).y<20 && isSideFree && !isFreeSpace){
                flakes.get(i).x=flakes.get(i).x-1;
                flakes.get(i).y=flakes.get(i).y+1;
            }


            terminal.moveCursor(flakes.get(i).x, flakes.get(i).y);
            terminal.applyForegroundColor(255, 255, 255);
            terminal.putCharacter('\u2588');
            terminal.moveCursor(0, 0);
        }
    }

    private static void addStructure(List<StructureBlock> blocks, Terminal terminal) {
        int bla = 5;
        for (int i = 10; i <= 20; i++) {
            StructureBlock block = new StructureBlock(5, i);
            blocks.add(block);
            terminal.moveCursor(5, i);
            terminal.putCharacter('X');
            StructureBlock block2 = new StructureBlock(15, i);
            blocks.add(block2);
            terminal.moveCursor(15, i);
            terminal.putCharacter('X');
            StructureBlock block3 = new StructureBlock(bla, 10);
            blocks.add(block3);
            terminal.moveCursor(bla, 10);
            terminal.putCharacter('X');
            terminal.moveCursor(0, 0);
            bla++;
        }
    }
}

