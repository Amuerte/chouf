package com.craziestate.alerts;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AlertsFinder {

    public static void main(String[] args) {
        var app = new SpringApplicationBuilder(AlertsFinder.class);

        app.run(args);
    }
}
