package com.example;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private PrintAction printAction;

    public Scheduler(PrintAction printAction) {
        this.printAction = printAction;
    }

    @Scheduled(fixedRateString = "${scheduler.frequency}")
    public void task() {
        printAction.run();
    }
}
