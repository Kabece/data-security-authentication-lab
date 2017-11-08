package server.business;

import server.security.Authenticator;

import javax.security.auth.login.LoginException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SingleRequestServiceImpl extends UnicastRemoteObject implements SingleRequestService {

    private Authenticator authenticator;

    public SingleRequestServiceImpl() throws RemoteException {
        this.authenticator = new Authenticator();
    }

    public String print(String filename, String printer, String username, String password) throws RemoteException, LoginException {
        if (this.authenticator.authenticateUser(username, password)) {
            return "Single Request || User: " + username + " invoked print method with printer: " + printer
                    + " and file: " + filename;
        } else {
            throw new LoginException("User authentication failed.");
        }
    }

    public String queue(String username, String password) throws RemoteException, LoginException {
        if (this.authenticator.authenticateUser(username, password)) {
            return "Single Request || User: " + username + " invoked queue method";
        } else {
            throw new LoginException("User authentication failed.");
        }
    }

    public String topQueue(int job, String username, String password) throws RemoteException, LoginException {
        if (this.authenticator.authenticateUser(username, password)) {
            return "Single Request || User: " + username + " invoked topQueue method with job: " + job;
        } else {
            throw new LoginException("User authentication failed.");
        }
    }

    public String start(String username, String password) throws RemoteException, LoginException {
        if (this.authenticator.authenticateUser(username, password)) {
            return "Single Request || User: " + username + " invoked start method";
        } else {
            throw new LoginException("User authentication failed.");
        }
    }

    public String stop(String username, String password) throws RemoteException, LoginException {
        if (this.authenticator.authenticateUser(username, password)) {
            return "Single Request || User: " + username + " invoked stop method";
        } else {
            throw new LoginException("User authentication failed.");
        }
    }

    public String restart(String username, String password) throws RemoteException, LoginException {
        if (this.authenticator.authenticateUser(username, password)) {
            return "Single Request || User: " + username + " invoked restart method";
        } else {
            throw new LoginException("User authentication failed.");
        }
    }

    public String status(String username, String password) throws RemoteException, LoginException {
        if (this.authenticator.authenticateUser(username, password)) {
            return "Single Request || User: " + username + " invoked status method";
        } else {
            throw new LoginException("User authentication failed.");
        }
    }

    public String readConfig(String parameter, String username, String password) throws RemoteException, LoginException {
        if (this.authenticator.authenticateUser(username, password)) {
            return "Single Request || User: " + username + " invoked readConfig method with parameter: " + parameter;
        } else {
            throw new LoginException("User authentication failed.");
        }
    }

    public String setConfig(String parameter, String value, String username, String password) throws RemoteException, LoginException {
        if (this.authenticator.authenticateUser(username, password)) {
            return "Single Request || User: " + username + " invoked setConfig method with parameter: " + parameter + " and value: " + value;
        } else {
            throw new LoginException("User authentication failed.");
        }
    }
}
