package cold.face.facade.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "响应实体基类")
public class BaseResponseDTO {
    @ApiModelProperty(value = "响应消息")
    private String message;
    @ApiModelProperty(value = "响应状态")
    private Boolean success;
    @ApiModelProperty(value = "执行状态")
    private Boolean excute;
    @ApiModelProperty(value = "错误码")
    private String errorcode;
    @ApiModelProperty(value = "响应数据")
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

    public Boolean getExcute() {
        return excute;
    }

    public void setExcute(Boolean excute) {
        this.excute = excute;
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
                ", excute=" + excute +
                ", errorcode='" + errorcode + '\'' +
                ", data=" + data +
                '}';
    }
}
