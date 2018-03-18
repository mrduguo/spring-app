package com.github.mrduguo.spring.app.config;

import org.springframework.boot.context.config.RandomValuePropertySource;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EnvConfig {

    public static void init() {
        populateStartupTime();
        setupActiveProfile();
    }

    private static void setupActiveProfile() {
        if (System.getProperty("spring.profiles.active")!=null && System.getenv("SPRING_PROFILES_ACTIVE")!=null) {
            String classPath = System.getProperty("java.class.path");
            String activeProfiles = (classPath.matches(".*test.classes.*") || classPath.matches(".*classes.test.*")) ? "test"
                    : (classPath.matches(".*production.classes.*") || classPath.matches(".*classes.main.*")) ? "local"
                    : "server";
            if(activeProfiles.equals("test")){
                System.setProperty("server.port", Integer.toString((int )(Math.random() * 1024 + 11000)));
            }
            try{
                activeProfiles=activeProfiles+","+ InetAddress.getLocalHost().getHostName();
            }catch (Exception ignore){}
            System.setProperty("spring.profiles.active",activeProfiles);
        }
    }

    private static void populateStartupTime() {
        System.setProperty("info.app.start.time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(new Date(ManagementFactory.getRuntimeMXBean().getStartTime())));
    }
}
