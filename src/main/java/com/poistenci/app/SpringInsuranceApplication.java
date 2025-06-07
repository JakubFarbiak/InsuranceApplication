package com.poistenci.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.poistenci.app.models")  // Ensure this matches the package of your entities
public class SpringInsuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringInsuranceApplication.class, args);
    }
}
