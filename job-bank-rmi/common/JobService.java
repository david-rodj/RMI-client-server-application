package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface JobService extends Remote {
    String registerJobOffer(JobOffer offer) throws RemoteException;
    List<JobOffer> consultJobOffers() throws RemoteException;
    String applyToJob(String applicantName, String email, String jobId) throws RemoteException;
    List<Application> getApplications(String employerId) throws RemoteException;
}
