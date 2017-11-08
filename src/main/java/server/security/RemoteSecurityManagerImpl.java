package server.security;

import server.business.Service;
import server.business.ServiceImpl;

import javax.security.auth.login.LoginException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteSecurityManagerImpl extends UnicastRemoteObject implements RemoteSecurityManager {

    public RemoteSecurityManagerImpl() throws RemoteException {
    }

    public Service loginToService(String username, String password) throws LoginException, RemoteException {
        Authenticator authenticator = new Authenticator();
        if (authenticator.authenticateUser(username, password)) {
            Service service = new ServiceImpl();
            Authenticator.sessionIds.add(service.getSessionId());
            return service;
        }
        throw new LoginException();
    }
}
