package operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

import models.*;

public class Game {
    public Matrix matriks;
    public Sequence sekuen[];
    public Buffer solution;
    public boolean wasFound;

    public Game() {
        wasFound = false;
    }

    public static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean readText(String path){
        wasFound = false;
        try {
            File inputFile = new File(path);
            Scanner readFile = new Scanner(inputFile);
            String line;
            if (!readFile.hasNextLine()){
                readFile.close();
                return false;
            }
            line = readFile.nextLine();
            if (!isNumeric(line) || Integer.parseInt(line) < 0){
                readFile.close();
                return false;
            }
            this.solution = new Buffer(Integer.parseInt(line));
            if (!readFile.hasNextLine()){
                readFile.close();
                return false;
            }
            line = readFile.nextLine();
            String dimension[] = line.split(" ");
            if (dimension.length != 2 || !Game.isNumeric(dimension[0]) || !Game.isNumeric(dimension[1]) || Integer.parseInt(dimension[0]) < 0 || Integer.parseInt(dimension[1]) < 0) {
                readFile.close();
                return false;
            }
            int row = Integer.parseInt(dimension[1]);
            int col = Integer.parseInt(dimension[0]);
            this.matriks = new Matrix(row, col);
            for (int i = 0; i < row; i++){
                if (!readFile.hasNextLine()){
                    readFile.close();
                    return false;
                }
                line = readFile.nextLine();
                String token[] = line.split(" ");
                if (!Sequence.isTokenValid(line, col)){
                    readFile.close();
                    return false;
                }
                for (int j = 0; j < col-1; j++) {
                    matriks.setToken(token[j], i, j);
                }
                String temp = "";
                temp += token[col-1].charAt(0);
                temp += token[col-1].charAt(1);
                matriks.setToken(temp, i, col-1);
            }
            line = readFile.nextLine();
            if (!isNumeric(line) || Integer.parseInt(line) < 0){
                readFile.close();
                return false;
            }
            this.sekuen = new Sequence[Integer.parseInt(line)];
            for (int i = 0; i < this.sekuen.length; i++){
                if (!readFile.hasNextLine()){
                    readFile.close();
                    return false;
                }
                line = readFile.nextLine();
                if (!readFile.hasNextLine()){
                    readFile.close();
                    return false;
                }
                String wght = readFile.nextLine();
                if (!isNumeric(wght)){
                    readFile.close();
                    return false;
                }
                int weight = Integer.parseInt(wght);
                String tokens[] = line.split(" ");
                if (!Sequence.isTokenValid(line, tokens.length)) {
                    readFile.close();
                    return false;
                }
                String clean = "";
                clean += tokens[tokens.length-1].charAt(0);
                clean += tokens[tokens.length-1].charAt(1);
                tokens[tokens.length-1] = clean;
                sekuen[i] = new Sequence(tokens, weight);
            }
            readFile.close();
            return true;
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }

    public void randomGenerate(int nToken, String tokens[], int bufferSize, int row, int col, int nSequence, int seqMaxSize) {
        wasFound = false;
        Random rand = new Random();
        this.matriks = new Matrix(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.matriks.setToken(tokens[rand.nextInt(nToken)], i, j);
            }
        }
        this.solution = new Buffer(bufferSize);
        this.sekuen = new Sequence[nSequence];
        for (int i = 0; i < nSequence; i++) {
            do {
                int seqLength = rand.nextInt(seqMaxSize-2+1)+2;
                this.sekuen[i] = generateSeq(tokens, seqLength);
            } while (isEqualSeqExist(this.sekuen[i], this.sekuen, i));
        }
    }

    public static Sequence generateSeq (String tokens[], int length) {
        Random rand = new Random();
        int nToken = tokens.length;
        Sequence out = new Sequence(length, rand.nextInt(101));
        for (int i = 0; i < length; i++) {
            out.sequence[i] = tokens[rand.nextInt(nToken)];
        }
        return out;
    }

    public static boolean isEqualSeqExist(Sequence seq, Sequence list[], int offset) {
        for (int i = 0; i < offset; i++) {
            if (seq.isSeqTokenEqual(list[i])) {
                return true;
            }
        }
        return false;
    }

