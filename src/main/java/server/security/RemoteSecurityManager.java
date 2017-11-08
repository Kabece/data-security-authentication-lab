package server.security;

import server.business.Service;

import javax.security.auth.login.LoginException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSecurityManager extends Remote {

    Service loginToService(String username, String password) throws LoginException, RemoteException;
}
