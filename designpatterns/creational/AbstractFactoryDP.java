package designpatterns.creational;

/**
 * This pattern is similar to Factory pattern but here in the Abstract Factory pattern,
 * we get rid of if-else or switch-case block and have a factory class for each sub-class.
 */
public class AbstractFactoryDP {
    public static void main(String[] args) {
        ComputerAbstractFactory windowsAbstractFactory = new WindowsFactory();
        Computer computer = ComputerFactory.getComputer(windowsAbstractFactory);
        computer.getSpecification();
        /**
         * able to create object without using any conditional block
         *
         * Though this pattern add an extra layer of abstraction to hide the implementation
         * It also introduces additional complexity to codebase and number of classes increases
         */
    }
}

/**
 * There are two types of computers windows and mac
 */
interface Computer {
    void getSpecification();
}

class Windows implements Computer {

    @Override
    public void getSpecification() {
        System.out.println("Windows computer: 16GB ram, 2TB HDD");
    }
}

class Mac implements Computer {

    @Override
    public void getSpecification() {
        System.out.println("Mac Book: 4GB ram, 1TB SDD");
    }
}

/**
 * Create Abstract factory for these two products
 */
interface ComputerAbstractFactory {
    Computer getComputer();
}

class WindowsFactory implements ComputerAbstractFactory {

    @Override
    public Computer getComputer() {
        return new Windows();
    }
}

class MacFactory implements ComputerAbstractFactory {

    @Override
    public Computer getComputer() {
        return new Mac();
    }
}

/**
 * Create factory class to create these products
 */
class ComputerFactory {
    public static Computer getComputer(ComputerAbstractFactory computerAbstractFactory) {
        return computerAbstractFactory.getComputer();
    }
}