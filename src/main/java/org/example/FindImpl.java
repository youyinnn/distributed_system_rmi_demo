package org.example;

// The description of the file
//
//
//
//
//
// FindImpl.java
import java.io.*;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

public class FindImpl extends UnicastRemoteObject implements Find {
    //constructor
    private final ArrayList<String> list = new ArrayList<>();

    public FindImpl(String aFile) throws RemoteException {
        try {
            FileReader fr = new FileReader(aFile);
            BufferedReader br = new BufferedReader(fr);
            String s = null; //convert a line into a vector element
            while ((s = br.readLine()) != null)
                list.add(s); fr.close();
        } catch (Throwable e) {
            System.err.println("exception");
            System.exit(1);
        }
    } //end of constructor

    public String findLine(String keyword) {
        System.out.println("Invoked");
        if (keyword == null)
            return null;
        keyword = keyword.toLowerCase();
        int n = list.size();
        for (String line : list) { //For each line in the list
            if (line.toLowerCase().contains(keyword)) //Is "keyword" part of the line?
                return line; //Return the line containing "keyword"
        }
        return null;
    }

    public static void main(String args[]) {
        try {

            // can't set the security manager with eclipse
            // if (System.getSecurityManager() == null) {
            //     System.setSecurityManager(new SecurityManager());
            // }

            final URL book = FindImpl.class.getClassLoader().getResource("phonebook.txt");
            assert book != null;

            String aFile= book.getPath();
            FindImpl server = new FindImpl(aFile);

            // this fixed: java.rmi.ConnectException: Connection refused to host: 127.0.0.1;
            LocateRegistry.createRegistry(8099);

            Naming.rebind("rmi://127.0.0.1:8099/FindServer", server);
            System.out.println("FindServer ready...");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}