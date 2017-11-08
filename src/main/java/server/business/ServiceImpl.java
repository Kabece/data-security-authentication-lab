package server.business;

import server.security.Authenticator;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.security.SecureRandom;

public class ServiceImpl extends UnicastRemoteObject implements Service, Unreferenced {

    private Integer sessionId = null;

    public ServiceImpl() throws RemoteException {
        SecureRandom secureRandom = new SecureRandom();
        this.sessionId = secureRandom.nextInt(16);
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

    public String print(String filename, String printer) throws RemoteException {
        return "print method invoked with filename: " + filename + " and printer: " + printer;
    }

    public String queue(int sessionId) throws RemoteException {
        if (Authenticator.authorizeRequest(sessionId))
            return "queue method invoked";
        else
            return "Session not authorized";
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

    public Integer getSessionId() throws RemoteException {
        return sessionId;
    }
}
