package designpatterns.behavioral;

import java.net.CacheRequest;

/**
 * Used to achieve loose-coupling in software design
 * request from client is passed through chain of objects
 * and objects decides who will process the request
 */
public class ChainOfResponsibilityDP {
    public static void main(String[] args) {
        /**
         * Real world example of this design pattern is ATM cash dispenser
         * if we request for 590/- bucks then
         * first request is sent to 100_Bucks_Dispenser which will provide 5 notes
         * then request is sent to 50_Bucks_Dispenser which will provide 1 note
         * then request is sent to 20_Bucks_Dispenser which will provide 2 notes
         * and at last request is sent to 10_Bucks_Dispenser which will provide 1 note
         *
         * this way request is passed through chain of handlers
         *
         * Here imp thing is client should not be aware about this chain
         * and order of chain is critical because incorrect order may lead to redundant handlers
         *
         * Every object in chain should have reference of next object
         *
         */

        ATMDispenser atmDispenser = new ATMDispenser();
        atmDispenser.dispenseMoney(5920); // here we are restricting amount to multiple of 10

    }
}

class ATMDispenser {
    /**
     * Create chain
     */
    CashDispenser hundredBucksDispenser = new HundredBucksDispenser();
    CashDispenser fiftyBucksDispenser = new FiftyBucksDispenser();
    CashDispenser tenBucksDispenser = new TenBucksDispenser();

    public ATMDispenser(){
        hundredBucksDispenser.setNext(fiftyBucksDispenser);
        fiftyBucksDispenser.setNext(tenBucksDispenser);
    }

    public void dispenseMoney(int amount){
        if(amount % 10 == 0){
            hundredBucksDispenser.dispense(amount);
        } else {
            System.out.println("Amount should be multiple of 10");
        }

    }
}

interface CashDispenser {
    void setNext(CashDispenser cashDispenser);
    void dispense(int amount);
}

class HundredBucksDispenser implements CashDispenser {

    private CashDispenser cashDispenser;

    @Override
    public void setNext(CashDispenser cashDispenser) {
        this.cashDispenser = cashDispenser;
    }

    @Override
    public void dispense(int amount) {
        if(amount > 100){
            int noOfNotes = amount / 100;
            int remainder = amount % 100;
            System.out.printf("Collect %d notes of $100\n", noOfNotes);
            if(remainder != 0){
                cashDispenser.dispense(remainder);
            }
        }else{
            cashDispenser.dispense(amount);
        }
    }
}

class FiftyBucksDispenser implements CashDispenser {

    private CashDispenser cashDispenser;

    @Override
    public void setNext(CashDispenser cashDispenser) {
        this.cashDispenser = cashDispenser;
    }

    @Override
    public void dispense(int amount) {
        if(amount > 50){
            int noOfNotes = amount / 50;
            int remainder = amount % 50;
            System.out.printf("Collect %d notes of $50\n", noOfNotes);
            if(remainder != 0){
                cashDispenser.dispense(remainder);
            }
        }else{
            cashDispenser.dispense(amount);
        }
    }
}

class TenBucksDispenser implements CashDispenser {

    private CashDispenser cashDispenser;

    @Override
    public void setNext(CashDispenser cashDispenser) {
        this.cashDispenser = cashDispenser;
    }

    @Override
    public void dispense(int amount) {
        if(amount > 10){
            int noOfNotes = amount / 10;
            int remainder = amount % 10;
            System.out.printf("Collect %d notes of $10\n", noOfNotes);
            if(remainder != 0){
                cashDispenser.dispense(remainder);
            }
        }else{
            cashDispenser.dispense(amount);
        }
    }
}