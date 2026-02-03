package com.example;

import com.example.Service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PrintAction {

    private PrintService printService;

    private String message;

    @Autowired
    public PrintAction(@Qualifier("consolePrintService") PrintService printService) {
        this.printService = printService;
    }

    public void addMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void run() {
        printService.print(message);
    }
}
