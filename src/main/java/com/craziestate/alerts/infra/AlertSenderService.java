package com.craziestate.alerts.infra;

import com.craziestate.alerts.event.AlertSendingEvent;

public interface AlertSenderService {

    /**
     * Send an event to the Alert Sending system.
     *
     * @param event
     */
    void send(AlertSendingEvent event);
}
