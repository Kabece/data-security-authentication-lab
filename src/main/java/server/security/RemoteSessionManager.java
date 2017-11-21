package server.security;

import server.business.SessionService;
import server.util.AuthorizationException;

import javax.security.auth.login.LoginException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSessionManager extends Remote {

    SessionService loginToService(String username, String password) throws LoginException, RemoteException, AuthorizationException;
}
