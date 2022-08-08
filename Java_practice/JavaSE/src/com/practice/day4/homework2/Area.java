package com.practice.day4.homework2;

public class Area extends AbstractShape{

    public Area() {
    }

    public Area(int num) {
        super(num);
    }

    public Area(int length, int width) {
        super(length, width);
    }

    @Override
    public int getResult() {
        return length * width;
    }
}
