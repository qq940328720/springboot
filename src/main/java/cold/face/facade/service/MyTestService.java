package cold.face.facade.service;

import cold.face.facade.dto.response.ResponseDTO;

public interface MyTestService {

    ResponseDTO getNoodlesTypeByJDBCTmp();

    ResponseDTO getNoodlesTypeByMybatis();

    ResponseDTO getNoodlesTypeBySqlProvider();

    ResponseDTO exceptionTest();
}
