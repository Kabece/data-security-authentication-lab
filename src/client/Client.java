package client;

import server.Service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Service service = (Service) Naming.lookup("rmi://localhost:5099/hello");
        System.out.println(":::" + service.echo("Hello Server!"));
    }
}
