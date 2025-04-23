import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class JobServiceImpl extends UnicastRemoteObject implements JobService {
    private List<JobOffer> jobOffers = new ArrayList<>();
    private List<Application> applications = new ArrayList<>();
    private static final String JOBS_FILE = "jobs.ser";
    private static final String APPS_FILE = "applications.ser";

    public JobServiceImpl() throws RemoteException {
        super();
        loadData();
    }

    // Guardar datos en archivos
    private synchronized void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(JOBS_FILE))) {
            oos.writeObject(jobOffers);
        } catch (IOException e) {
            System.err.println("Error guardando vacantes: " + e.getMessage());
        }
        // Guardar aplicaciones de manera similar
    }

    // Cargar datos al iniciar
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(JOBS_FILE))) {
            jobOffers = (List<JobOffer>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No se encontraron vacantes previas.");
        }
        // Cargar aplicaciones de manera similar
    }

    @Override
    public String registerJobOffer(JobOffer offer) throws RemoteException {
        jobOffers.add(offer);
        saveData();
        return "Oferta registrada exitosamente.";
    }

    @Override
    public List<JobOffer> consultJobOffers() throws RemoteException {
        return new ArrayList<>(jobOffers);
    }

    @Override
    public String applyToJob(String name, String email, String jobId) throws RemoteException {
        applications.add(new Application(jobId, name, email));
        saveData();
        return "Postulación exitosa a la vacante " + jobId;
    }

    @Override
    public List<Application> getApplications(String employerId) throws RemoteException {
        List<Application> result = new ArrayList<>();
        for (Application app : applications) {
            // Filtrar por empleador (requiere lógica adicional)
            result.add(app);
        }
        return result;
    }
}
