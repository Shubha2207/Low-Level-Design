package designpatterns.behavioral;

/**
 * Used when we want all classes to follow some specific steps to process the task
 * but also need to provide flexibility that each class has its own business logic
 */
public class TemplateMethodDP {
    public static void main(String[] args) {
        /**
         * Assuming Payment method flow
         * 1. payment to UPI
         * 2. payment to QR
         * both flows have to follow some basic steps e.g. input-validation, checking balance, enter amount and pin etc
         */

        Payment payment1 = new UPIPayment();
        payment1.sendMoney();

        Payment payment2 = new QRPayment();
        payment2.sendMoney();

        /**
         * if some steps are common, you can define them in Payment class to avoid duplicate code
         */
    }
}

abstract class Payment {

    /**
     * Child class can change the business logic as per need
     */
    public abstract void validateInput();
    public abstract void checkBalance();
    public abstract void makePayment();

    /**
     * This is the template method.
     * making it final so that child classes cannot override it.
     * Child class has to follow specific steps to complete the task
     */
    public final void sendMoney(){
        // step 1
        validateInput();

        // step 2
        checkBalance();

        // step 3
        makePayment();
    }
}

class UPIPayment extends Payment {

    @Override
    public void validateInput() {
        System.out.println("UPI validation");
    }

    @Override
    public void checkBalance() {
        System.out.println("check balance");
    }

    @Override
    public void makePayment() {
        System.out.println("enter amount and pin and submit");
    }
}

class QRPayment extends Payment {

    @Override
    public void validateInput() {
        System.out.println("Merchant QR validation");
    }

    @Override
    public void checkBalance() {
        System.out.println("check balance");
    }

    @Override
    public void makePayment() {
        System.out.println("enter amount and pin and submit");
    }
}