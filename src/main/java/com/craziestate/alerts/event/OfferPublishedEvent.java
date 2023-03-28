package com.craziestate.alerts.event;

import java.util.UUID;

public record OfferPublishedEvent(UUID id, Integer offerId) {
}
