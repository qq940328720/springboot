package cold.face.schedule;

import cold.face.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

//    @Scheduled(fixedRate     = 5000)：上一次开始执行时间点之后5秒再执行
//    @Scheduled(fixedDelay     = 5000)：上一次执行完毕时间点之后5秒再执行
//    @Scheduled(initialDelay=1000,     fixedRate=5000)：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
//    @Scheduled(cron="*/5     * * * * *")：通过cron表达式定义规则
    @Scheduled(fixedRate = 5000)
    public void showCurrentTime() {
        log.info("current time : " + DateUtils.long2String(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
    }

}
