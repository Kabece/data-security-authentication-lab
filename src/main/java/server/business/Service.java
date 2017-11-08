package server.business;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {

    String print(String filename, String printer) throws RemoteException;
    String queue(int sessionId) throws RemoteException;
    String topQueue(int job) throws RemoteException;
    String start() throws RemoteException;
    String stop() throws RemoteException;
    String restart() throws RemoteException;
    String status() throws RemoteException;
    String readConfig(String parameter) throws RemoteException;
    String setConfig(String parameter, String value) throws RemoteException;
    void logout() throws RemoteException;
    Integer getSessionId() throws RemoteException;
}
