package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player extends Thread{
    private String name;
    private Bag bag;
    private List<Token> tokens;
    public Player(String name,Bag bag){
        this.name=name;
        this.bag=bag;
        this.tokens=new ArrayList<>();
    }
public void run(){
        if(!bag.isEmpty()){

        }

}
}
