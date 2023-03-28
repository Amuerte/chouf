package com.craziestate.alerts.infra.repositories;

import com.craziestate.alerts.domain.Alert;
import com.craziestate.alerts.domain.AlertRepository;
import com.craziestate.alerts.domain.EstateType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class JdbcAlertRepository implements AlertRepository {

    private static final RowMapper<Alert> ROW_MAPPER = (rs, rowNum) -> new Alert(
            rs.getInt("id"),
            rs.getObject("alert_id", UUID.class),
            rs.getInt("customer_id"),
            EstateType.fromCode(rs.getString("estate_type").charAt(0)),
            rs.getString("country_code"),
            rs.getString("region_code"),
            rs.getString("city_code"),
            rs.getString("criteria"),
            rs.getBoolean("active"),
            rs.getTimestamp("creation_time").toInstant(),
            rs.getTimestamp("update_time").toInstant()
    );
    
    private static final String FIND_ALERTS_FOR_OFFER = """
            select
            	a.*
            from alert a join offer o on (o.estate_type  = a.estate_type  and o.country_code = a.country_code and o.region_code  = o.region_code)
            where
                o.id = ?
            and	o.floor_area >= coalesce ((a.criteria->>'floor_area_min')::int, 0)
            and o.floor_area <= coalesce ((a.criteria->>'floor_area_max')::int, #max_int_value)
            and o.price >= coalesce ((a.criteria->>'price_min')::int, 0)
            and o.price <= coalesce ((a.criteria->>'price_max')::int, #max_int_value)
            and coalesce (o.land_area, 0) >= coalesce ((a.criteria->>'land_area_min')::int, 0)
            and coalesce (o.land_area, 0) <= coalesce ((a.criteria->>'land_area_max')::int, #max_int_value)
            and coalesce (o.rooms, 0) >= coalesce ((a.criteria->>'rooms_min')::int, 0)
            and coalesce (o.rooms, 0) <= coalesce ((a.criteria->>'rooms_max')::int, #max_int_value)
            and coalesce (o.bathrooms, 0) >= coalesce ((a.criteria->>'bathrooms_min')::int, 0)
            and coalesce (o.bathrooms, 0) <= coalesce ((a.criteria->>'bathrooms_max')::int, #max_int_value)
            and coalesce (o.bedrooms, 0) >= coalesce ((a.criteria->>'bedrooms_min')::int, 0)
            and coalesce (o.bedrooms, 0) <= coalesce ((a.criteria->>'bedrooms_max')::int, #max_int_value)
            """.replace("#max_int_value", String.valueOf(Integer.MAX_VALUE));
    
    private final JdbcTemplate jdbcTemplate;

    public JdbcAlertRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Alert> findByOfferId(int offerId) {
        return jdbcTemplate.query(FIND_ALERTS_FOR_OFFER, ROW_MAPPER, offerId);
    }

    @Override
    public List<Alert> findAll() {
        return jdbcTemplate.query("SELECT * FROM alert", ROW_MAPPER);
    }
}
