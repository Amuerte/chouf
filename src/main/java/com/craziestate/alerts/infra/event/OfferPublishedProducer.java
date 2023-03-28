package com.craziestate.alerts.infra.event;

import com.craziestate.alerts.event.OfferPublishedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OfferPublishedProducer {

    Logger log = LoggerFactory.getLogger(OfferPublishedProducer.class);

    private String topic;
    private KafkaTemplate<Integer, Object> kafkaTemplate;

    public OfferPublishedProducer(@Value("${topics.buyer.offerPublished}") String topic, KafkaTemplate<Integer, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void produce(Integer offerId) {
        var event = new OfferPublishedEvent(UUID.randomUUID(), offerId);
        log.debug("Producing Offer Published event {}", event);
        kafkaTemplate.send(topic, offerId, event);
    }
}
