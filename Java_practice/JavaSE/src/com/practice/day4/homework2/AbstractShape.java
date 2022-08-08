package com.practice.day4.homework2;

public abstract class AbstractShape {
    protected int length;
    protected int width;

    public AbstractShape() {
        this.length = 1;
        this.width = 1;
    }

    public AbstractShape(int num) {
        this.length = num;
        this.width = num;
    }

    public AbstractShape(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public abstract int getResult();
}