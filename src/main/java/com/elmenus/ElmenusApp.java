package com.elmenus;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;



@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ElmenusApp.class, loader = AnnotationConfigContextLoader.class)
@SpringBootApplication
public class ElmenusApp {
    public static void main(String[] args) {
        SpringApplication.run(ElmenusApp.class, args);
    }
}
