package models;

public class Buffer {
    public String buffer[];
    public int position[][];
    public int weight;
    public int length;
    public int maxsize;

    public Buffer() {
    }

    public Buffer(int maxsize) {
        buffer = new String[maxsize];
        position = new int[maxsize][2];
        length = 0;
        weight = 0;
        this.maxsize = maxsize;
    }

    public Buffer(Buffer buf) {
        this(buf.maxsize);
        this.length = buf.length;
        this.weight = buf.weight;
        for (int i = 0; i< this.length; i++){
            this.buffer[i] = buf.buffer[i];
            this.position[i][0] = buf.position[i][0];
            this.position[i][1] = buf.position[i][1];
        }
    }

    public Buffer addTokenNew(String token, int row, int col){
        Buffer newBuf = new Buffer(this);
        newBuf.buffer[newBuf.length] = token;
        newBuf.position[newBuf.length][0] = row;
        newBuf.position[newBuf.length][1] = col;
        newBuf.length += 1;
        return newBuf;
    }

    public void addToken(String token, int row, int col){
        this.buffer[this.length] = token;
        this.position[this.length][0] = row;
        this.position[this.length][1] = col;
        this.length += 1;
    }

    public boolean isSeqExisted (Sequence seq) {
        if (seq.length > this.length) {
            return false;
        }
        int limit = this.length - seq.length + 1;
        boolean exist = false;
        int i = 0;
        while (!exist && i < limit) {
            int j = 0;
            boolean diff = false;
            while (!diff && j < seq.length) {
                if (!Sequence.isTokenEqual(seq.sequence[j], this.buffer[j+i])){
                    diff = true;
                } else {
                    j ++;
                }
            }
            if (j == seq.length) {
                exist = true;
            }
            i ++;
        }
        return exist;
    }

    public boolean isTokenTaken(int row, int col) {
        for (int i = 0 ; i < this.length; i++){
            if (this.position[i][0] == row && this.position[i][1] == col) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBuffer(Sequence seq[]) {
        int len = seq.length;
        weight = 0;
        boolean exist = false;
        for (int i = 0; i < len; i++) {
            if (isSeqExisted(seq[i])) {
                weight += seq[i].weight;
                exist = true;
            }
        }
        return exist;
    }

    @Override
    public String toString() {
        String temp = "";
        String pos = "";
        for (int i = 0; i < this.length; i++){
            temp += this.buffer[i];
            String temppos = "("+Integer.toString(this.position[i][1]+1)+","+Integer.toString(this.position[i][0]+1)+")";
            pos += temppos;
            if (i != this.length-1){
                temp+= "-";
                pos += "-";
            }
        }
        return "Buffer{"+temp+", "+pos+", weight: "+ Integer.toString(weight)+", maxsize: "+Integer.toString(maxsize)+", length: "+Integer.toString(length)+"}";
    }
}
