package com.example.coursework;

import java.util.ArrayList;

public class Collection {

    private ArrayList<Coin> coinCollection =new ArrayList<>();

    public final String nameCollection;


    public Collection(String nameCollection) {
        this.nameCollection = nameCollection;

    }

    public ArrayList<Coin> getCoinCollection() {
        return coinCollection;
    }

    public void addToCollection(Coin coin){
        coinCollection.add(coin);

    }

}

