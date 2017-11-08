package server.security;

import server.business.SessionService;
import server.business.SessionServiceImpl;

import javax.security.auth.login.LoginException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteSessionManagerImpl extends UnicastRemoteObject implements RemoteSessionManager {

    public RemoteSessionManagerImpl() throws RemoteException {
    }

    public SessionService loginToService(String username, String password) throws LoginException, RemoteException {
        Authenticator authenticator = new Authenticator();
        if (authenticator.authenticateUser(username, password)) {
            SessionService sessionService = new SessionServiceImpl();
            Authenticator.sessionIds.put(sessionService.getSessionId(), username);
            return sessionService;
        }
        throw new LoginException("User authentication failed.");
    }
}
