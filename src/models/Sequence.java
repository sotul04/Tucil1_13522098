package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sequence {
    public String sequence[];
    public int length;
    public int weight;

    public static boolean isTokenEqual(String token1, String token2) {
        return token1.charAt(0) == token2.charAt(0) && token1.charAt(1) == token2.charAt(1); 
    }

    public static boolean isTokenValid(String tokens, int length) {
        String regex = "^[a-zA-Z0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        String token[] = tokens.split(" ");
        if (token.length != length){
            return false;
        }
        for (int i = 0; i < token.length; i++) {
            Matcher matcher = pattern.matcher(token[i]);
            if (!matcher.matches() || token[i].length() != 2) {
                return false;
            }
        }
        return true;
    }

    public Sequence(){
        length = 0;
        weight = 0;
    }

    public Sequence(int length, int weight){
        sequence = new String[length];
        this.length = length;
        this.weight = weight;
    }

    public Sequence(String token[], int weight){
        this(token.length, weight);
        for (int i = 0; i < length; i++){
            sequence[i] = token[i];
        }
    }

    public boolean isSeqTokenEqual(Sequence second) {
        if (this.length != second.length) {
            return false;
        }
        for (int i = 0; i < this.length; i++) {
            if (!isTokenEqual(this.sequence[i], second.sequence[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < length; i++){
            temp += sequence[i];
            if (i != length-1) {
                temp += "-";
            }
        }
        return "Sequence{"+temp+", length:"+Integer.toString(length)+", weight: "+Integer.toString(weight)+"}";
    }
}
