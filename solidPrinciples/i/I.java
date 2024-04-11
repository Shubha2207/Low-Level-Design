package solidPrinciples.i;

/**
 * I : Interface Segmented Principle
 * Interface should be such that, implementing class should not have to implement
 * unnecessary methods
 */
public class I {
    public static void main(String[] args) {
        /**
         * here Waiter class has to implemented unnecessary methods
         * hence segment the interface such that implementing class should not have to
         * implement unnecessary methods
         */

        RestaurantEmp emp = new Waiter();
        emp.cookFood(); // unnecessary method implementation
        emp.takeOrder();

        WaiterInterface waiterInterface = new WaiterClazz();
        waiterInterface.takeOrder();
        waiterInterface.setTableForCustomer();
    }
}

interface RestaurantEmp {
    void washDishes();
    void takeOrder();
    void cookFood();
}

class Waiter implements RestaurantEmp {

    @Override
    public void washDishes() {
        System.out.println("Not my task");
    }

    @Override
    public void takeOrder() {
        System.out.println("Order Received");
    }

    @Override
    public void cookFood() {
        System.out.println("Not my task");
    }
}

interface WaiterInterface {
    void takeOrder();
    void setTableForCustomer();
}

class WaiterClazz implements WaiterInterface {

    @Override
    public void takeOrder() {
        System.out.println("Order confirmed");
    }

    @Override
    public void setTableForCustomer() {
        System.out.println("Clearing the table");
    }
}