# Job Bank RMI System

A distributed job bank system that connects employers and job applicants using Java RMI (Remote Method Invocation). The system allows employers to post job offers and applicants to browse and apply to these offers.

## System Architecture

The system is distributed across three components:
- **Server**: Hosts the RMI registry and implements the job service
- **Employer Client**: Allows companies to post job offers and view applications
- **Applicant Client**: Allows job seekers to browse offers and submit applications

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Three separate machines (or virtual machines) with network connectivity
- Open port 1099 on the server machine for RMI communication

## Compilation

First, compile all Java files. From the project root directory:

```bash
# Compile common classes first
javac common/*.java

# Compile server classes
javac -cp . server/*.java

# Compile client classes
javac -cp . employer-client/EmployerClient.java
javac -cp . applicant-client/ApplicantClient.java
```

## Deployment

The system should be deployed on at least three separate machines:
1. One machine for the server
2. Multiple machines for employer clients
3. Multiple machines for applicant clients

### Directory Structure

Copy the compiled classes to each machine maintaining this structure:

```
job-bank-rmi/
├── common/
│   ├── JobService.class
│   ├── JobOffer.class
│   └── Application.class
├── server/
│   ├── Server.class
│   └── JobServiceImpl.class
├── employer-client/
│   └── EmployerClient.class
└── applicant-client/
    └── ApplicantClient.class
```

## Execution Guide

### 1. Starting the Server

On the server machine:

```bash
cd job-bank-rmi
java -Djava.rmi.server.hostname=SERVER_IP_ADDRESS server.Server
```

Replace `SERVER_IP_ADDRESS` with the actual IP address of your server machine.

You should see a message: `RMI Server active on port 1099`

### 2. Running the Employer Client

On the employer client machine(s):

```bash
cd job-bank-rmi
```

Edit the `employer-client/EmployerClient.java` file to update the server IP:
- Replace `IP_SERVIDOR` with the actual server IP address
- Recompile if necessary

Then run:

```bash
java employer-client.EmployerClient
```

Follow the console prompts to register job offers and view applications.

### 3. Running the Applicant Client

On the applicant client machine(s):

```bash
cd job-bank-rmi
```

Edit the `applicant-client/ApplicantClient.java` file to update the server IP:
- Replace `IP_SERVIDOR` with the actual server IP address
- Recompile if necessary

Then run:

```bash
java applicant-client.ApplicantClient
```

Follow the console prompts to view available jobs and apply to them.

## Troubleshooting

1. **Connection refused**: Ensure the server is running and that port 1099 is accessible.
2. **ClassNotFoundException**: Make sure all required classes are available on all machines.
3. **Serialization errors**: Verify that all serializable classes have matching versions on all nodes.
4. **Registry not found**: Double-check the server IP address in client files.

## System Workflow

1. The server maintains lists of job offers and applications
2. Employers can register new job offers and view applications for their positions
3. Applicants can browse all available positions and apply with their information
4. Data is persisted using serialization to files on the server

## License

This project is distributed under the MIT License.
