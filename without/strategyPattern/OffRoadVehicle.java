package without.strategyPattern;

public class OffRoadVehicle extends Vehicle{


    @Override
    public void drive(){
        System.out.println("Special driving capability");
    }

    // SportVehicle and OffRoadVehicle has same code

}
