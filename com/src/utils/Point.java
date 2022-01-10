package com.src.utils;

//Point class used to abstract pieces position in the board
public class Point {
    public int x;
    public int y;

    
    public Point(int y, int x){
        this.x = x;
        this.y  = y;
    }

    public void Update(int y, int x){
        this.x = x;
        this.y = y;
    }
}
