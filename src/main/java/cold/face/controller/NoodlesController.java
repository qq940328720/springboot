package cold.face.controller;

import cold.face.facade.dto.response.ResponseDTO;
import cold.face.facade.service.NoodlesService;
import cold.face.schedule.Tasks;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Future;

@Controller
@RequestMapping("/coldface")
public class NoodlesController {

    @Autowired
    private NoodlesService noodlesService;

    @Autowired
    private Tasks tasks;

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
    @RequestMapping(value = "/ylj", method = RequestMethod.GET)
    @ApiOperation(value = "page1", notes = "page1")
    public String cfgjsp() {
        return "hello spring boot!!!";
    }

    @ResponseBody
    @RequestMapping(value = "/NoodlesType/get/v1-1/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "getNoodlesTypeByJDBCTmp", notes = "getNoodlesTypeByJDBCTmp")
    public ResponseDTO getNoodlesTypeByJDBCTmp(@PathVariable String id) {
        return noodlesService.getNoodlesTypeByJDBCTmp();
    }

    @ResponseBody
    @RequestMapping(value = "/NoodlesType/get/v1-2", method = RequestMethod.GET)
    @ApiOperation(value = "getNoodlesTypeByMybatis", notes = "getNoodlesTypeByMybatis")
    public ResponseDTO getNoodlesTypeByMybatis() {
        return noodlesService.getNoodlesTypeByMybatis();
    }

    @ResponseBody
    @RequestMapping(value = "/NoodlesType/get/v1-3", method = RequestMethod.GET)
    @ApiOperation(value = "getNoodlesTypeBySqlProvider", notes = "getNoodlesTypeBySqlProvider")
    public ResponseDTO getNoodlesTypeBySqlProvider() {
        return noodlesService.getNoodlesTypeBySqlProvider();
    }

    @ResponseBody
    @RequestMapping(value = "/ExceptionTest", method = RequestMethod.GET)
    @ApiOperation(value = "ExceptionTest", notes = "ExceptionTest")
    public ResponseDTO exceptionTest() {
        return noodlesService.exceptionTest();
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
}
