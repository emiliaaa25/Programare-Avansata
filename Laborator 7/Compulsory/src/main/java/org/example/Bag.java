package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag {
    private List<Token> tokens;

    Bag(int n){
        tokens=new ArrayList<>();
        Random rand = new Random();

        for(int i=1;i<=n;i++) {
            for (int j = 1; j < n; j++) {
                tokens.add(new Token(rand.nextInt(1,n), rand.nextInt(1,n)));
            }
        }
    }

    public List<Token> extract(int m){
        List<Token> extracted=new ArrayList<>();
        for(int i=0;i<m;i++){
            if(tokens==null){
                break;
            }
            extracted.add(tokens.get(i));
        }
        return extracted;
    }
    public boolean isEmpty(){
        return tokens.isEmpty();
    }
}
