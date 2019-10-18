package cold.face.others;

import java.util.List;

public class FederationService {
    long serviceId;
    String serviceType;
    long adminUserId;
    List<FederationService> subServiceList;

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(long adminUserId) {
        this.adminUserId = adminUserId;
    }

    public List<FederationService> getSubServiceList() {
        return subServiceList;
    }

    public void setSubServiceList(List<FederationService> subServiceList) {
        this.subServiceList = subServiceList;
    }
}
