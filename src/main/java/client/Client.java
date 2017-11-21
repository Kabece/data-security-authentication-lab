package client;

import org.mindrot.jbcrypt.BCrypt;
import server.business.SessionService;
import server.business.SingleRequestService;
import server.security.RemoteSessionManager;
import server.util.AuthorizationException;

import javax.security.auth.login.LoginException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        String username = "alice";
        String password = "alice";
        Client client = new Client();
        SingleRequestService singleRequestService = (SingleRequestService) Naming.lookup("rmi://localhost:5099/singleRequestPrinter");
        RemoteSessionManager remoteSessionManager = (RemoteSessionManager) Naming.lookup("rmi://localhost:5099/sessionPrinter");
        SessionService sessionService = null;
        try {
            // Single Request invocations
            System.out.println(singleRequestService.print("filename", "printer", username, password));
            System.out.println(singleRequestService.queue(username, password));
            System.out.println(singleRequestService.topQueue(1, username, password));
            System.out.println(singleRequestService.start(username, password));
            System.out.println(singleRequestService.stop(username, password));
            System.out.println(singleRequestService.restart(username, password));
            System.out.println(singleRequestService.status(username, password));
            System.out.println(singleRequestService.readConfig("parameter", username, password));
            System.out.println(singleRequestService.setConfig("parameter", "value", username, password));

            // Session invocations
            sessionService = remoteSessionManager.loginToService(username, password);
            System.out.println(sessionService.print("filename", "printer"));
            System.out.println(sessionService.queue());
            System.out.println(sessionService.topQueue(1));
            System.out.println(sessionService.start());
            System.out.println(sessionService.stop());
            System.out.println(sessionService.restart());
            System.out.println(sessionService.status());
            System.out.println(sessionService.readConfig("parameter"));
            System.out.println(sessionService.setConfig("parameter", "value"));

        } catch (LoginException | AuthorizationException e) {
            System.out.println(e.getMessage());
        }
    }
}
