package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Bag {
    private List<Token> tokens;
    private Lock lock;


    Bag(int n){
        tokens=new ArrayList<>();
        Random rand = new Random();

        for(int i=1;i<=n;i++) {
            for (int j = 1; j < n; j++) {
                tokens.add(new Token(rand.nextInt(1,n), rand.nextInt(1,n)));
            }
        }
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

