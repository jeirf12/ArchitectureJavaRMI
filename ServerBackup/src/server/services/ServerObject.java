package server.services;

import common.utilities.Console;
import java.rmi.RemoteException;
import server.controllers.ControllerCopySecurity;
import server.repository.SongCopyRepository;
import server.utilities.RegisterServer;

/**
 *
 * @author jhonfer
 */
public class ServerObject {

    public static void main(String[] args) throws RemoteException {
        int numPortRMIRegistry = 0;
        String addressIpRMIRegistry = " ";

        addressIpRMIRegistry = "localhost";
        numPortRMIRegistry = 2022;
        SongCopyRepository objRepository = new SongCopyRepository();
        ControllerCopySecurity objRemote = new ControllerCopySecurity(objRepository);

        try {
            RegisterServer.runNS(numPortRMIRegistry);
            RegisterServer.registerObjectRemote(objRemote, addressIpRMIRegistry, numPortRMIRegistry, "objServicioGestionCopiaCanciones");
            Console.writeJumpLine("\nServidor de Respaldo iniciado correctamente", false);
        } catch (Exception e) {
            Console.writeJumpLine("No fue posible arrancar el ns o registrar el objeto remoto " + e.getMessage(), false);
        }
    }
}
