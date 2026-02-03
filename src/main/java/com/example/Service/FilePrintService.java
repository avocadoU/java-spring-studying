package com.example.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FilePrintService implements PrintService {

    @Value("${print.file.prefix}")
    private String prefix;

    @Override
    public void print(String message) {
        System.out.println(prefix + " " + message);
    }
}
