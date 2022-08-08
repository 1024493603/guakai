package com.practice.day4.homework2;

public class Main {
    public static void main(String[] args) {

        Area area1 = new Area();
        show(area1);
        Area area2 = new Area(2);
        show(area2);
        Area area3 = new Area(3, 4);
        show(area3);

        Girth girth1 = new Girth();
        show(girth1);
        Girth girth2 = new Girth(2);
        show(girth2);
        Girth girth3 = new Girth(3, 4);
        show(girth3);
    }

    public static void show (AbstractShape shape) {
        int result = shape.getResult();
        System.out.println(result);
    }
}
