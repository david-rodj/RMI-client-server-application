import common.JobService;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            JobService service = new JobServiceImpl();
            registry.rebind("JobService", service);
            System.out.println("Servidor RMI activo en puerto 1099");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
