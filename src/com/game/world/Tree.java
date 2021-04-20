package com.game.world;

import java.util.HashMap;

public class Tree {
    private final int id;
    private final int cost;
    private final int coins;
    private final String name;

    public static HashMap<Integer, Tree> trees = new HashMap<>();
    public static int selectedTree = 1;

    public static void init(){
        trees.put(1, new Tree(1, 0, 5, "Arvere1"));
        trees.put(2, new Tree(2, 100, 200, "Arvere2"));
        trees.put(3, new Tree(3, 2000, 4000, "Arvere3"));
    }

    public Tree(int id, int cost, int coins, String name) {
        this.id = id;
        this.cost = cost;
        this.coins = coins;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public int getCoins() {
        return coins;
    }

    public String getName() {
        return name;
    }



}
