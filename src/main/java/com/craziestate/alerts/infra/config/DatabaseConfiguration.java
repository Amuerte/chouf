package com.craziestate.alerts.infra.config;

import com.craziestate.alerts.domain.EstateType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.List;

@Configuration
public class DatabaseConfiguration extends AbstractJdbcConfiguration {
    @ReadingConverter
    public class EstateTypeReadConverter implements Converter<String, EstateType> {
        public EstateType convert(String source) {
            return EstateType.fromCode(source.charAt(0));
        }
    }

    @Override
    protected List<?> userConverters() {
        return List.of(new EstateTypeReadConverter());
    }
}