package server.services;

import common.utilities.Console;
import java.rmi.RemoteException;
import server.controllers.ControllerManageAdministrator;
import server.controllers.ControllerManageSong;
import server.repository.SongRepository;
import server.utilities.RegisterServer;

/**
 *
 * @author jhonfer
 */
public class ServerObject {

    public static void main(String[] args) throws RemoteException {
        int numPortRMIRegistry = 2022;
        String addressIpRMIRegistry = "localhost";
        // addressIpRMIRegistry = Console.read("Cuál es la dirección ip donde se encuentra el rmiRegistry ?", addressIpRMIRegistry, false);
        // numPortRMIRegistry = Console.read("Cuál es el numero de puerto por el cual escucha el rmiRegistry ?", numPortRMIRegistry, false);
        
        SongRepository objRepository = new SongRepository();
        ControllerManageAdministrator objRemoteAdministrator = new ControllerManageAdministrator();
        ControllerManageSong objRemoteSong = new ControllerManageSong(objRepository, objRemoteAdministrator);
        objRemoteSong.getObjectRemoteServerBackup("localhost", 2022);
        
        try {
            RegisterServer.runNS(numPortRMIRegistry);
            RegisterServer.registerObjectRemote(objRemoteSong, addressIpRMIRegistry, numPortRMIRegistry, "objServicioGestionCanciones");
            RegisterServer.registerObjectRemote(objRemoteAdministrator, addressIpRMIRegistry, numPortRMIRegistry, "objServicioGestionAdministradores");
            Console.writeJumpLine("\nServidor de canciones iniciado correctamente", false);
        } catch (Exception e) {
            Console.writeJumpLine("No fue posible arrancar el ns o registrar el objeto remoto " + e.getMessage(), false);
        }
    }  
}
