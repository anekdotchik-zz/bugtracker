package org.test.bugtracker.starter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationStarter {
    private ApplicationContext context;

    public ApplicationStarter(ApplicationContext context) {
        this.context = context;
    }

    public void start() {
        context.getBeanDefinitionNames();
    }

    public static void main(String[] args) {
        new ApplicationStarter(new ClassPathXmlApplicationContext(
                "spring/context.xml")).start();
    }
}
