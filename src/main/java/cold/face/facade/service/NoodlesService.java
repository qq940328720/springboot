package cold.face.facade.service;

import cold.face.facade.dto.info.NoodlesTypeInfoDTO;
import cold.face.facade.dto.response.ResponseDTO;

public interface NoodlesService {
    ResponseDTO addNoodlesType(NoodlesTypeInfoDTO request);
}
