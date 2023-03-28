package com.craziestate.alerts.infra.api;

import com.craziestate.alerts.domain.Offer;
import com.craziestate.alerts.domain.OfferRepository;
import com.craziestate.alerts.infra.event.OfferPublishedProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/offer")
public class OfferController {

    private OfferRepository offerRepository;
    private OfferPublishedProducer offerPublishedProducer;

    public OfferController(OfferRepository offerRepository, OfferPublishedProducer offerPublishedProducer) {
        this.offerRepository = offerRepository;
        this.offerPublishedProducer = offerPublishedProducer;
    }

    @GetMapping
    public List<Offer> offers() {
        return StreamSupport.stream(offerRepository.findAll().spliterator(), false).toList();
    }

    @PostMapping("/{offerId}/publish")
    public void alerts(@PathVariable Integer offerId) {
        offerPublishedProducer.produce(offerId);
    }
}
