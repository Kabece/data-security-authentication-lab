package client;

import org.mindrot.jbcrypt.BCrypt;
import server.Service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    private String salt = "$2a$10$B9ZlYa6livQarz3RLt0KeO";

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, salt);
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        String username = "admin";
        String password = "admin";
        Client client = new Client();
        Service service = (Service) Naming.lookup("rmi://localhost:5099/printer");
        System.out.println(service.print("filename", "printer"));
        System.out.println(service.queue(username, client.hashPassword(password)));
        System.out.println(service.topQueue(1));
        System.out.println(service.start());
        System.out.println(service.stop());
        System.out.println(service.restart());
        System.out.println(service.status());
        System.out.println(service.readConfig("parameter"));
        System.out.println(service.setConfig("parameter", "value")) ;
    }
}
