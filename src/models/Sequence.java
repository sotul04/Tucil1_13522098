package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sequence {
    public int length;
    public int weight;
    public String seqString;

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
        this.length = length;
        this.weight = weight;
        this.seqString = "";
    }

    public Sequence(String token[], int weight){
        this(token.length, weight);
        for (int i = 0; i < length-1; i++){
            seqString += token[i]+" ";
        }
        seqString += token[length-1];
    }

    public boolean isSeqTokenEqual(Sequence second) {
        return this.seqString.equals(second.seqString);
    }

    @Override
    public String toString() {
        return "Sequence{"+seqString+", length:"+Integer.toString(length)+", weight: "+Integer.toString(weight)+"}";
    }
}
