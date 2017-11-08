package server.business;

import server.security.Authenticator;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;

public class ServiceImpl extends UnicastRemoteObject implements Service, Unreferenced {

    private Authenticator authenticator;

    public ServiceImpl() throws RemoteException {
        this.authenticator = new Authenticator();
    }

    public String print(String filename, String printer) throws RemoteException {
        return "print method invoked with filename: " + filename + " and printer: " + printer;
    }

    public String queue() throws RemoteException {
        return "queue method invoked";
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

    public void logout() throws RemoteException {
        unexportObject(this, true);
    }

    public void unreferenced() {
        try {
            unexportObject(this, true);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }
}
