/**
 * @CopyRight Jay
 */
package com.sysintelli.ims;

import java.util.logging.Logger;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * @author Jay
 */
@SpringBootApplication
@EnableTransactionManagement
public class SbicApplication {

    private static final Logger logger = Logger.getLogger(SbicApplication.class.getName());

    public static void main(String[] args) {
        logger.info("SBIC Application run successfully.");
        SpringApplication.run(SbicApplication.class, args);
    }

    /**
     * @Method for remove un-wanted error
     * @return
     */
//    @Bean
//    public ErrorPageFilter errorPageFilter() {
//        return new ErrorPageFilter();
//    }
//
//    @Bean
//    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(filter);
//        filterRegistrationBean.setEnabled(false);
//        return filterRegistrationBean;
//    }
}

@Configuration
@EnableSpringHttpSession
class HttpSessionConfig {

    @Bean
    SessionRepository<ExpiringSession> inmemorySessionRepository() {
        return new MapSessionRepository();
    }

    @Bean
    HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }
}
