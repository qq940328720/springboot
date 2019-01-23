package cold.face.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActionFilterConfig {
    @Bean
    @Order(1)//过滤器执行顺序（优先：小到大）
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        ActionFilter actionFilter = new ActionFilter();
        registrationBean.setFilter(actionFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/test/**");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
