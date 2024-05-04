package designpatterns.behavioral;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Used when you are interested in a particular subject (called as observable) and
 * wanted to get notified on state-change
 */
public class ObserverDP {
    public static void main(String[] args) {
        /**
         * There could be multiple observers of an observable subject
         * and all observers should be notified everytime when there is state-change
         */
        Observable batObservable = new ProductObservable();
        Observer virat = new Customer();
        Observer rohit = new Customer();

        virat.setObservable(batObservable);
        rohit.setObservable(batObservable);

        // updating data for the observable
        batObservable.setData(100);
        batObservable.setData(75);

        /**
         * There could be another observable object and observers could also be listening to that
         * hence setObservable() is used
         */

        Observable helmetObservable = new ProductObservable();
        rohit.setObservable(helmetObservable); // this will unregister it from the existing observable

        // this update will notify only rohit
        helmetObservable.setData(500);
        // this update will notify only virat
        batObservable.setData(30);

        /**
         * if you have already gone through Mediator design pattern,
         * you might find observer pattern similar to mediator but actually there is a difference
         * let's take example of each pattern to understand the difference
         *
         * The observer pattern: Class Product, can have zero or more observers of type CustomerClazz registered with it.
         * When something in Product object is changed it notifies all the observers.
         *
         * The mediator pattern: You have some number of instances of class X (or maybe even several types:X, Y & Z),
         * and they wish to communicate with each other (but you don't want each to have explicit references to each other),
         * so you create a mediator class M. Each instance of X has a reference to a shared instance of M,
         * through which it can communicate with the other instances of X (or X, Y and Z).
         */

    }
}

interface Observable {
    void registerObserver(Observer observer);
    void unRegisterObserver(Observer observer);
    void notifyObservers();
    Object getData();
    void setData(Object updatedData);
}

class ProductObservable implements Observable {

    private List<Observer> observers;
    private Integer productCount;

    public ProductObservable(){
        this.observers = new LinkedList<>();
        productCount = 0;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unRegisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.receiveUpdate();
        }
    }

    @Override
    public Object getData() {
        return productCount;
    }

    /**
     * When data is updated then notify all observers
     * @param newProductCount
     */
    @Override
    public void setData(Object newProductCount){
        this.productCount = (Integer) newProductCount;
        notifyObservers();
    }
}

interface Observer {
    void receiveUpdate();
    void setObservable(Observable observable);
}

class Customer implements Observer {

    Observable observable;

    public void setObservable(Observable observable){
        // if observer want to listen to another observable
        // then existing one must unregister it
        if(Objects.nonNull(this.observable)){
            this.observable.unRegisterObserver(this);
        }
        this.observable = observable;
        observable.registerObserver(this);
    }

    @Override
    public void receiveUpdate() {
        System.out.println("Received by " + this.hashCode() +" ; Observable " + observable.hashCode() +" data updated to : "+observable.getData());
    }
}
