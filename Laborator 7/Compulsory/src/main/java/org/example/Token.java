package org.example;

public class Token {
    public  int first;
    public int second;
    Token(int first,int second){
        this.first=first;
        this.second=second;
    }
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
