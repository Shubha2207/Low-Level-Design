package designpatterns.structural;

/**
 * Used to help client application to easily interact with system
 */
public class FacadeDP {
    /**
     * Unified interface for set of interfaces of similar kind
     * Facade doesn't hide the interfaces to system, client can still directly access the system
     * <p>
     * e.g. we have two db mysql and oracle, and we want to generate report in html or pdf format
     * we can have facade for these two interfaces
     */
    public static void main(String[] args) {
        String dbName = "school";
        String tableName = "teacher";

        // without Facade
        System.out.println("\n ### Without Facade ### \n");
        MySqlDriver mySqlDriver = new MySqlDriver();
        mySqlDriver.connectToMySqlDB();
        mySqlDriver.generatePdfReport(dbName, tableName);

        // with Facade
        System.out.println("\n ### With Facade ### \n");
        DBFacade.generateReport("MySql", "PDF", dbName, tableName);

    }
}

class MySqlDriver {
    public void connectToMySqlDB() {
        System.out.println("Connected to My Sql DB");
    }

    public void generateHtmlReport(String dbName, String tableName) {
        System.out.println("HTML report: " + dbName + " - " + tableName);
    }

    public void generatePdfReport(String dbName, String tableName) {
        System.out.println("PDF report: " + dbName + " - " + tableName);
    }
}


class OracleDriver {
    public void connectToOracleDB() {
        System.out.println("Connected to Oracle DB");
    }

    public void generateHtmlReport(String dbName, String tableName) {
        System.out.println("HTML report: " + dbName + " - " + tableName);
    }

    public void generatePdfReport(String dbName, String tableName) {
        System.out.println("PDF report: " + dbName + " - " + tableName);
    }
}

class DBFacade {
    public static void generateReport(String dbType, String reportType, String dbName, String tableName) {
        switch (dbType) {
            case "MySql":
                MySqlDriver mySqlDriver;
                switch (reportType) {
                    case "HTML":
                        mySqlDriver = new MySqlDriver();
                        mySqlDriver.connectToMySqlDB();
                        mySqlDriver.generateHtmlReport(dbName, tableName);
                        break;
                    case "PDF":
                        mySqlDriver = new MySqlDriver();
                        mySqlDriver.connectToMySqlDB();
                        mySqlDriver.generatePdfReport(dbName, tableName);
                        break;
                    case "Oracle":
                        OracleDriver oracleDriver;
                        switch (reportType) {
                            case "HTML":
                                oracleDriver = new OracleDriver();
                                oracleDriver.connectToOracleDB();
                                oracleDriver.generateHtmlReport(dbName, tableName);
                                break;
                            case "PDF":
                                oracleDriver = new OracleDriver();
                                oracleDriver.connectToOracleDB();
                                oracleDriver.generatePdfReport(dbName, tableName);
                                break;

                        }
                }
        }
    }
}