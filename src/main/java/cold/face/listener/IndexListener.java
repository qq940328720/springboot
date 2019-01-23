package cold.face.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class IndexListener implements ServletContextListener {

    private static Logger log = LoggerFactory.getLogger(IndexListener.class);

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        log.info("IndexListener context Initialized method");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        log.info("IndexListener context Destroyed method");
    }
}
