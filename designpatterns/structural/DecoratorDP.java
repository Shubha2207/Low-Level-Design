package designpatterns.structural;

import solidPrinciples.l.L;

import java.io.FileReader;

/**
 * Used to add new functionality to object at runtime
 */
public class DecoratorDP {
    /**
     * e.g. we have a car, we can add functionality like sports car set and sedan car speakers
     * Decorator pattern is useful when number of choices are more.
     * Instead of creating concrete implementations for each type of car, which may lead to unmanageable code as
     * types of car increases i.e. class-explosion scenario e.g. basic_car + sport, basic_car+luxury, basic_car+sport+luxury; we separate out
     * the choices from base_component
     *
     * Base_Component : abstract class or interface that defines the base functionality that need to be implemented
     * Base_Component_Implementation: class that implementation basic functionality
     * Decorator: it implements the base component interface and also has a HAS-A relationship, which allows the child decorator
     * classes to add new functionality on the top of base component
     */
    public static void main(String[] args) {
        Car car = new BasicCar();
        // car with mixture of sport and luxury features
        Car sportLuxuryCar = new SportSeatDecorator(new LuxuryScreenDecorator(car));
        sportLuxuryCar.assembleCar();
        System.out.println();
        // same car object with different features, without affecting original instance
        Car luxuryCar = new LuxuryScreenDecorator(car);
        luxuryCar.assembleCar();
        System.out.println();
        car.assembleCar();
    }
}

// Base component
interface Car {
    void assembleCar();
}

// Base component Implementation
class BasicCar implements Car {

    @Override
    public void assembleCar() {
        System.out.println("Create the basic structure");
    }
}

// Decorator
class CarDecorator implements Car {

    private final Car car;

    public CarDecorator(Car car){
        this.car = car;
    }

    @Override
    public void assembleCar() {
        this.car.assembleCar();
    }
}

class SportSeatDecorator extends CarDecorator{

    public SportSeatDecorator(Car car) {
        super(car);
    }

    @Override
    public void assembleCar(){
        // calling the exiting functionality first
        super.assembleCar();
        // adding new functionality on top of existing one
        System.out.println("Add sport seats");
    }
}

class LuxuryScreenDecorator extends CarDecorator {

    public LuxuryScreenDecorator(Car car) {
        super(car);
    }

    @Override
    public void assembleCar(){
        // calling the exiting functionality first
        super.assembleCar();
        // adding new functionality on top of existing one
        System.out.println("Add luxury monitor screen");
    }
}