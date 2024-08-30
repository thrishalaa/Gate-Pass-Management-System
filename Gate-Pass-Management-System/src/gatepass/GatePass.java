package gatepass;

import java.util.Scanner;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GatePass {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GatePassManager manager = new GatePassManager();
    private static Admin admin;
    private static GatePassDAO gatePassDAO = new GatePassDAO();
    private static void setupAdmin() {
        System.out.print("Enter the admin name: ");
        String adminName = scanner.nextLine();
        System.out.print("Enter the admin password: ");
        String adminPassword = scanner.nextLine();

        if ("kmit@123".equals(adminPassword)) {
            admin = new Admin(adminName, adminPassword, gatePassDAO);
            manager.setAdmin(admin);
        } else {
            System.out.println("Incorrect admin password. Exiting the Gate Pass Management System.");
            System.exit(0);
        }
    }

    public static void displayOptions() {
        System.out.println("\n----- Gate Pass Management System -----");
        System.out.println("1. Submit Gate Pass Request (User)");
        System.out.println("2. Process Gate Pass Requests (Admin)");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void processUserGatePassRequest() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your rollno: ");
        String rollno = scanner.nextLine();

        System.out.print("Enter your year: ");
        String year = scanner.nextLine();

        System.out.print("Enter your class: ");
        String className = scanner.nextLine();

        System.out.print("Enter your section: ");
        String section = scanner.nextLine();

        System.out.print("Enter the reason for the gate pass: ");
        String reason = scanner.nextLine();

        try {
            GatePassRequest request = new GatePassRequest(name, rollno, year, className, section, reason);
            manager.addGatePassRequest(request);
            gatePassDAO.savePendingRequest(request);
            System.out.println("Your gate pass request has been submitted successfully!");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void processAdminActions() {
        setupAdmin();
        List<GatePassRequest> gatePassRequests = manager.getGatePassRequests();
        if (gatePassRequests.isEmpty()) {
            System.out.println("No pending gate pass requests.");
            return;
        }

        System.out.println("\n------ Pending Gate Pass Requests ------");
        int count = 1;
        for (GatePassRequest request : gatePassRequests) {
            System.out.println(count + ". " + request.getRollNo() + " - " + request.getReason());
            count++;
        }

        System.out.print("Enter the request you want to process: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 0) {
            return;
        } else if (choice >= 1 && choice <= gatePassRequests.size()) {
            GatePassRequest selectedRequest = gatePassRequests.get(choice - 1);
            processAdminAction(selectedRequest,selectedRequest.getRollNo());
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void processAdminAction(GatePassRequest request, String n) {
        System.out.println("Selected Gate Pass Request:");
        System.out.println("Name: " + request.getName());
        System.out.println("RollNo: " + request.getRollNo());
        System.out.println("Year: " + request.getYear());
        System.out.println("Class: " + request.getClassName());
        System.out.println("Section: " + request.getSection());
        System.out.println("Reason: " + request.getReason());
        System.out.println("Request Timestamp: " + request.getRequestTimestamp());

        System.out.print("Do you want to approve this request? (yes/no): ");
        String response = scanner.nextLine();

        if ("yes".equalsIgnoreCase(response)) {
            admin.acceptRequest(request);
            request.setAcceptTimestamp();
            manager.getGatePassRequests().remove(request);
            GatePassDAO.update_pending_requests(n);
            System.out.println("Request Accepted at: " + request.getAcceptTimestamp());
        } else if ("no".equalsIgnoreCase(response)) {
            admin.rejectRequest(request);
            request.setRejectTimestamp();
            manager.getGatePassRequests().remove(request);
            GatePassDAO.update_pending_requests(n);
            System.out.println("Request Rejected at: " + request.getRejectTimestamp());
        } else {
            System.out.println("Invalid response. Please enter 'yes' or 'no'.");
        }
    }
}

