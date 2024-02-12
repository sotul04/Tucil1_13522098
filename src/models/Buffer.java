package models;

public class Buffer {
    public int position[][];
    public int weight;
    public int length;
    public int maxsize;
    public String buffString;

    public Buffer() {
    }

    public Buffer(int maxsize) {
        position = new int[maxsize][2];
        length = 0;
        weight = 0;
        this.maxsize = maxsize;
        buffString = "";
    }

    public Buffer(Buffer buf) {
        this(buf.maxsize);
        this.length = buf.length;
        this.weight = buf.weight;
        for (int i = 0; i< this.length; i++){
            this.position[i][0] = buf.position[i][0];
            this.position[i][1] = buf.position[i][1];
        }
        this.buffString = new String(buf.buffString);
    }

    public Buffer addTokenNew(String token, int row, int col){
        Buffer newBuf = new Buffer(this);
        if (newBuf.length != 0) {
            newBuf.buffString += " ";
        }
        newBuf.buffString += token;
        newBuf.position[newBuf.length][0] = row;
        newBuf.position[newBuf.length][1] = col;
        newBuf.length += 1;
        return newBuf;
    }

    public boolean isSeqExisted (Sequence seq) {
        return this.buffString.contains(seq.seqString);
    }

    public int checkBuffer(Sequence seq[]) {
        int len = seq.length;
        weight = 0;
        for (int i = 0; i < len; i++) {
            if (isSeqExisted(seq[i])) {
                weight += seq[i].weight;
            }
        }
        return weight;
    }
}
