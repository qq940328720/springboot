package cold.face.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AmqpTemplate rabbitMQTemplate;

    public void send() {
        String context = "hello :" + new Date();
        log.info("Sender : " + context);
        this.rabbitMQTemplate.convertAndSend("hello", context);
    }

}
