package cold.face.controller;

import cold.face.common.enums.MyGoodsEnum;
import cold.face.facade.dto.info.MyOrderInfo;
import cold.face.facade.dto.response.ResponseDTO;
import cold.face.facade.service.MyAQSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

/**
 * @author lijing_yan
 * @data 2021-04-27 16:18
 */
@RestController
@RequestMapping("myaqs")
@Api(tags = "AQS测试")
public class MyAQSController {

    //    @Autowired
//    AmqpTemplate amqpTemplate;
    @Autowired
    MyAQSService myAQSService;

    @ResponseBody
    @RequestMapping(value = "buy", method = RequestMethod.GET)
    @ApiOperation(value = "秒杀", notes = "购买限量商品")
    public ResponseDTO buy() {
        MyOrderInfo info = new MyOrderInfo();
        info.setUserId(new Random().nextInt(20));
        info.setGoodsId(MyGoodsEnum.GOODS_COMPUTER.getValue());
        info.setCreateTime(new Date().getTime());
        info.setNumber(3);
        myAQSService.doWith(info);
        return new ResponseDTO();
    }

    @ResponseBody
    @RequestMapping(value = "fresh", method = RequestMethod.GET)
    @ApiOperation(value = "刷新", notes = "刷新商品库存")
    public ResponseDTO fresh() {
        myAQSService.fresh();
        return new ResponseDTO();
    }
}
