package without.strategyPattern;

public class SportVehicle extends Vehicle{

    @Override
    public void drive() {
        System.out.println("Special driving capability");
    }

    // SportVehicle and OffRoadVehicle has same code
}
