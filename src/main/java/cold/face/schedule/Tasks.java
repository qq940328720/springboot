package cold.face.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class Tasks {

    private static Logger log = LoggerFactory.getLogger(Tasks.class);

    private String excuteOrder = "";

    public String getExcuteOrder() {
        return excuteOrder;
    }

    public void setExcuteOrder(String excuteOrder) {
        this.excuteOrder = excuteOrder;
    }

    @Async
    public void taskOne() throws Exception {
        log.info("task1 start:");
        Thread.sleep(8000L);
        log.info("task1 end:");
        log.info(excuteOrder);
    }

    @Async
    public void taskoTwo() throws Exception {
        log.info("task2 start:");
        Thread.sleep(5000L);
        log.info("task2 end:");
        log.info(excuteOrder);
    }

    @Async
    public void taskThree() throws Exception {
        log.info("task3 start:");
        Thread.sleep(3000L);
        log.info("task3 end:");
        log.info(excuteOrder);
    }

    @Async
    public void taskFour() throws Exception {
        log.info("task4 start:");
        Thread.sleep(7000L);
        log.info("task4 end:");
        log.info(excuteOrder);
    }

    @Async
    public Future<String> taskOneOne() throws Exception {
        log.info("task11 start:");
        Thread.sleep(8000L);
        log.info("task11 end:");
        excuteOrder += "11";
        log.info(excuteOrder);
        return new AsyncResult<>(excuteOrder);
    }

    @Async
    public Future<String> taskoTwoTwo() throws Exception {
        log.info("task22 start:");
        Thread.sleep(5000L);
        log.info("task22 end:");
        excuteOrder += "22";
        log.info(excuteOrder);
        return new AsyncResult<>(excuteOrder);
    }

    @Async
    public Future<String> taskThreeThree() throws Exception {
        log.info("task33 start:");
        Thread.sleep(3000L);
        log.info("task33 end:");
        excuteOrder += "33";
        log.info(excuteOrder);
        return new AsyncResult<>(excuteOrder);
    }

    @Async
    public Future<String> taskFourFour() throws Exception {
        log.info("task44 start:");
        Thread.sleep(7000L);
        log.info("task44 end:");
        excuteOrder += "44";
        log.info(excuteOrder);
        return new AsyncResult<>(excuteOrder);
    }

    public static void main(String[] args) throws Exception {
        try {
            throw new Exception("");
        } catch (Exception e) {
            throw e;
        }
    }
}
