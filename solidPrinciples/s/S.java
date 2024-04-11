package solidPrinciples.s;

/**
 * S: Single Responsibility Principle
 * A class should only have one responsibility
 */
public class S {
    public static void main(String[] args) {
        Marker m = new Marker("123", 10);
        Invoice invoice = new Invoice(m, 20);
        // so many functionality in single class
        System.out.println(invoice.calculatePrice());
        invoice.printInvoice();
        invoice.saveToDB();

        // refactored code
        System.out.println(invoice.calculatePrice());
        InvoiceDao invoiceDao = new InvoiceDao();
        invoiceDao.saveToDB(invoice);
        InvoicePrinter invoicePrinter = new InvoicePrinter();
        invoicePrinter.printInvoice(invoice);
    }
}

class Marker {
    String id;
    double price;

    Marker(String id, double price){
        this.id = id;
        this.price = price;
    }
}

class Invoice {
    Marker marker;
    int quantity;

    Invoice(Marker marker, int quantity){
        this.marker = marker;
        this.quantity = quantity;
    }

    double calculatePrice(){
        return this.marker.price * this.quantity;
    }

    /**
    * Here printInvoice and saveToDB methods are additional responsibilities for a class
     * hence we need to refactor the code and create separate class for these functionalities
     */

    void printInvoice(){
        System.out.println(this.hashCode());
    }

    void saveToDB(){
        System.out.println("Saved to DB");
    }

}

class InvoiceDao {
    void saveToDB(Invoice invoice){
        System.out.println(invoice.hashCode() + " Saved to DB using invoiceDao");
    }
}

class InvoicePrinter {
    void printInvoice(Invoice invoice){
        System.out.println(invoice.hashCode() + " by invoicePrinter");
    }
}