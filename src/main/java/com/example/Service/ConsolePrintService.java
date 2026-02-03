package com.example.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsolePrintService implements PrintService {

    @Value("${print.console.prefix}")
    private String prefix;

    @Override
    public void print(String message) {
        System.out.println(prefix + " " + message);
    }
}
