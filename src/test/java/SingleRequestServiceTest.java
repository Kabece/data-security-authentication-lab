import client.Client;
import org.junit.Test;
import server.business.SingleRequestService;

import javax.security.auth.login.LoginException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;

public class SingleRequestServiceTest {

    private String username = "admin";
    private String password = "admin";
    private Client client;
    private SingleRequestService singleRequestService;

    public SingleRequestServiceTest() throws RemoteException, NotBoundException, MalformedURLException {
        this.singleRequestService = (SingleRequestService) Naming.lookup(
                "rmi://localhost:5099/singleRequestPrinter");
        this.client = new Client();
    }

    @Test
    public void authenticatedInvocation() throws LoginException, RemoteException {
        String expectedResponse = "Single Request || User: " + username + " invoked queue method";
        String actualResponse = singleRequestService.queue(username, password);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test(expected = LoginException.class)
    public void notAuthenticatedInvocation() throws LoginException, RemoteException {
        singleRequestService.queue(username, "wrong password");
    }

}
