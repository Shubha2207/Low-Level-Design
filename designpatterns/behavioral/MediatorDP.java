package designpatterns.behavioral;

import java.util.LinkedList;
import java.util.List;

/**
 * Used to achieve loose-coupling by providing centralized communication
 * medium between different object in the system
 */
public class MediatorDP {
    public static void main(String[] args) {
        /**
         * Let's take an example of online auction system
         * when a bidder place a bid, other bidders should be notified
         *
         * if we try to make each bidder communicate to every other bidder then
         * that would be tight coupling and hard to maintain
         *
         * Hence, we use mediator so that we can keep the communication logic at center point
         *
         * other examples are Air traffic control, Group Chatting application
         */

        Mediator auctionMediator = new AuctionMediator();
        Colleague c1 = new Bidder();
        c1.setMediator(auctionMediator);
        Colleague c2 = new Bidder();
        c2.setMediator(auctionMediator);
        Colleague c3 = new Bidder();
        c3.setMediator(auctionMediator);

        c1.sendMessage("Bid of $100");
        System.out.println();
        c3.sendMessage("Bid of $125");

        /**
         * so here if you see, no object is directly communicating with each other,
         * or you can put it as no bidder knows about other bidders
         *
         * if you want to understand the difference between observer and mediator design pattern
         * check the ObserverDP.java
         */
    }
}

/**
 * each object in the system is called as Colleague
 */
interface Colleague {
    void setMediator(Mediator mediator);
    void sendMessage(String msg);
    void receiveMessage(String msg);
}

class Bidder implements Colleague{

    private Mediator mediator;

    /**
     * we can either use parameterized constructor or setter
     * to set the mediator for a colleague
     */
    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
        mediator.addColleague(this);
    }

    @Override
    public void sendMessage(String msg) {
        System.out.println(this.hashCode() + " sent a message : " + msg);
        mediator.broadcastMessage(msg, this);
    }

    @Override
    public void receiveMessage(String msg) {
        System.out.println(this.hashCode() + " received the message : " + msg);
    }
}

/**
 * Mediator acts as a central point of communication
 */
interface Mediator {
    void addColleague(Colleague colleague);
    void broadcastMessage(String msg, Colleague colleague);
}

class AuctionMediator implements Mediator {

    private List<Colleague> colleagues;

    public AuctionMediator() {
        colleagues = new LinkedList<>();
    }

    @Override
    public void addColleague(Colleague colleague) {
        colleagues.add(colleague);
    }

    @Override
    public void broadcastMessage(String msg, Colleague colleague) {
        for (Colleague c: colleagues) {
            // skip sender of message
            if(c != colleague){
                c.receiveMessage(msg);
            }
        }
    }
}