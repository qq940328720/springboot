package cold.face.controller;

import cold.face.facade.dto.info.NoodlesTypeInfoDTO;
import cold.face.facade.dto.response.ResponseDTO;
import cold.face.facade.service.NoodlesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(description = "面条相关操作")
@RequestMapping("noodles")
public class NoodlesController {

    @Autowired
    private NoodlesService noodlesService;

    @ResponseBody
    @PostMapping("add")
    @ApiOperation(value = "addNoodlesType", notes = "添加面条")
    public ResponseDTO addNoodlesType(@RequestBody NoodlesTypeInfoDTO request) {
        return noodlesService.addNoodlesType(request);
    }

}
