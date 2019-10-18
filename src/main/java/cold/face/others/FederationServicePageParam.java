package cold.face.others;

public class FederationServicePageParam extends Param {

    private String serviceType;//服务类型Id,多个用“，”分割

    private long federationPartnerId;//联邦伙伴ID

    private int isValid;//是否查询有效期内的服务

    private int isEnable;//是否查询已启用的服务

    private int pageSize;//页面大小

    private int pageNum;//页码

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public long getFederationPartnerId() {
        return federationPartnerId;
    }

    public void setFederationPartnerId(long federationPartnerId) {
        this.federationPartnerId = federationPartnerId;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }
}
