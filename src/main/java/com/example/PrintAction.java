package com.example;

import com.example.Service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PrintAction {

    private PrintService printService;

    @Autowired
    public PrintAction(@Qualifier("consolePrintService")PrintService printService) {
        this.printService = printService;
    }

    public void run(String message) {
        printService.print(message);
    }
}
