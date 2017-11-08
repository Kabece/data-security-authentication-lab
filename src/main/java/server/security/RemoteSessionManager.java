package server.security;

import server.business.SessionService;

import javax.security.auth.login.LoginException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSessionManager extends Remote {

    SessionService loginToService(String username, String password) throws LoginException, RemoteException;
}
