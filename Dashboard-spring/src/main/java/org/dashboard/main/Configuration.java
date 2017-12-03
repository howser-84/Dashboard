package org.dashboard.main;


import org.dashboard.main.service.DummyLoginService;
import org.dashboard.main.service.DummyTaskService;
import org.dashboard.main.service.LoginService;
import org.dashboard.main.service.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@org.springframework.context.annotation.Configuration
@EnableWebMvc
@ComponentScan
public class Configuration {

    @Bean
    public TaskService getTaskService(){
        return new DummyTaskService();
    }

    @Bean
    public LoginService getLoginService(){
        return new DummyLoginService();
    }
}
