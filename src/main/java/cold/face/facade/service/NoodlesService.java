package cold.face.facade.service;

import cold.face.facade.dto.response.ResponseDTO;

public interface NoodlesService {
    ResponseDTO getNoodlesTypeByJDBCTmp();

    ResponseDTO getNoodlesTypeByMybatis();
}
