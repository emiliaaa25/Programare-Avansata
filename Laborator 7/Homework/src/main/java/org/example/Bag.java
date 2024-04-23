package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Bag {
    private final List<Token> tokens;
    public List<Token> getTokens() {
        return tokens;
    }

    Bag(int n){
        tokens=new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tokens.add(new Token(i, j));
            }
        }
        shuffleTokens();
    }

    public void shuffleTokens(){
        Collections.shuffle(tokens);
    }
    synchronized Token extract() {
        if (!tokens.isEmpty()) {
            return tokens.remove(0);
        }
        return null;
    }

    synchronized boolean isEmpty() {
        return tokens.isEmpty();
    }

}

