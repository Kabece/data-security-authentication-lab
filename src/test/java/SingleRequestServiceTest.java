import client.Client;
import org.junit.Test;
import server.business.SingleRequestService;
import server.util.AuthorizationException;

import javax.security.auth.login.LoginException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;

public class SingleRequestServiceTest {

    private String username = "bob";
    private String password = "bob";
    private Client client;
    private SingleRequestService singleRequestService;

    public SingleRequestServiceTest() throws RemoteException, NotBoundException, MalformedURLException {
        this.singleRequestService = (SingleRequestService) Naming.lookup(
                "rmi://localhost:5099/singleRequestPrinter");
        this.client = new Client();
    }

    @Test
    public void authenticatedAndAuthorizedInvocation() throws LoginException, RemoteException, AuthorizationException {
        String expectedResponse = "Single Request || User: " + username + " invoked start method";
        String actualResponse = singleRequestService.start(username, password);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test(expected = LoginException.class)
    public void notAuthenticatedInvocation() throws LoginException, RemoteException, AuthorizationException {
        singleRequestService.start(username, "wrong password");
    }

    @Test(expected = AuthorizationException.class)
    public void noAuthorizedInvocation() throws LoginException, RemoteException, AuthorizationException {
        singleRequestService.queue(username, password);
    }

}
