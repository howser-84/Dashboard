package org.dashboard.main;


import org.dashboard.main.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public TaskService getTaskService(){
        return new DBTaskService();
        //return new DummyTaskService();
    }

    @Bean
    public LoginService getLoginService(){
        return new DummyLoginService();
    }
}
