package server.business;

import javax.security.auth.login.LoginException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SingleRequestService extends Remote {

    String print(String filename, String printer, String username, String password) throws RemoteException, LoginException;
    String queue(String username, String password) throws RemoteException, LoginException;
    String topQueue(int job, String username, String password) throws RemoteException, LoginException;
    String start(String username, String password) throws RemoteException, LoginException;
    String stop(String username, String password) throws RemoteException, LoginException;
    String restart(String username, String password) throws RemoteException, LoginException;
    String status(String username, String password) throws RemoteException, LoginException;
    String readConfig(String parameter, String username, String password) throws RemoteException, LoginException;
    String setConfig(String parameter, String value, String username, String password) throws RemoteException, LoginException;
}
