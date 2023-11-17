package com.craziestate.alerts.event;

import java.util.UUID;

public record AlertSendingEvent(UUID id, Integer alertId, Integer offerId, Integer customerId) {
}
