package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements Service {
    protected ServiceImpl() throws RemoteException {
    }

    @Override
    public String echo(String input) throws RemoteException {
        return "From server: " + input;
    }
}
