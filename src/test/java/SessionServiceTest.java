import client.Client;
import org.junit.Test;
import server.business.SessionService;
import server.security.RemoteSessionManager;
import server.util.AuthorizationException;

import javax.security.auth.login.LoginException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;

public class SessionServiceTest {

    private String username = "bob";
    private String password = "bob";

    @Test
    public void authenticatedAndAuthorizedSession() throws RemoteException, NotBoundException, MalformedURLException, LoginException, AuthorizationException {
        RemoteSessionManager remoteSessionManager = (RemoteSessionManager) Naming.lookup("rmi://localhost:5099/sessionPrinter");
        SessionService sessionService = remoteSessionManager.loginToService(username, password);

        String expectedResponseStop = "Session || User: " + username + " invoked stop method";
        String expectedResponseStart = "Session || User: " + username + " invoked start method";
        String actualResponseStop = sessionService.stop();
        String actualResponseStart = sessionService.start();

        assertEquals(expectedResponseStop, actualResponseStop);
        assertEquals(expectedResponseStart, actualResponseStart);
    }

    @Test(expected = LoginException.class)
    public void notAuthenticatedSession() throws RemoteException, NotBoundException, MalformedURLException, LoginException, AuthorizationException {
        RemoteSessionManager remoteSessionManager = (RemoteSessionManager) Naming.lookup("rmi://localhost:5099/sessionPrinter");
        SessionService sessionService = remoteSessionManager.loginToService(username, "wrong password");
        sessionService.start();
    }

    @Test(expected = AuthorizationException.class)
    public void notAuthorizedSession() throws RemoteException, NotBoundException, MalformedURLException, LoginException, AuthorizationException {
        RemoteSessionManager remoteSessionManager = (RemoteSessionManager) Naming.lookup("rmi://localhost:5099/sessionPrinter");
        SessionService sessionService = remoteSessionManager.loginToService(username, password);
        sessionService.queue();
    }

    @Test(expected = NoSuchObjectException.class)
    public void loggedOutSession() throws RemoteException, NotBoundException, MalformedURLException, LoginException, AuthorizationException {
        RemoteSessionManager remoteSessionManager = (RemoteSessionManager) Naming.lookup("rmi://localhost:5099/sessionPrinter");
        SessionService sessionService = remoteSessionManager.loginToService(username, password);
        sessionService.start();
        sessionService.logout();
        sessionService.start();
    }

}
