package gatepass;

class Admin {
    private String adminName;
    private String adminPassword;
    private GatePassDAO gatePassDAO;

    public Admin(String adminName, String adminPassword, GatePassDAO gatePassDAO) {
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.gatePassDAO = gatePassDAO;
    }

    public String getAdminName() {

        return adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void acceptRequest(GatePassRequest request) {
        gatePassDAO.saveAcceptedRequest(request);
        System.out.println("Request Accepted!");
    }

    public void rejectRequest(GatePassRequest request) {
        gatePassDAO.saveRejectedRequest(request);
        System.out.println("Request Rejected!");
    }
}