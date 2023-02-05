package org.example;

// The description of the file
//
//
//

// FindClient.java
import java.rmi.*;
import java.rmi.server.*;
public class FindClient {

    public static void main(String args[]) {
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            //Assuming the server is running on orion
            String name = "rmi://" + "127.0.0.1:8099/FindServer";

            Find ref = (Find)Naming.lookup(name);
            String results = ref.findLine("BCD");
            if (results == null)
                System.err.println("** not found **");
            else
                System.out.println(results);
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
