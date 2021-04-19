package com.game.config;

public class Config {
    public static long TREE_GROW_TIME = 5000;
    public static boolean SHOW_FPS, DEBUG = false;
    public static int targetFPS = 60;

    public static void init(String[] args) {
        for (String arg : args){
            if(arg.equals("d")){DEBUG=true;}
            else if(arg.equals("f")){SHOW_FPS=true;}
        }
    }
}
