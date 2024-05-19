package designpatterns.structural;

import java.security.PublicKey;

/**
 * proxy acts as a placeholder of actual object and perform some prechecks like
 * access control, caching, logging before delegating task to actual object
 */
public class ProxyDP {
    public static void main(String[] args) {
        EmpDao empDao = new EmpDaoProxy();
        empDao.create("Admin", "122334");
        empDao.delete("USER", "122334");
        /**
         * Proxy checks whether client is authorised
         */
    }
}

interface EmpDao {
    void create(String client, String empId);

    void get(String client, String empId);

    void delete(String client, String empId);
}

/**
 * Data Access Layer : Connects with DB and performs CRUD operations
 */
class EmpDaoImpl implements EmpDao {

    @Override
    public void create(String client, String empId) {
        System.out.println("New Record Created For: " + empId);
    }

    @Override
    public void get(String client, String empId) {
        System.out.println("Record Fetched For: " + empId);
    }

    @Override
    public void delete(String client, String empId) {
        System.out.println("Record Deleted For: " + empId);
    }
}

/**
 * Proxy used for authorization
 */
class EmpDaoProxy implements EmpDao {

    private EmpDao empDao;

    public EmpDaoProxy() {
        this.empDao = new EmpDaoImpl();
    }

    @Override
    public void create(String client, String empId) {
        if ("ADMIN".equalsIgnoreCase(client)) {
            empDao.create(client, empId);
            return;
        }
        System.out.println("Access Denied");
    }

    @Override
    public void get(String client, String empId) {
        empDao.get(client, empId);
    }

    @Override
    public void delete(String client, String empId) {
        if ("ADMIN".equalsIgnoreCase(client)) {
            empDao.delete(client, empId);
            return;
        }
        System.out.println("Access Denied");
    }
}

