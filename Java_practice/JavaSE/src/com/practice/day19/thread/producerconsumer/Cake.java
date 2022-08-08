package com.practice.day19.thread.producerconsumer;

public class Cake {
    private String name;

    public Cake(String name) {
        this.name = name;
    }

    public Cake() {
    }

    @Override
    public String toString() {
        return "Cake{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
