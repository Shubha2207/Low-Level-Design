package designpatterns.creational;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Used to manage the pool of reusable objects
 */
public class ObjectsPoolDP {
    public static void main(String[] args) {
        /**
         * Client borrows from pool, use it and return back to pool
         * eg. db connection
         *
         * Advantage:
         * 1. reduce the overhead of creating resource(cpu/memory) intensive objects
         * 2. prevents from resource exhaustion by limiting the number of objects
         * 3. reduce the object initialization time
         *
         * Disadvantage:
         * 1. Need to be handled properly otherwise resource leakage might happen e.g. object is acquired but never released
         * 2. More memory is required as pool is maintained
         * 3. Additional overhead of pool management and thread safety
         *
         * Following singleton-pattern for pool-manager-creation is most imp thing in object-pools-pattern
         */

        DBConnPoolManager dbConnPoolManager = DBConnPoolManager.getInstance();
        DBConnection connection = null;
        for (int i = 0; i < 5; i++) {
            DBConnection dbConnection = dbConnPoolManager.getDBConnection();
            if(Objects.nonNull(dbConnection)){
                connection = dbConnection;
                System.out.println("Fetched db connection="+dbConnection.hashCode());
            }

        }
        dbConnPoolManager.releaseDBConnection(connection);

    }
}

// Resource
class DBConnection {
    public static DBConnection getDBConn() {
        return new DBConnection();
    }
}


// Resouce Pool Manager
class DBConnPoolManager {
    /**
     * To keep this thread safe, we will use double-locking-singleton-design-pattern
     * to create the object of this pool manager
     */
    private static DBConnPoolManager dbConnPoolManager;

    private List<DBConnection> freeDBConnections = new ArrayList<>();
    private List<DBConnection> inUseDBConnections = new ArrayList<>();

    private final static int MAX_POOL_SIZE = 3;
    private final static int INITIAL_POOL_SIZE = 1;

    // singleton-pattern with double locking
    public static DBConnPoolManager getInstance() {
        if (dbConnPoolManager == null) {
            synchronized (DBConnPoolManager.class) {
                if (dbConnPoolManager == null) {
                    dbConnPoolManager = new DBConnPoolManager();
                }
            }
        }
        return dbConnPoolManager;
    }

    private DBConnPoolManager() {
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            DBConnection dbConn = DBConnection.getDBConn();
            freeDBConnections.add(dbConn);
        }
    }

    public synchronized DBConnection getDBConnection() {
        if (freeDBConnections.isEmpty()) {
            if (inUseDBConnections.size() < MAX_POOL_SIZE) {
                DBConnection dbConn = DBConnection.getDBConn();
                freeDBConnections.add(dbConn);
                System.out.println("Created new db connection=" + dbConn.hashCode());

            } else {
                System.out.println("Max limit reached! Cannot create new db connection.");
                return null;
            }
        }
        DBConnection dbConnection = freeDBConnections.remove(0);
        inUseDBConnections.add(dbConnection);
        return dbConnection;
    }

    public synchronized void releaseDBConnection(DBConnection dbConnection) {
        if (Objects.nonNull(dbConnection)) {
            inUseDBConnections.remove(dbConnection);
            freeDBConnections.add(dbConnection);
            System.out.println("Release db connnection object="+dbConnection.hashCode());
        }
    }
}