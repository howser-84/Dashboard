package org.dashboard.main;


import org.dashboard.main.service.DBTaskService;
import org.dashboard.main.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public TaskService getTaskService(){
        return new DBTaskService();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
