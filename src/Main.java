import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import models.*;
import operations.Game;

public class Main {
    static Game newGame;
    static boolean isPlay;
    static int userInput;
    static Scanner input;

    public static int toInteger (String s){
        return Integer.parseInt(s);
    }

    public static boolean readFromFile() {
        newGame = new Game();
        String path;
        File testFile;
        do {
            printGreen("Masukkan source file: ");
            path = input.nextLine();
            testFile = new File("../test/input/"+path);
            if (!testFile.exists()) {
                printlnRed("System tidak dapat menemukan file!");
            }
        } while (!testFile.exists());
        return newGame.readText("../test/input/"+path);
    }

    public static void readCLI() {
        newGame = new Game();
        int nTOken, bufferSize, row, col, nSequence, seqMaxSize;
        String tokens[];
        String nt;
        do {
            printGreen("Jumlah token unik  : ");
            nt = input.nextLine();
            if (!Game.isNumeric(nt) || Integer.parseInt(nt) < 1) {
                printlnRed("Masukan tidak valid.");
            }
        } while (!Game.isNumeric(nt) || Integer.parseInt(nt) < 1);
        nTOken = Integer.parseInt(nt);
        do {
            printGreen("Token              : ");
            nt = input.nextLine();
            if (!Sequence.isTokenValid(nt, nTOken)) {
                printlnRed("Masukan tidak sesuai.");
            }
        } while (!Sequence.isTokenValid(nt, nTOken));
        tokens = nt.split(" ");
        do {
            printGreen("Ukuran buffer      : ");
            nt = input.nextLine();
            if (!Game.isNumeric(nt) || Integer.parseInt(nt) < 1) {
                printlnRed("Masukan tidak valid.");
            }
        } while (!Game.isNumeric(nt) || Integer.parseInt(nt) < 1);
        bufferSize = Integer.parseInt(nt);
        String dimension[];
        do {
            printGreen("Ukuran matrix (w,h): ");
            nt = input.nextLine();
            dimension = nt.split(" ");
            if (dimension.length != 2 || !Game.isNumeric(dimension[0]) || !Game.isNumeric(dimension[1]) || toInteger(dimension[0]) < 0 || toInteger(dimension[1]) < 0) {
                printlnRed("Masukan tidak valid.");
            }
        } while (dimension.length != 2 || !Game.isNumeric(dimension[0]) || !Game.isNumeric(dimension[1]) || toInteger(dimension[0]) < 0 || toInteger(dimension[1]) < 0);
        col = toInteger(dimension[0]);
        row = toInteger(dimension[1]);
        do {
            printGreen("Jumlah sekuens     : ");
            nt = input.nextLine();
            if (!Game.isNumeric(nt) || Integer.parseInt(nt) < 2) {
                printlnRed("Masukan tidak valid.");
            }
        } while (!Game.isNumeric(nt) || Integer.parseInt(nt) < 2);
        nSequence = Integer.parseInt(nt);
        do {
            printGreen("Ukuran maks sekuen : ");
            nt = input.nextLine();
            if (!Game.isNumeric(nt) || Integer.parseInt(nt) < 2) {
                printlnRed("Masukan tidak valid.");
            }
        } while (!Game.isNumeric(nt) || Integer.parseInt(nt) < 2);
        seqMaxSize = Integer.parseInt(nt);
        newGame.randomGenerate(nTOken, tokens, bufferSize, row, col, nSequence, seqMaxSize);
        System.out.println();
        newGame.showProperties();
    }

    public static void printlnGreen(String s) {
        String rest = "\u001B[0m";
        String green = "\u001B[32m";

        System.out.println(green + s + rest);
    }

    public static void printGreen(String s) {
        String rest = "\u001B[0m";
        String green = "\u001B[32m";

        System.out.print(green + s + rest);
    }

    public static void printlnYellow(String s) {
        String rest = "\u001B[0m";
        String yellow = "\u001B[33m";

        System.out.println(yellow + s + rest);
    }

    public static void printlnRed(String s) {
        String rest = "\u001B[0m";
        String red = "\u001B[31m";

        System.out.println(red + s + rest);
    }

    public static void showHeader() {
        String header = "";
        try {
            File inputFile = new File("../src/header.txt");
            Scanner readFile = new Scanner(inputFile);
            String line;
            while (readFile.hasNextLine() && (line = readFile.nextLine()) != null) {
                header += line+"\n";
            }
            readFile.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        printlnYellow(header);
        printlnGreen("Selamat datang!!");
        printlnGreen("");
    }

    public static void showMainMenu() {
        printlnGreen("Menu:");
        printlnYellow("1. Masukan berupa file.");
        printlnYellow("2. Masukan random.");
        printlnYellow("0. Keluar.");
    }

    public static void askInput() {
        String userInp;
        do {
            printGreen("Masukkan pilihan: ");
            userInp = input.nextLine();
            if (!Game.isNumeric(userInp) || Integer.parseInt(userInp) < 0 || Integer.parseInt(userInp) > 2) {
                printlnRed("Masukan tidak sesuai!");
            }
        } while (!Game.isNumeric(userInp) || Integer.parseInt(userInp) < 0 || Integer.parseInt(userInp) > 2);
        userInput = toInteger(userInp);
    }

    public static void askSave(int time) {
        String userInp;
        do {
            printGreen("Apakah ingin menyimpan solusi? (y/n): ");
            userInp = input.nextLine();
            if (userInp == "" || (userInp.charAt(0) != 'y' && userInp.charAt(0) != 'Y' && userInp.charAt(0) != 'N' && userInp.charAt(0) != 'n')) {
                printlnRed("Masukan tidak sesuai!");
            }
        } while (userInp == "" || (userInp.charAt(0) != 'y' && userInp.charAt(0) != 'Y' && userInp.charAt(0) != 'N' && userInp.charAt(0) != 'n'));
        if (userInp.charAt(0) == 'Y' || userInp.charAt(0) == 'y') {
            newGame.saveSolution(time);
        } else {
            System.out.println();
        }
    }

    public static void startGame() {
        showHeader();
        isPlay = true;
        while (isPlay) {
            showMainMenu();
            askInput();
            if (userInput == 1){
                boolean fileValid = readFromFile();
                if (fileValid) {
                    long start = System.currentTimeMillis();
                    newGame.startSearch();
                    long end = System.currentTimeMillis();
                    int diff = (int)(end-start);
                    newGame.displaySolution(diff);
                    askSave(diff);
                } else {
                    printlnRed("\nFile tidak memuat data yang sesuai format!\n");
                }
            } else if (userInput == 2) {
                readCLI();
                long start = System.currentTimeMillis();
                newGame.startSearch();
                long end = System.currentTimeMillis();
                int diff = (int)(end-start);
                newGame.displaySolution(diff);
                askSave(diff);
            } else {
                isPlay = false;
            }
        }
        printlnYellow("\nBye-bye...\n");
    }

    public static void main (String args[]) {
        input = new Scanner(System.in);
        startGame();
        input.close();
    }
}
