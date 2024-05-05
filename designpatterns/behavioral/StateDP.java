package designpatterns.behavioral;

import java.util.*;

/**
 * Used when behavior of an object changes based on state
 */
public class StateDP {
    /**
     * Let's take an example of Vending Machine
     * there could be multiple states of it like idle, user selecting product, user inserting money, dispensing product
     * based on each state there are various actions
     * 1. selecting product : get product code, refund amount if canceled
     * 2. inserting money : receive amount, refund amount if canceled
     * 3. dispensing product : dispense product, return the change
     *
     * if we try to implement this using if-else conditions then client code would tightly couple and hard to manage if more states are added
     * hence we can use State Design pattern, so that code will be loosely coupled
     *
     * This pattern is a bit similar to strategy pattern
     * but in strategy dp, we have multiple ways to perform same operation e.g. payment using cc or upi
     * and in state dp, we have multiple operations, and we use polymorphism to abstract actual functinality
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ContextProduct vendingMachine = new VendingMachine(new HashMap<>(Map.of("112-choco", 10, "115-salty", 15, "213-spicy", 35)));
        vendingMachine.addProduct(Map.of("302-crunchy", 25));
        while(true){
            vendingMachine.displayProducts();

            // product selection
            vendingMachine.setState(new ProductSelection(vendingMachine));
            String productCode = (String) vendingMachine.performOperation();
            if(Objects.isNull(productCode)) continue;

            // money insertion
            vendingMachine.setState(new MoneyInsertion(vendingMachine, productCode));
            Integer amount = (Integer) vendingMachine.performOperation();
            if(Objects.isNull(amount)) continue;

            // dispense product
            vendingMachine.setState(new ProductDispense(vendingMachine, productCode));
            String item = (String) vendingMachine.performOperation();
            if(Objects.isNull(item)) {
                continue;
            }else{
                System.out.println(item + " item dispensed successfully!");
            }

            System.out.println("Do you want to proceed?");
            String response = sc.next();
            if("no".equalsIgnoreCase(response)){
                break;
            }
        }
    }

}

/**
 * States of the product
 */
interface State {
    Object doAction();
}

class ProductSelection implements State {
    private final ContextProduct contextProduct;
    Scanner sc = new Scanner(System.in);

    public ProductSelection(ContextProduct contextProduct){
        this.contextProduct = contextProduct;
    }

    @Override
    public Object doAction() {
        System.out.println("Enter the product code");
        String productCode = sc.next();
        if(isValidProductCode(productCode)){
            System.out.println("Do you want to proceed?");
            String response = sc.next();
            if("yes".equalsIgnoreCase(response)){
                return productCode;
            }
        }else{
            System.out.println("Enter valid product code.");
        }
        return null;
    }

    private boolean isValidProductCode(String productCode) {
        Map products = (Map) contextProduct.getProduct();
        return products.containsKey(productCode);
    }
}

class MoneyInsertion implements State {

    private final ContextProduct contextProduct;
    private final String item;
    Scanner sc = new Scanner(System.in);

    public MoneyInsertion(ContextProduct contextProduct, String item){
        this.contextProduct = contextProduct;
        this.item = item;
    }

    @Override
    public Object doAction() {
        System.out.println("Insert the money");
        int amount = sc.nextInt();
        // limiting it to insert exact amount only
        if(isExactAmountInserted(amount)){
            System.out.println("Do you want to proceed?");
            String response = sc.next();
            if("yes".equalsIgnoreCase(response)){
                return amount;
            }
        } else{
            System.out.println("Insert exact amount.");
        }
        return null;
    }

    private boolean isExactAmountInserted(int amount) {
        Map products = (Map) contextProduct.getProduct();
        return amount == (int) products.get(item);
    }
}

class ProductDispense implements State {

    private final ContextProduct contextProduct;
    private final String item;

    public ProductDispense(ContextProduct contextProduct, String item){
        this.contextProduct = contextProduct;
        this.item = item;
    }

    @Override
    public Object doAction() {
        Map products = (Map) contextProduct.getProduct();
        contextProduct.removeProduct(Map.of(item, products.get(item)));
        return item;
    }
}

/**
 * Context
 */
interface ContextProduct {
    void addProduct(Object product);
    void removeProduct(Object product);
    void setState(State state);
    Object performOperation();
    Object getProduct();
    void displayProducts();
}


class VendingMachine implements ContextProduct{
    // map of product code and price
    private final Map<String, Integer> vendingItems;

    private State state;

    public VendingMachine(Map<String, Integer> vendingItems){
        this.vendingItems = vendingItems;
    }


    @Override
    public void addProduct(Object product) {
        Map<String, Integer> item = (Map) product;
        vendingItems.putAll(item);
    }

    @Override
    public void removeProduct(Object product) {
        Set<String> productCodes = ((Map<String, Integer>) product).keySet();
        productCodes.forEach(productCode -> vendingItems.remove(productCode));
    }

    @Override
    public void setState(State state){
        this.state = state;
    }

    @Override
    public Object performOperation() {
        return this.state.doAction();
    }

    @Override
    public Object getProduct(){
        return this.vendingItems;
    }

    @Override
    public void displayProducts() {
        System.out.println(vendingItems);
    }

}