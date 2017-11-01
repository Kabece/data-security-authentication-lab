package client;

import server.Service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Service service = (Service) Naming.lookup("rmi://localhost:5099/printer");
        System.out.println(service.print("filename", "printer"));
        System.out.println(service.queue());
        System.out.println(service.topQueue(1));
        System.out.println(service.start());
        System.out.println(service.stop());
        System.out.println(service.restart());
        System.out.println(service.status());
        System.out.println(service.readConfig("parameter"));
        System.out.println(service.setConfig("parameter", "value"));
    }
}
