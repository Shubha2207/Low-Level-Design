package with.strategyPattern;

public class SportVehicle extends Vehicle{

    public SportVehicle(){
        super(new SpecialDriveStrategy());
    }

}
