package designpatterns.behavioral;

import java.util.Stack;
import java.util.StringJoiner;

/**
 * used to save the state of an object so that we can restore it later on.
 * also, saved state data of the object shuold not be accessible outside the Object, this protects the integrity of saved state data.
 */
public class MementoDP {
    public static void main(String[] args) {
        /**
         * There are three components – originator, memento and caretaker.
         * The originator is the Object whose state needs to be saved and restored, and it uses an inner class to save the state of Object.
         * The inner class is called “Memento”, and it’s private so that it can’t be accessed from other objects.
         * The caretaker maintains the list of memento objects
         */
        Price price1 = new Price();
        price1.setAmount(110);

        PriceOriginator priceOriginator = new PriceOriginator(price1);
        PriceMemento memento1 = priceOriginator.createMemento();

        CareTaker careTaker = new CareTaker();
        careTaker.addMemento(memento1);

        // updating price
        price1.setAmount(125);
        PriceMemento memento2 = priceOriginator.createMemento();
        careTaker.addMemento(memento2);

        // updating price
        price1.setAmount(90);
        PriceMemento memento3 = priceOriginator.createMemento();
        careTaker.addMemento(memento3);

        careTaker.displayHistory();

        // updating price
        price1.setAmount(150);

        PriceMemento lastMemento = careTaker.getLastMemento();

        // before restoring the price
        System.out.println(price1.getAmount());

        priceOriginator.restoreFromMemento(lastMemento);

        // after restoring the price
        System.out.println(price1.getAmount());


    }
}

class Price {
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Price.class.getSimpleName() + "[", "]")
                .add(this.hashCode()+"-amount=" + amount)
                .toString();
    }
}

/**
 * Orginator of state, to create or restore from memento
 */
class PriceOriginator {
    private Price price;

    public PriceOriginator(Price price){
        this.price = price;
    }

    public PriceMemento createMemento(){
        PriceMemento priceMemento = new PriceMemento(price);
        return priceMemento;
    }

    public Price restoreFromMemento(PriceMemento priceMemento){
        this.price.setAmount(priceMemento.getPriceMemento().getAmount());
        return this.price;
    }


}

/**
 * Create memento or snapshot of state
 */
class PriceMemento {

    /**
     * here memento class can have same member variables as Price class
     * but if there are too many fields then duplicating all that would not be right
     * hence I have used Price class as a member variable for the memento class
     */
    private Price priceMemento;


    // creating deep copy of price
    public PriceMemento(Price price){
        this.priceMemento = new Price();
        this.priceMemento.setAmount(price.getAmount());
    }

    public Price getPriceMemento() {
        return priceMemento;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PriceMemento.class.getSimpleName() + "[", "]")
                .add(priceMemento.hashCode() + "-memento=" + priceMemento)
                .toString();
    }
}

/**
 * maintains the list of snapshots/mementos
 */
class CareTaker {
    Stack<PriceMemento> priceHistory;

    public CareTaker(){
        priceHistory = new Stack<>();
    }

    public void addMemento(PriceMemento memento){
        priceHistory.push(memento);
    }

    public PriceMemento getLastMemento(){
        return priceHistory.pop();
    }

    // created only for debugging purpose
    public void displayHistory(){
        System.out.println(priceHistory);
    }
}