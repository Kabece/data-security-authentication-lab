package server.business;

import server.util.AuthorizationException;

import javax.security.auth.login.LoginException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SingleRequestService extends Remote {

    String print(String filename, String printer, String username, String password) throws RemoteException, LoginException, AuthorizationException;
    String queue(String username, String password) throws RemoteException, LoginException, AuthorizationException;
    String topQueue(int job, String username, String password) throws RemoteException, LoginException, AuthorizationException;
    String start(String username, String password) throws RemoteException, LoginException, AuthorizationException;
    String stop(String username, String password) throws RemoteException, LoginException, AuthorizationException;
    String restart(String username, String password) throws RemoteException, LoginException, AuthorizationException;
    String status(String username, String password) throws RemoteException, LoginException, AuthorizationException;
    String readConfig(String parameter, String username, String password) throws RemoteException, LoginException, AuthorizationException;
    String setConfig(String parameter, String value, String username, String password) throws RemoteException, LoginException, AuthorizationException;
}
