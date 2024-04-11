package solidPrinciples.o;

/**
 * O: Open-Closed Principle
 * Open for extension but close for modification
 * Instead of modifying already tested code, extend it if you want to add new functionality
 */
public class O {
    public static void main(String[] args) {
        /**
         * Instead of adding one more method to InvoiceDao class
         * I created interface for that and implemented save() method
         * for two separate class
         */
        InvoiceDao i1 = new DatabaseInvoiceDao();
        InvoiceDao i2 = new FileInvoiceDao();
        i1.save();
        i2.save();

        /**
         * In future if we want to add one more functionality of saving file to AWS S3;
         * we can implement same save() method without disturbing other classes
         */
    }
}

interface InvoiceDao{
    void save();
}

class DatabaseInvoiceDao implements InvoiceDao{
    @Override
    public void save(){
        System.out.println("Save to DB");
    }
}

class FileInvoiceDao implements InvoiceDao {
    @Override
    public void save() {
        System.out.println("Save to file");
    }
}