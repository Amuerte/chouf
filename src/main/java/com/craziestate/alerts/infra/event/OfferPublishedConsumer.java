package com.craziestate.alerts.infra.event;

import com.craziestate.alerts.domain.AlertRepository;
import com.craziestate.alerts.event.AlertSendingEvent;
import com.craziestate.alerts.event.OfferPublishedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OfferPublishedConsumer {

    Logger log = LoggerFactory.getLogger(OfferPublishedConsumer.class);

    private AlertRepository alertRepository;
    private String alertSendTopic;
    private KafkaTemplate<Integer, Object> kafkaTemplate;

    public OfferPublishedConsumer(@Value("${topics.buyer.alertSend}") String alertSendTopic, AlertRepository alertRepository,
                                  KafkaTemplate kafkaTemplate) {
        this.alertRepository = alertRepository;
        this.alertSendTopic = alertSendTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "${topics.buyer.offerPublished}")
    public void consume(OfferPublishedEvent event) {
        log.debug("Consuming published offer event {}", event);
        var offerId = event.offerId();
        var alertEvents = alertRepository.findByOfferId(offerId).stream()
                .map(alert -> new AlertSendingEvent(UUID.randomUUID(), alert.getId(), offerId, alert.getCustomerId())).toList();

        if (alertEvents.isEmpty()) {
            log.debug("No alert notification to send for offer {}", event.offerId());
        }

        for (AlertSendingEvent e:alertEvents) {
            log.debug("Publishing Alert Send event {}", e);
            kafkaTemplate.send(alertSendTopic, e.customerId(), e);
        }
    }
}
