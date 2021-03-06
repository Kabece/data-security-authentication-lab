package server.business;

import server.security.Authenticator;
import server.util.AuthorizationException;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.security.SecureRandom;

public class SessionServiceImpl extends UnicastRemoteObject implements SessionService, Unreferenced {

    private Integer sessionId = null;

    public SessionServiceImpl() throws RemoteException {
        SecureRandom secureRandom = new SecureRandom();
        this.sessionId = secureRandom.nextInt(16);
    }

    public void logout() throws RemoteException {
        unexportObject(this, true);
        Authenticator.removeSession(this.sessionId);
    }

    public void unreferenced() {
        try {
            unexportObject(this, true);
            Authenticator.removeSession(this.sessionId);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }

    public String print(String filename, String printer) throws RemoteException, AuthorizationException {
        if (Authenticator.authorizeRequest(sessionId, "print")) {
            return "Session || User: " + Authenticator.getUsernameForSessionId(sessionId) + " invoked print method with printer: "
                    + printer + " and filename: " + filename;
        } else {
            throw new AuthorizationException("Session authorization failed for sessionId: " + sessionId);
        }
    }

    public String queue() throws RemoteException, AuthorizationException {
        if (Authenticator.authorizeRequest(sessionId, "queue"))
            return "Session || User: " + Authenticator.getUsernameForSessionId(sessionId) + " invoked queue method";
        else
            throw new AuthorizationException("Session authorization failed for sessionId: " + sessionId);
    }

    public String topQueue(int job) throws RemoteException, AuthorizationException {
        if (Authenticator.authorizeRequest(sessionId, "topQueue"))
            return "Session || User: " + Authenticator.getUsernameForSessionId(sessionId) + " invoked topQueue method with job: " + job;
        else
            throw new AuthorizationException("Session authorization failed for sessionId: " + sessionId);
    }

    public String start() throws RemoteException, AuthorizationException {
        if (Authenticator.authorizeRequest(sessionId, "start"))
            return "Session || User: " + Authenticator.getUsernameForSessionId(sessionId) + " invoked start method";
        else
            throw new AuthorizationException("Session authorization failed for sessionId: " + sessionId);
    }

    public String stop() throws RemoteException, AuthorizationException {
        if (Authenticator.authorizeRequest(sessionId, "stop"))
            return "Session || User: " + Authenticator.getUsernameForSessionId(sessionId) + " invoked stop method";
        else
            throw new AuthorizationException("Session authorization failed for sessionId: " + sessionId);
    }

    public String restart() throws RemoteException, AuthorizationException {
        if (Authenticator.authorizeRequest(sessionId, "restart"))
            return "Session || User: " + Authenticator.getUsernameForSessionId(sessionId) + " invoked restart method";
        else
            throw new AuthorizationException("Session authorization failed for sessionId: " + sessionId);
    }

    public String status() throws RemoteException, AuthorizationException {
        if (Authenticator.authorizeRequest(sessionId, "status"))
            return "Session || User: " + Authenticator.getUsernameForSessionId(sessionId) + " invoked status method";
        else
            throw new AuthorizationException("Session authorization failed for sessionId: " + sessionId);
    }

    public String readConfig(String parameter) throws RemoteException, AuthorizationException {
        if (Authenticator.authorizeRequest(sessionId, "readConfig"))
            return "Session || User: " + Authenticator.getUsernameForSessionId(sessionId) + " invoked readConfig method " +
                    "with parameter: " + parameter;
        else
            throw new AuthorizationException("Session authorization failed for sessionId: " + sessionId);
    }

    public String setConfig(String parameter, String value) throws RemoteException, AuthorizationException {
        if (Authenticator.authorizeRequest(sessionId, "setConfig"))
            return "Session || User: " + Authenticator.getUsernameForSessionId(sessionId) + " invoked setConfig method " +
                    "with parameter: " + parameter + " and value: " + value;
        else
            throw new AuthorizationException("Session authorization failed for sessionId: " + sessionId);
    }

    public Integer getSessionId() throws RemoteException, AuthorizationException {
        return sessionId;
    }
}
