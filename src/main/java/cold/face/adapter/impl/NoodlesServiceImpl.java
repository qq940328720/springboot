package cold.face.adapter.impl;

import cold.face.dal.dao.NoodlesDao;
import cold.face.dal.dao.NoodlesTypeDao;
import cold.face.dal.model.NoodlesType;
import cold.face.facade.dto.info.NoodlesTypeInfoDTO;
import cold.face.facade.dto.response.ResponseDTO;
import cold.face.facade.service.NoodlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoodlesServiceImpl implements NoodlesService {

    @Autowired
    private NoodlesTypeDao noodlesTypeDao;

    @Autowired
    private NoodlesDao noodlesDao;

    @Override
    public ResponseDTO addNoodlesType(NoodlesTypeInfoDTO request) {
        ResponseDTO responseDTO = new ResponseDTO();
        String pCode = request.getParentTypeCode();
        if (null != pCode && "" != pCode) {
            NoodlesType type = noodlesTypeDao.getNoodlesTypeByTypeCode(pCode);
            if (type == null) {
                responseDTO.setMessage("上级分类不存在！");
                responseDTO.setSuccess(true);
                responseDTO.setExcute(false);
                return responseDTO;
            }
        } else {
            pCode = "00000000";
        }
        NoodlesType noodlesType = new NoodlesType();
        noodlesType.setName(request.getName());
        noodlesType.setRemark(request.getRemark());
        noodlesType.setParentTypeCode(pCode);
        noodlesType.setTypeCode(request.getTypeCode());
        noodlesTypeDao.insert(noodlesType);
        responseDTO.setSuccess(true);
        responseDTO.setExcute(true);
        return responseDTO;
    }
}