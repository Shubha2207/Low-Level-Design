package with.strategyPattern;

public class Vehicle {
    DriveStrategy driveStrategy;


    // injecting required drive strategy
    public Vehicle(DriveStrategy driveStrategy){
        this.driveStrategy = driveStrategy;
    }

    public void drive(){
        driveStrategy.drive();
    }
}
