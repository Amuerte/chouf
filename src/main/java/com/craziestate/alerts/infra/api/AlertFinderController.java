package com.craziestate.alerts.infra.api;

import com.craziestate.alerts.domain.Alert;
import com.craziestate.alerts.domain.AlertRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alert")
public class AlertFinderController {

    private AlertRepository alertRepository;

    public AlertFinderController(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @GetMapping
    public List<Alert> alerts() {
        return alertRepository.findAll();
    }
}
