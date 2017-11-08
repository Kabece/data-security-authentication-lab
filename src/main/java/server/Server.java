package server;

import server.business.ServiceImpl;
import server.security.RemoteSecurityManagerImpl;
import server.util.DBUtil;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;

public class Server {

    public static void main(String[] args) throws RemoteException {
        Connection conn = DBUtil.getConnection();

        Registry registry = LocateRegistry.createRegistry(5099);
        registry.rebind("printer", new RemoteSecurityManagerImpl());
    }
}
