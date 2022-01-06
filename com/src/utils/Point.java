package com.src.utils;

public class Point {
    public int x;
    public int y;

    
    public Point(int y, int x){
        this.x = x;
        this.y  = y;
    }

    public void update(int y, int x){
        this.x = x;
        this.y = y;
    }
}
