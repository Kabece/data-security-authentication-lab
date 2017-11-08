package server.business;

import javax.security.auth.login.LoginException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SessionService extends Remote {

    String print(String filename, String printer) throws RemoteException, LoginException;
    String queue() throws RemoteException, LoginException;
    String topQueue(int job) throws RemoteException, LoginException;
    String start() throws RemoteException, LoginException;
    String stop() throws RemoteException, LoginException;
    String restart() throws RemoteException, LoginException;
    String status() throws RemoteException, LoginException;
    String readConfig(String parameter) throws RemoteException, LoginException;
    String setConfig(String parameter, String value) throws RemoteException, LoginException;
    void logout() throws RemoteException, LoginException;
    Integer getSessionId() throws RemoteException, LoginException;
}
