package org.example;

// The description of the file
//
//
//

// FindClient.java

import org.junit.jupiter.api.Test;
import java.rmi.Naming;

public class FindClient {

    @Test
    void testFindLine() {
        try {
            // can't set the security manager with eclipse
            // if (System.getSecurityManager() == null) {
            //     System.setSecurityManager(new SecurityManager());
            // }

            //Assuming the server is running on orion
            String name = "rmi://" + "127.0.0.1:8099/FindServer";

            // RMI lookup
            Find ref = (Find)Naming.lookup(name);

            // invoking
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
