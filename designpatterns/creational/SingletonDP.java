package designpatterns.creational;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Used to create only one instance of the class
 */
public class SingletonDP {
    public static void main(String[] args){
        /**
         * There are four ways to achieve singleton pattern
         * 1. Eager Initialization
         * 2. Lazy Initialization
         * 3. synchronized keyword
         * 4. Double Locking
         */

        // Object creation is restricted
        // MongoDbConn conn = new MongoDbConn(); // throws compile time error

        // Eager initialization
        MongoDbConn mongoDbConn = MongoDbConn.getInstance();
        System.out.println(mongoDbConn);

        // Lazy initialization
        MySqlDbConn mySqlDbConn = MySqlDbConn.getInstance();
        System.out.println(mySqlDbConn);

        // synchronized keyword
        DocDbConn docDbConn = DocDbConn.getInstance();
        System.out.println(docDbConn);

        // double locking
        DynamoDbConn dynamoDbConn = DynamoDbConn.getInstance();
        System.out.println(dynamoDbConn);
    }
}

/**
 * Singleton pattern using eager initialization
 * we are creating object even before invocation of getInstance() method
 * object is created during class loading since object is static
 */
class MongoDbConn {
    private static MongoDbConn mongoDbConn = new MongoDbConn();

    // making default constructor private
    // so that we can restrict the object creation
    private MongoDbConn() {
    }

    // making this method static
    // so that, object is not required to invoke it
    public static MongoDbConn getInstance() {
        return mongoDbConn;
    }
}

/**
 * Singleton pattern using lazy initialization
 * we will be creating object only after invocation of getInstance() method
 */
class MySqlDbConn {

    private static MySqlDbConn conn;

    private MySqlDbConn() {
    }

    public static MySqlDbConn getInstance() {
        if (conn == null) {
            conn = new MySqlDbConn();
        }
        return conn;
    }

    /**
     * IMPORTANT IMPORTANT IMPORTANT
     * ------------------------------
     * this implementation is not thread safe
     * if multiple thread invoke getInstance method at same time,
     * then there might be a possibility that more than one object is created
     */

}

/**
 * Singleton pattern using synchronized keyword
 */
class DocDbConn {
    // lazy initialization
    private static DocDbConn conn;

    private DocDbConn(){}

    public static synchronized DocDbConn getInstance(){
        if(conn == null){
            conn = new DocDbConn();
        }
        return conn;
    }

    /**
     * IMPORTANT IMPORTANT IMPORTANT
     * ------------------------------
     * this implementation is thread safe, but it's expensive
     * every time a thread tries to invoke getInstance method, a lock is acquired to stop other threads
     * this locking is expensive and can cause performance degradation
     */
}


/**
 * Singleton pattern using double locking
 * this is used in enterprise level application to make object creation thread safe and performant
 */
class DynamoDbConn {
    private static DynamoDbConn conn;

    private DynamoDbConn(){}

    public static DynamoDbConn getInstance(){
        if(conn == null){
            synchronized (DynamoDbConn.class){  // if two threads t1, t2 reached at this point at same time
                if(conn == null){ // only one thread will be allowed to enter and object will be created and
                    conn = new DynamoDbConn(); // hence next thread will not create an object even if it enters the block
                }
            }
        }
        return conn;
    }
    /**
     * IMPORTANT IMPORTANT IMPORTANT
     * ------------------------------
     * this implementation is performant and thread safe as well
     * synchronized block will allow only one thread to enter even if two threads arrive at same time
     * and since we are checking {if conn==null} twice, object will be created once
     */
}
