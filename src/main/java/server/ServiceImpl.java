package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements Service {

    private Authenticator authenticator;

    protected ServiceImpl() throws RemoteException {
        this.authenticator = new Authenticator();
    }


    public String print(String filename, String printer) throws RemoteException {
        return "print method invoked with filename: " + filename + " and printer: " + printer;
    }

    public String queue(String username, String password) throws RemoteException {
        if (authenticator.authenticateUser(username, password))
            return "queue method invoked";
        else
            return "User not authenticated!";
    }

    public String topQueue(int job) throws RemoteException {
        return "topQueue method invoked with job: " + job;
    }

    public String start() throws RemoteException {
        return "start method invoked";
    }

    public String stop() throws RemoteException {
        return "stop method invoked";
    }

    public String restart() throws RemoteException {
        return "restart method invoked";
    }

    public String status() throws RemoteException {
        return "status method invoked";
    }

    public String readConfig(String parameter) throws RemoteException {
        return "readConfig method invoked with parameter: " + parameter;
    }

    public String setConfig(String parameter, String value) throws RemoteException {
        return "setConfig method invoked with parameter: " + parameter + " and value: " + value;
    }
}
