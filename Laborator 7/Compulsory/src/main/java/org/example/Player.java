package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player extends Thread{
    private String name;
    private Bag bag;
    private int points=0;
    private int n;

    public Player(String name,Bag bag,int n){
        this.name=name;
        this.bag=bag;
        this.n=n;
    }
    @Override
    public void run() {
        while (!bag.isEmpty() && points < n) {
            Token token;
            synchronized (bag) {
                token = bag.extract();
            }
            if (token != null) {
                List<Token> sequence = new ArrayList<>();
                sequence.add(token);
                while (sequence.size() < n) {
                    Token nextToken;
                    synchronized (bag) {
                        nextToken = bag.extract();
                    }
                    if (nextToken != null && nextToken.first == sequence.get(sequence.size() - 1).second) {
                        sequence.add(nextToken);
                    } else {
                        synchronized (bag) {
                            bag.extract();
                        }
                        break;
                    }
                }
                points = Math.max(points, sequence.size());
                System.out.println(name + " created sequence " + sequence + " and has " + points + " points.");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
