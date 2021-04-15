package com.waracle.cakemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CakeManagerMavenWebappApplication {


    //docker: docker run --publish 8080:8080  cake-manager-new
    //locally to test to avoid security layer: mvn spring-boot:run -Dspring-boot.run.profiles=test
    //locally: mvn spring-boot:run
    public static void main(String[] args) {

        SpringApplication.run(CakeManagerMavenWebappApplication.class, args);

    }

}
