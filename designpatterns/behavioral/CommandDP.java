package designpatterns.behavioral;

/**
 * Useful to loosely couple the request-response model
 */
public class CommandDP {
    public static void main(String[] args) {
        /**
         * There are three layers in this pattern : Invoker -> Command -> Receiver
         * the client sends the request to invoker;
         * invoker passes it to the encapsulated command object;
         * command object executes the appropriate method of receiver
         */

        HomeAppliances bulb = new Bulb();
        HomeAppliances ac = new AC();
        RemoteController.setCommand(new TurnOnCommand(bulb));
        RemoteController.executeCommand();
        RemoteController.setCommand(new TurnOnCommand(ac));
        RemoteController.executeCommand();
        RemoteController.setCommand(new TurnOffCommand(bulb));
        RemoteController.executeCommand();
    }
}

/**
 * We have multiple home electrical appliances, and we need to command them
 * This is Receiver layer because it receives the command from Invoker
 */
interface HomeAppliances {
    void turnOn();
    void turnOff();
}

class Bulb implements HomeAppliances {

    @Override
    public void turnOn() {
        System.out.println("Turning on the bulb");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off the bulb");
    }
}

class AC implements HomeAppliances {

    @Override
    public void turnOn() {
        System.out.println("Turning on the AC");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off the AC");
    }
}

/**
 * Abstracting commands
 */
interface ICommand {
    void execute();
}

/**
 * Creating class for each command that we need to send to receiver
 */
class TurnOnCommand implements ICommand {
    /**
     * we need to initialize the command for specific appliance
     */
    HomeAppliances homeAppliances;

    public TurnOnCommand(HomeAppliances homeAppliances){
        this.homeAppliances = homeAppliances;
    }

    @Override
    public void execute() {
        homeAppliances.turnOn();
    }
}

class TurnOffCommand implements ICommand {

    HomeAppliances homeAppliances;

    public TurnOffCommand(HomeAppliances homeAppliances){
        this.homeAppliances = homeAppliances;
    }

    @Override
    public void execute() {
        homeAppliances.turnOff();
    }
}

/**
 * Invoker Layer initializes the command first and then execute it for specific appliance
 */
class RemoteController {
    private static ICommand command;
    public static void setCommand(ICommand iCommand){
        command = iCommand;
    }

    public static void executeCommand(){
        if(command != null){
            command.execute();
        }
    }
}
