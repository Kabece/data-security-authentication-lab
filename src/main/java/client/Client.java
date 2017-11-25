package client;

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
        SingleRequestService singleRequestService = (SingleRequestService) Naming.lookup("rmi://localhost:5099/singleRequestPrinter");
        RemoteSessionManager remoteSessionManager = (RemoteSessionManager) Naming.lookup("rmi://localhost:5099/sessionPrinter");
        SessionService sessionService;
        // Alice
        try {
            // Single Request invocation
            System.out.println(singleRequestService.topQueue(1, "alice", "alice"));
            // Session invocation
            sessionService = remoteSessionManager.loginToService("alice", "alice");
            System.out.println(sessionService.topQueue(1));
        } catch (LoginException | AuthorizationException e) {
            System.out.println(e.getMessage());
        }

        // Bob
        try {
            // Single Request invocation
            System.out.println(singleRequestService.start("bob", "bob"));
            // Session invocation
            sessionService = remoteSessionManager.loginToService("bob", "bob");
            System.out.println(sessionService.start());
        } catch (LoginException | AuthorizationException e) {
            System.out.println(e.getMessage());
        }

        // Cecilie
        try {
            // Single Request invocation
            System.out.println(singleRequestService.readConfig("parameter", "cecilie", "cecilia"));
            // Session invocation

        } catch (LoginException | AuthorizationException e) {
            System.out.println(e.getMessage());
        }

        try {
            sessionService = remoteSessionManager.loginToService("cecilie", "cecilia");
            System.out.println(sessionService.readConfig("parameter"));
        } catch (LoginException | AuthorizationException e) {
            System.out.println(e.getMessage());
        }

        // David
        try {
            // Single Request invocation
            System.out.println(singleRequestService.print("filename", "printer", "david", "david"));
            // Session invocation
            sessionService = remoteSessionManager.loginToService("david", "david");
            System.out.println(sessionService.print("filename", "printer"));
        } catch (LoginException | AuthorizationException e) {
            System.out.println(e.getMessage());
        }

        // Erica
        try {
            // Single Request invocation
            System.out.println(singleRequestService.setConfig("parameter", "value", "erica", "erica"));
        } catch (LoginException | AuthorizationException e) {
            System.out.println(e.getMessage());
        }

        // Erica
        try {
            // Session invocation
            sessionService = remoteSessionManager.loginToService("erica", "erica");
            System.out.println(sessionService.setConfig("parameter", "value"));
        } catch (LoginException | AuthorizationException e) {
            System.out.println(e.getMessage());
        }

        // Fred
        try {
            // Single Request invocation
            System.out.println(singleRequestService.queue("fred", "fred"));
            // Session invocation
            sessionService = remoteSessionManager.loginToService("fred", "fred");
            System.out.println(sessionService.queue());
        } catch (LoginException | AuthorizationException e) {
            System.out.println(e.getMessage());
        }
    }
}
