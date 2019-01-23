package cold.face.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// 监听“hello”队列
@Component
@RabbitListener(queues = "hello")
public class Receiver {

    private static Logger log = LoggerFactory.getLogger(Receiver.class);

    // handler注解来指定对消息的处理方法
    @RabbitHandler
    public void process(String hello) {
        log.info("Receiver:" + hello);
    }
}
