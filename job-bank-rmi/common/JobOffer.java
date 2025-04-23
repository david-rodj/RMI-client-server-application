package common;

import java.io.Serializable;

public class JobOffer implements Serializable {
    private String jobId;
    private String employerId;
    private String title;
    private double salary;

    // Constructor
    public JobOffer(String jobId, String employerId, String title, double salary) {
        this.jobId = jobId;
        this.employerId = employerId;
        this.title = title;
        this.salary = salary;
    }

    // Getters (necesarios para RMI)
    public String getJobId() { return jobId; }
    public String getEmployerId() { return employerId; }
    public String getTitle() { return title; }
    public double getSalary() { return salary; }
}
