package with.strategyPattern;

public class Impl {

    public static void main(String[] args) {
        SportVehicle sportVehicle = new SportVehicle();
        sportVehicle.drive();

        PassengerVehicle passengerVehicle = new PassengerVehicle();
        passengerVehicle.drive();

        Vehicle vehicle = new SportVehicle();
        vehicle.drive();
    }
}
