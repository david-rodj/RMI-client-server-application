import common.JobOffer;
import common.Application;
import common.JobService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ApplicantClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("IP_SERVIDOR", 1099);
            JobService service = (JobService) registry.lookup("JobService");
            Scanner scanner = new Scanner(System.in);

            System.out.println("Vacantes disponibles:");
            service.consultJobOffers().forEach(offer ->
                System.out.println(offer.getTitle() + " - $" + offer.getSalary())
            );

            System.out.print("\nID de la vacante a aplicar: ");
            String jobId = scanner.nextLine();
            System.out.print("Nombre completo: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();

            String response = service.applyToJob(name, email, jobId);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
