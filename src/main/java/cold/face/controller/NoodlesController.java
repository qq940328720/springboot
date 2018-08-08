package cold.face.controller;

import cold.face.facade.dto.response.ResponseDTO;
import cold.face.facade.service.NoodlesService;
import cold.face.schedule.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Future;

@Controller
public class NoodlesController {

    @Autowired
    private NoodlesService noodlesService;

    @Autowired
    private Tasks tasks;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello spring boot!!!";
    }

    @RequestMapping("/hw")
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

    @RequestMapping("/cfgjsp")
    @ResponseBody
    public String cfgjsp() {
        return "hello spring boot!!!";
    }

    @RequestMapping("/NoodlesType/get/v1-1")
    @ResponseBody
    public ResponseDTO getNoodlesTypeByJDBCTmp() {
        return noodlesService.getNoodlesTypeByJDBCTmp();
    }

    @RequestMapping("/NoodlesType/get/v1-2")
    @ResponseBody
    public ResponseDTO getNoodlesTypeByMybatis() {
        return noodlesService.getNoodlesTypeByMybatis();
    }

    @RequestMapping("/NoodlesType/get/v1-3")
    @ResponseBody
    public ResponseDTO getNoodlesTypeBySqlProvider() {
        return noodlesService.getNoodlesTypeBySqlProvider();
    }

    @RequestMapping("/ExceptionTest")
    @ResponseBody
    public ResponseDTO exceptionTest() {
        return noodlesService.exceptionTest();
    }

    @RequestMapping("/TaskAndAsynTask")
    @ResponseBody
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
