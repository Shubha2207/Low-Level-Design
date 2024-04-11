package solidPrinciples.l;

/**
 * L : Liskov Substitution Principle
 * Subclass should extend all the functionalities of parent class and not narrow it down
 */
public class L {
    public static void main(String[] args) {
        /**
         * here motorcycle class extends all the functionality
         * but bicycle class narrowing it down by throwing exception
         */
        Motorcycle motorcycle = new Motorcycle();
        Bicycle bicycle = new Bicycle();

        Engine engine = motorcycle;
        engine.turnOn();
        engine = bicycle;
        engine.turnOn(); // throws runtime exception

        /**
         * we should be able to replace parent class object with subclass object
         * without changing the behavior (output might differ)
         *
         * also parent class should only have most generic functionality to avoid this issue.
         */

    }
}

interface Engine {
    void turnOn();
    void accelerate();
}

class Motorcycle implements Engine {
    boolean isEngineOn;
    double speed;

    @Override
    public void turnOn(){
        isEngineOn = true;
    }

    @Override
    public void accelerate(){
        speed += 10;
    }
}

class Bicycle implements Engine {
    double speed;
    @Override
    public void turnOn(){
        throw new RuntimeException("Engine not found");
    }

    @Override
    public void accelerate(){
        speed += 2;
    }
}