package designpatterns.structural;

/**
 * Create a bridge between abstraction and implementation
 * this decoupling helps abstraction and implementation to vary independently
 */
public class BridgeDP {
    /**
     * Ex. we have Shape abstract class, for which we need to apply color for each shape
     * both Shape and Color can have different hierarchies e.g. Shape -> Circle|Square|Triangle and
     * Color -> Red|Green etc.
     * So to avoid the hard dependency, we use bridge design patten
     * This design pattern follows the notion of prefer Composition to inheritance
     *
     * You may find it similar to Strategy Design Pattern
     */
    public static void main(String[] args) {
        Shape square1 = new Square(new Green());
        Shape circle1 = new Circle(new Red());

        square1.applyColor();
        circle1.applyColor();
    }
}

// Abstraction Layer
abstract class Shape {
    protected ColorInterface color;

    Shape(ColorInterface color){
        this.color = color;
    }

    abstract void applyColor();
}

// Implementor
interface ColorInterface {
    void applyColor();
}

// Refined Abstraction
class Circle extends Shape {

    Circle(ColorInterface color) {
        super(color);
    }

    @Override
    void applyColor() {
        System.out.printf("Circle : ");
        color.applyColor();
    }
}

class Square extends Shape{
    Square(ColorInterface color) {
        super(color);
    }

    @Override
    void applyColor() {
        System.out.printf("Square : ");
        color.applyColor();
    }
}

// Concrete Implementations
class Red implements ColorInterface {

    @Override
    public void applyColor() {
        System.out.println("Apply Red Color");
    }
}

class Green implements ColorInterface {

    @Override
    public void applyColor() {
        System.out.println("Apply Green Color");
    }
}