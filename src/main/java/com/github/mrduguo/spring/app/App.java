package com.github.mrduguo.spring.app;

import com.github.mrduguo.spring.app.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.github"})
public class App {

    static {
        EnvConfig.init();
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
