package designpatterns.behavioral;

/**
 * Useful when we have multiple algorithms for specific task, and we want
 * our application to be flexible to choose any of the algorithm at runtime
 */
public class StrategyDP {
    public static void main(String[] args) {
        Shopping shopping = new Shopping();
        // passing the strategy as an argument
        shopping.pay(new CreditCardPayment());
        shopping.pay(new UpiPayment());
    }
}


/**
 * Abstracting strategy
 */
interface PaymentStrategy {
    void pay();
}

/**
 * various ways to perform this payment task
 */
class CreditCardPayment implements PaymentStrategy {

    @Override
    public void pay() {
        System.out.println("Paid by credit card");
    }
}

class UpiPayment implements PaymentStrategy {

    @Override
    public void pay() {
        System.out.println("Paid by UPI");
    }
}

/**
 * base class for which any of the above strategy is used to perform the task
 */
class Shopping {
    public void pay(PaymentStrategy paymentStrategy){
        paymentStrategy.pay();
    }
}