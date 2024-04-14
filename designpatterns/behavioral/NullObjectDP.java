package designpatterns.behavioral;

/**
 * Replace null with NullObject which basically reflects do-nothing or default behaviour;
 * Useful pattern to get rid of null-checks in every method
 */
public class NullObjectDP {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.getVehicle("car");
        car.getSpecification();

        Vehicle vehicle = VehicleFactory.getVehicle("bike");
        vehicle.getSpecification();
    }

}

/**
 * Using factory pattern to create object of Vehicle
 */
class VehicleFactory {
    public static Vehicle getVehicle(String condition){
        if("car".equalsIgnoreCase(condition)){
            return new Car();
        }
        return new NullVehicle();
    }
}

/**
 * Abstracting Vehicle
 */
interface Vehicle {
    void getSpecification();
}

class Car implements Vehicle{

    @Override
    public void getSpecification() {
        System.out.println("Car: 4 Seats, 30Ltr fuel tank");
    }
}

/**
 * Default behaviour
 */
class NullVehicle implements Vehicle {

    @Override
    public void getSpecification() {
        System.out.println("NullVehicle: ");
    }
}