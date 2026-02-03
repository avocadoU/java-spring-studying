package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        PrintAction printAction = context.getBean(PrintAction.class);
        printAction.addMessage("Hello World!");
        printAction.run();
    }
}