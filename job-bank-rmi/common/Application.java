package common;

import java.io.Serializable;

public class Application implements Serializable {
    private String jobId;
    private String applicantName;
    private String email;

    // Constructor
    public Application(String jobId, String applicantName, String email) {
        this.jobId = jobId;
        this.applicantName = applicantName;
        this.email = email;
    }

    // Getters
    public String getJobId() { return jobId; }
    public String getApplicantName() { return applicantName; }
    public String getEmail() { return email; }
}
