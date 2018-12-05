package cold.face.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class IndexListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("IndexListener context Initialized method");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("IndexListener context Destroyed method");
    }
}
