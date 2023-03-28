package com.craziestate.alerts.domain;

import java.util.List;

public interface AlertRepository {
    List<Alert> findByOfferId(int offerId);

    List<Alert> findAll();
}
