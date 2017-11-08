import client.Client;
import org.junit.Test;
import server.business.SessionService;
import server.security.RemoteSessionManager;

import javax.security.auth.login.LoginException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;

public class SessionServiceTest {

    private String username = "admin";
    private String password = "admin";
    private Client client;

    public SessionServiceTest() throws RemoteException, NotBoundException, MalformedURLException {
        this.client = new Client();
    }

    @Test
    public void authenticatedSession() throws RemoteException, NotBoundException, MalformedURLException, LoginException {
        RemoteSessionManager remoteSessionManager = (RemoteSessionManager) Naming.lookup("rmi://localhost:5099/sessionPrinter");
        SessionService sessionService = remoteSessionManager.loginToService(username, client.hashPassword(password));

        String expectedResponseQueue = "Session || User: " + username + " invoked queue method";
        String expectedResponseStart = "Session || User: " + username + " invoked start method";
        String actualResponseQueue = sessionService.queue();
        String actualResponseStart = sessionService.start();

        assertEquals(expectedResponseQueue, actualResponseQueue);
        assertEquals(expectedResponseStart, actualResponseStart);
    }

    @Test(expected = LoginException.class)
    public void notAuthenticatedSession() throws RemoteException, NotBoundException, MalformedURLException, LoginException {
        RemoteSessionManager remoteSessionManager = (RemoteSessionManager) Naming.lookup("rmi://localhost:5099/sessionPrinter");
        SessionService sessionService = remoteSessionManager.loginToService(username, client.hashPassword("wrong password"));
    }

    @Test(expected = NoSuchObjectException.class)
    public void loggedOutSession() throws RemoteException, NotBoundException, MalformedURLException, LoginException {
        RemoteSessionManager remoteSessionManager = (RemoteSessionManager) Naming.lookup("rmi://localhost:5099/sessionPrinter");
        SessionService sessionService = remoteSessionManager.loginToService(username, client.hashPassword(password));
        sessionService.start();
        sessionService.logout();
        sessionService.start();
    }

}
