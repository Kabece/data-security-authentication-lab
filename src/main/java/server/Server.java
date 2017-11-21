package server;

import server.business.SingleRequestServiceImpl;
import server.security.Authenticator;
import server.security.RemoteSessionManagerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(5099);
        registry.rebind("sessionPrinter", new RemoteSessionManagerImpl());
        registry.rebind("singleRequestPrinter", new SingleRequestServiceImpl());
    }
}
