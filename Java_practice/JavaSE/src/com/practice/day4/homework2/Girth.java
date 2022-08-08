package com.practice.day4.homework2;

public class Girth extends AbstractShape{

    public Girth() {
    }

    public Girth(int num) {
        super(num);
    }

    public Girth(int length, int width) {
        super(length, width);
    }

    @Override
    public int getResult() {
        return 2 * (length + width);
    }
}
