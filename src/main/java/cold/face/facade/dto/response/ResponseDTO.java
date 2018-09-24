package cold.face.facade.dto.response;

import cold.face.facade.dto.base.BaseResponseDTO;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "响应实体")
public class ResponseDTO extends BaseResponseDTO {
    @Override
    public String toString() {
        return "ResponseDTO{}";
    }
}
