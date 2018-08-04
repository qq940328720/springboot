package cold.face.facade.dto.base;

public class BaseResponseDTO {
    private String message;
    private Boolean success;
    private String errorcode;
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponseDTO{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", errorcode='" + errorcode + '\'' +
                ", data=" + data +
                '}';
    }
}
