package cold.face.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class Tasks {

    private String excuteOrder = "";

    public String getExcuteOrder() {
        return excuteOrder;
    }

    public void setExcuteOrder(String excuteOrder) {
        this.excuteOrder = excuteOrder;
    }

    @Async
    public void taskOne() throws Exception {
        System.out.println("task1 start:");
        Thread.sleep(8000L);
        System.out.println("task1 end:");
        System.out.println(excuteOrder);
    }

    @Async
    public void taskoTwo() throws Exception {
        System.out.println("task2 start:");
        Thread.sleep(5000L);
        System.out.println("task2 end:");
        System.out.println(excuteOrder);
    }

    @Async
    public void taskThree() throws Exception {
        System.out.println("task3 start:");
        Thread.sleep(3000L);
        System.out.println("task3 end:");
        System.out.println(excuteOrder);
    }

    @Async
    public void taskFour() throws Exception {
        System.out.println("task4 start:");
        Thread.sleep(7000L);
        System.out.println("task4 end:");
        System.out.println(excuteOrder);
    }

    @Async
    public Future<String> taskOneOne() throws Exception {
        System.out.println("task11 start:");
        Thread.sleep(8000L);
        System.out.println("task11 end:");
        excuteOrder += "11";
        System.out.println(excuteOrder);
        return new AsyncResult<>(excuteOrder);
    }

    @Async
    public Future<String> taskoTwoTwo() throws Exception {
        System.out.println("task22 start:");
        Thread.sleep(5000L);
        System.out.println("task22 end:");
        excuteOrder += "22";
        System.out.println(excuteOrder);
        return new AsyncResult<>(excuteOrder);
    }

    @Async
    public Future<String> taskThreeThree() throws Exception {
        System.out.println("task33 start:");
        Thread.sleep(3000L);
        System.out.println("task33 end:");
        excuteOrder += "33";
        System.out.println(excuteOrder);
        return new AsyncResult<>(excuteOrder);
    }

    @Async
    public Future<String> taskFourFour() throws Exception {
        System.out.println("task44 start:");
        Thread.sleep(7000L);
        System.out.println("task44 end:");
        excuteOrder += "44";
        System.out.println(excuteOrder);
        return new AsyncResult<>(excuteOrder);
    }
}
