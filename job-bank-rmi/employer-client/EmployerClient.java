import common.JobOffer;
import common.Application;
import common.JobService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class EmployerClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("IP_SERVIDOR", 1099);
            JobService service = (JobService) registry.lookup("JobService");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingrese ID de empresa: ");
            String empId = scanner.nextLine();
            
            // Registrar una oferta
            System.out.print("Título de la vacante: ");
            String title = scanner.nextLine();
            System.out.print("Salario ofrecido: ");
            double salary = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer

            JobOffer offer = new JobOffer("JOB_" + System.currentTimeMillis(), empId, title, salary);
            String response = service.registerJobOffer(offer);
            System.out.println(response);

             // Consultar postulaciones
            System.out.println("\nPostulaciones recibidas:\n");

            List<Application> lastApps = new ArrayList<>();

            while(true){
                Thread.sleep(2000);
                List<Application> newApps = service.getApplications(empId);
                
                if (!newApps.equals(lastApps)) {
                    newApps.forEach(app -> 
                        System.out.println(app.getApplicantName() + " - " + app.getEmail())
                    );
                    lastApps = newApps;
                    System.out.println();
                }
            }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
