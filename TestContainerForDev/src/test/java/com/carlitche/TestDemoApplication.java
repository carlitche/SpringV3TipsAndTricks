package com.carlitche;

import carlitche.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class TestDemoApplication {

  @Bean
  @RestartScope  //we want this container started but if already on just live there
  @ServiceConnection
  PostgreSQLContainer postgreSQLContainer(){
    return new PostgreSQLContainer("postgres:15-alpine");
  }

  public static void main(String[] args){
    //delegate to our Application main
    SpringApplication.from(Application::main)
                     .with(TestDemoApplication.class) //add this configuration
                     .run(args);
  }
}
