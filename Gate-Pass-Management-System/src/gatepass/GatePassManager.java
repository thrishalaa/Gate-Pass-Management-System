package gatepass;

import java.util.List;

class GatePassManager {
    private List<GatePassRequest> gatePassRequests;
    private Admin admin;

    public GatePassManager() {
        gatePassRequests = GatePassDAO.getPendingRequests();
    }

    public void setAdmin(Admin admin) {

        this.admin = admin;
    }

    public void addGatePassRequest(GatePassRequest request) {
        gatePassRequests.add(request);
    }

    public List<GatePassRequest> getGatePassRequests() {
        return gatePassRequests;
    }
}