    public void bruteForce(Matrix matrix, boolean isVertical, Buffer buff, int curRow, int curCol) {
        if (buff.maxsize > buff.length){
            Matrix newMatrix = new Matrix(matrix);
            newMatrix.setVisited(true, curRow, curCol);
            Buffer newBuff = buff.addTokenNew(matrix.getToken(curRow, curCol), curRow, curCol);
            if (newBuff.checkBuffer(sekuen)) {
                // if (newBuff.length == 5){
                //     System.out.println(newBuff);
                // }
                if (wasFound) {
                    if (newBuff.weight > solution.weight) {
                        solution = new Buffer(newBuff);
                    } else if (newBuff.weight == solution.weight && newBuff.length < solution.length) {
                        solution = new Buffer(newBuff);
                        //System.out.println("Lewat sini.");
                    }
                } else {
                    //System.out.println("Initialized");
                    wasFound = true;
                    solution = new Buffer(newBuff);
                }
            }
            if (isVertical) {
                for (int i = 0; i < newMatrix.row; i++){
                    if (!newMatrix.getVisited(i, curCol)){
                        bruteForce(newMatrix, !isVertical, newBuff, i, curCol);
                    }
                }
            } else {
                for (int i = 0; i < newMatrix.col; i++){
                    if(!newMatrix.getVisited(curRow, i)) {
                        bruteForce(newMatrix, !isVertical, newBuff, curRow, i);
                    }
                }
            }
        }
    }

    public void startSearch() {
        int column = matriks.col;
        Matrix newMat = new Matrix(matriks);
        Buffer buff = new Buffer(solution);
        for (int j = 0; j < column; j++) {
            bruteForce(newMat, true, buff, 0, j);
        }
    }

    public void displaySolution(int time) {
        System.out.println("\nSolution:");
        if (wasFound){
            System.err.println(solution.weight);
            String buff = "";
            for (int i = 0; i < solution.length; i++){
                buff += solution.buffer[i]+" ";
            }
            System.err.println(buff);
            for (int i = 0; i < solution.length; i++){
                System.out.println((solution.position[i][1]+1)+", "+(solution.position[i][0]+1));
            }
        } else {
            System.out.println("0");
            System.out.println("TIdak ada solusi");
        }
        System.out.println("\n"+time+"ms\n");
    }

    public void showProperties() {
        String temp = "";
        for (int i = 0 ; i < matriks.row; i++){
            for (int j = 0 ; j < matriks.col; j++){
                temp += matriks.getToken(i, j);
                if (j != matriks.col-1){
                    temp+=" ";
                }
            }
            temp += "\n";
        }
        System.out.println("Matriks:");
        System.out.println(temp);
        System.out.println("Sekuens:");
        for (int i = 0; i < sekuen.length; i++){
            String sek = "";
            for (int j = 0; j < sekuen[i].length; j++){
                sek += sekuen[i].sequence[j]+" ";
            }
            sek += "bobot: "+sekuen[i].weight;
            System.out.println(sek);
        }
    }

    public void saveSolution(int time) {
        String save = "";
        if (wasFound) {
            save += Integer.toString(solution.weight) +"\n";
            for (int i = 0; i < solution.length; i++) {
                save += solution.buffer[i] + " ";
            }
            save += "\n";
            for (int i = 0; i < solution.length; i++) {
                save += (solution.position[i][1]+1) +", "+(solution.position[i][0]+1) + "\n";
            }
            save += "\n" + time + "ms\n";
        } else {
            save += "0\nTidak ada Solusi\n\n"+time+"ms\n";
        }

        String fileName = "../test/output/solution";
        File testFile = new File(fileName+".txt");
        int idx = 0;
        while (testFile.exists()){
            idx ++;
            testFile = new File(fileName+"_"+idx+".txt");
        }
        if (idx != 0) {
            fileName += "_" + idx + ".txt";
        } else {
            fileName += ".txt";
        }
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(save);
            writer.close();
            System.out.println("\nSolusi berhasil disimpan pada: "+fileName+"\n");
        } catch (Exception e) {
            System.out.println("An error occurred\n");
            e.printStackTrace();
        }
    }
}
