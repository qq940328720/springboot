package cold.face.controller;

import cold.face.facade.dto.response.ResponseDTO;
import cold.face.facade.service.MyTestService;
import cold.face.rabbitmq.Sender;
import cold.face.schedule.Tasks;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/test")
@Api(tags = "测试接口")
public class MyTestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MyTestService myTestService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Tasks tasks;

    @Autowired
    private Sender sender;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiOperation(value = "hello", notes = "welcom page")
    @ResponseBody
    public String hello() {
        return "hello spring boot!!!";
    }

    @RequestMapping(value = "/hw", method = RequestMethod.GET)
    @ApiOperation(value = "topage1", notes = "topage1")
    @ResponseBody
    public String topage1(ModelMap modelMap) {
        /*
            默认静态加载static下的页面。
            pom引入模板引擎thymeleaf后，动态覆盖静态，默认动态加载templates下的页面。
            动态模式下，要想加载静态页面，需要重定向：return "redirect:/page2";
        */
        modelMap.addAttribute("host", "http://wyait.blog.51cto.com123");
        return "page1";
//        modelMap.addAttribute("host", "http://wyait.blog.51cto.com");
//        return "redirect:/page2";
    }

    @ResponseBody
    @GetMapping(value = "/ylj")
    @ApiOperation(value = "page1", notes = "page1")
    public String cfgjsp() {
        return "hello spring boot!!!";
    }

    //    @ResponseBody
    @RequestMapping(value = "/get/v1-1/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "getNoodlesTypeByJDBCTmp", notes = "getNoodlesTypeByJDBCTmp")
    public ResponseDTO getNoodlesTypeByJDBCTmp(@PathVariable String id) {
        return myTestService.getNoodlesTypeByJDBCTmp();
    }

    @ResponseBody
    @RequestMapping(value = "/get/v1-2", method = RequestMethod.GET)
    @ApiOperation(value = "getNoodlesTypeByMybatis", notes = "getNoodlesTypeByMybatis")
    public ResponseDTO getNoodlesTypeByMybatis() {
        return myTestService.getNoodlesTypeByMybatis();
    }

    @ResponseBody
    @RequestMapping(value = "/get/v1-3", method = RequestMethod.GET)
    @ApiOperation(value = "getNoodlesTypeBySqlProvider", notes = "getNoodlesTypeBySqlProvider")
    public ResponseDTO getNoodlesTypeBySqlProvider() {
        return myTestService.getNoodlesTypeBySqlProvider();
    }

    @ResponseBody
    @RequestMapping(value = "/ExceptionTest", method = RequestMethod.GET)
    @ApiOperation(value = "ExceptionTest", notes = "ExceptionTest")
    public ResponseDTO exceptionTest() {
        return myTestService.exceptionTest();
    }

    @ResponseBody
    @RequestMapping(value = "/TaskAndAsynTask", method = RequestMethod.GET)
    @ApiOperation(value = "TaskAndAsynTask", notes = "TaskAndAsynTask")
    public ResponseDTO taskAndAsynTask() {

        ResponseDTO responseDTO = new ResponseDTO();
        try {
            tasks.taskOne();
            tasks.taskoTwo();
            tasks.taskThree();
            tasks.taskFour();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Future<String> task1 = tasks.taskOneOne();
            Future<String> task2 = tasks.taskoTwoTwo();
            Future<String> task3 = tasks.taskThreeThree();
            Future<String> task4 = tasks.taskFourFour();
            while (true) {
                if (task1.isDone() && task2.isDone() && task3.isDone() && task4.isDone()) {
                    //三个任务都调用完成，退出循环等待
                    responseDTO.setData(tasks.getExcuteOrder());
                    break;
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseDTO.setMessage(tasks.getExcuteOrder());
        return responseDTO;
    }

    @RequestMapping(value = "/wyait", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "getMsg", notes = "getMsg")
    public String getMsg(HttpServletResponse response) {
        logger.debug("===========debug信息>>>>");
        logger.info("===========info信息>>>>");
        logger.trace("I am trace log.");
        logger.debug("I am debug log.");
        logger.warn("I am warn log.");
        logger.error("I am error log.");
        // 手动异常
        System.out.println(1 / 0);
        // 会有中文乱码问题 TODO
//        return paramProperties.getWyaitName() +" 正在写"
//                + paramProperties.getWyaitTitle() +"!总结："
//                + paramProperties.getWyaitMessage();
        return "paramProperties.getWyaitName()" + " 正在写"
                + "paramProperties.getWyaitTitle()" + "!总结："
                + "paramProperties.getWyaitMessage()";
    }

    @RequestMapping(value = "/setRedis", method = RequestMethod.GET)
    @ResponseBody
    public String setRedis() {
        //保存字符串
        stringRedisTemplate.opsForValue().set("test", "111");
        return "ok";
    }

    @RequestMapping(value = "/testRabbitMq", method = RequestMethod.GET)
    @ResponseBody
    public void testRabbitMq() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                sender.send();
            }
        }
    }
}
