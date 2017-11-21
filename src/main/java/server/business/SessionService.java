package server.business;

import server.util.AuthorizationException;

import javax.security.auth.login.LoginException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SessionService extends Remote {

    String print(String filename, String printer) throws RemoteException, AuthorizationException;
    String queue() throws RemoteException, AuthorizationException;
    String topQueue(int job) throws RemoteException, AuthorizationException;
    String start() throws RemoteException, AuthorizationException;
    String stop() throws RemoteException, AuthorizationException;
    String restart() throws RemoteException, AuthorizationException;
    String status() throws RemoteException, AuthorizationException;
    String readConfig(String parameter) throws RemoteException, AuthorizationException;
    String setConfig(String parameter, String value) throws RemoteException, AuthorizationException;
    void logout() throws RemoteException;
    Integer getSessionId() throws RemoteException, AuthorizationException;
}
