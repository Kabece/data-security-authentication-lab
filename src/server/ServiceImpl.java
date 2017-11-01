package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements Service {
    protected ServiceImpl() throws RemoteException {
    }


    @Override
    public String print(String filename, String printer) throws RemoteException {
        return "print method invoked with filename: " + filename + " and printer: " + printer;
    }

    @Override
    public String queue() throws RemoteException {
        return "queue method invoked";
    }

    @Override
    public String topQueue(int job) throws RemoteException {
        return "topQueue method invoked with job: " + job;
    }

    @Override
    public String start() throws RemoteException {
        return "start method invoked";
    }

    @Override
    public String stop() throws RemoteException {
        return "stop method invoked";
    }

    @Override
    public String restart() throws RemoteException {
        return "restart method invoked";
    }

    @Override
    public String status() throws RemoteException {
        return "status method invoked";
    }

    @Override
    public String readConfig(String parameter) throws RemoteException {
        return "readConfig method invoked with parameter: " + parameter;
    }

    @Override
    public String setConfig(String parameter, String value) throws RemoteException {
        return "setConfig method invoked with parameter: " + parameter + " and value: " + value;
    }
}
