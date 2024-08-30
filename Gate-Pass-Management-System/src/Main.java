import gatepass.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        GatePassDAO.createTables();
        Scanner scanner= new Scanner(System.in);

        while (true) {
            GatePass.displayOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    GatePass.processUserGatePassRequest();
                    break;
                case 2:
                    GatePass.processAdminActions();
                    break;
                case 3:
                    System.out.println("Exiting the Gate Pass Management System.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}