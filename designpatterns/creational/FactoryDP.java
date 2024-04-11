package designpatterns.creational;

import solidPrinciples.s.S;

/**
 * create an object of class based on some condition
 */
public class FactoryDP {
    public static void main(String[] args) {
        /**
         * without Factory pattern
         * We would have instantiated Circle and Square object in main method and created two getShape methods to return
         * Circle and Square object. This will cause duplicate code logic
         * e.g.
         * Circle getCircleShape(){
         *  return new Circle();
         * }
         */

        ShapeFactory shapeFactory = new ShapeFactory();

        shapeFactory.getShape("Circle").draw();
        shapeFactory.getShape("Square").draw();

        /**
         * with Factory pattern
         * Code is more clean and easy to extend
         */

    }
}

class ShapeFactory {
    Shape getShape(String shape){
        switch (shape){
            case "Circle":
                return new Circle();
            case "Square":
                return new Square();
            default:
                return null;
        }
    }
}

interface Shape {
    void draw();
}

class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Circle");
    }
}

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Square");
    }
}