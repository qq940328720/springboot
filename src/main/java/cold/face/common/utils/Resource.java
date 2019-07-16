package cold.face.common.utils;

public class Resource {
    private String resourceId;// 主键（cityCode+ invoiceTypeId）
    private String cityCode;// 城市的区域编码
    private String invoiceTypeId;// 发票类型id
    private String invoiceTypeName;// 发票类型名称
    private String resourceIsOpen;// 开通的标识（0：未开通、1：开通）
    private String inputFields;// 必输入的字段（输入框）格式：发票代码#fpdm#12#string;发票号码#fphm#8#string
    private String extraFields;// 额外输入的字段(ip=127.0.0.1;isFirst=1;sfzh=)
    private String yzmUrl;// 获取验证码的地址
    private String cookieUrl;// 获取网页的Cookie的地址
    private String targetUrl;// 目标url(获取查询结果的url)
    private String yzmRefererUrl;// 获取验证码Referer的url
    private String targetRefererUrl;// 获取查询结果Referer的url
    private String proxyUrl;// 代理服务器的地址
    private String proxyPort;// 代理服务器的端口号
    private String querySpecification;// 查询说明（备选）
    private String officialUrl;// 税局的官方url(查询用的)

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getInvoiceTypeId() {
        return invoiceTypeId;
    }

    public void setInvoiceTypeId(String invoiceTypeId) {
        this.invoiceTypeId = invoiceTypeId;
    }

    public String getInvoiceTypeName() {
        return invoiceTypeName;
    }

    public void setInvoiceTypeName(String invoiceTypeName) {
        this.invoiceTypeName = invoiceTypeName;
    }

    public String getResourceIsOpen() {
        return resourceIsOpen;
    }

    public void setResourceIsOpen(String resourceIsOpen) {
        this.resourceIsOpen = resourceIsOpen;
    }

    public String getInputFields() {
        return inputFields;
    }

    public void setInputFields(String inputFields) {
        this.inputFields = inputFields;
    }

    public String getExtraFields() {
        return extraFields;
    }

    public void setExtraFields(String extraFields) {
        this.extraFields = extraFields;
    }

    public String getYzmUrl() {
        return yzmUrl;
    }

    public void setYzmUrl(String yzmUrl) {
        this.yzmUrl = yzmUrl;
    }

    public String getCookieUrl() {
        return cookieUrl;
    }

    public void setCookieUrl(String cookieUrl) {
        this.cookieUrl = cookieUrl;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getYzmRefererUrl() {
        return yzmRefererUrl;
    }

    public void setYzmRefererUrl(String yzmRefererUrl) {
        this.yzmRefererUrl = yzmRefererUrl;
    }

    public String getTargetRefererUrl() {
        return targetRefererUrl;
    }

    public void setTargetRefererUrl(String targetRefererUrl) {
        this.targetRefererUrl = targetRefererUrl;
    }

    public String getProxyUrl() {
        return proxyUrl;
    }

    public void setProxyUrl(String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getQuerySpecification() {
        return querySpecification;
    }

    public void setQuerySpecification(String querySpecification) {
        this.querySpecification = querySpecification;
    }

    public String getOfficialUrl() {
        return officialUrl;
    }

    public void setOfficialUrl(String officialUrl) {
        this.officialUrl = officialUrl;
    }

}
