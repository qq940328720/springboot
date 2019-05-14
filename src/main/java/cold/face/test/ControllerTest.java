package cold.face.test;

import cold.face.boot.NoodlesApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = NoodlesApplication.class)
@WebAppConfiguration
public class ControllerTest {
    
    @Test
    public void testGetChangePayCustomer() {
        int i = 0;
    }

}
