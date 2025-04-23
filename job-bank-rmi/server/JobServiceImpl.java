import common.JobOffer;
import common.Application;
import common.JobService;
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
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(APPS_FILE))) {
            oos.writeObject(applications);
        } catch (IOException e) {
            System.err.println("Error guardando aplicaciones: " + e.getMessage());
        }
    }
    
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(JOBS_FILE))) {
            jobOffers = (List<JobOffer>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No se encontraron vacantes previas.");
            jobOffers = new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(APPS_FILE))) {
            applications = (List<Application>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No se encontraron aplicaciones previas.");
            applications = new ArrayList<>();
        }
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
            // Buscar la oferta de trabajo asociada
            for (JobOffer offer : jobOffers) {
                if (offer.getJobId().equals(app.getJobId()) && 
                    offer.getEmployerId().equals(employerId)) {
                    result.add(app);
                    break;
                }
            }
        }
        return result;
    }
}
