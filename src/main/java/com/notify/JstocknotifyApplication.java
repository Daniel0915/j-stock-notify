package com.notify;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JstocknotifyApplication implements CommandLineRunner {
    @Autowired
    EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
//        SpringApplication app = new SpringApplication(JstocknotifyApplication.class);
//        app.setWebApplicationType(WebApplicationType.NONE);
//        app.run(args);

//        임원ㆍ주요주주 소유보고: exec_ownership
//        대량보유 상황보고: large_holdings
        SpringApplication.run(JstocknotifyApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("entityManagerFactory = " + entityManagerFactory);
    }
}
