package cold.face.springboot;

import cold.face.common.utils.MyZipUtils;
import cold.face.rabbitmq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplicationTests.class)
public class MqApplicationTest {

    private static Logger log = LoggerFactory.getLogger(MyZipUtils.class);
    
    @Autowired
    private Sender send;

    @Test
    public void test() {
        log.info("==========发送消息！");
        send.send();
    }
}
