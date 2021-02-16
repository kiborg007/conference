package aval.ua.conference.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MicrometerManager {

    @Autowired
    private MeterRegistry meterRegistry;

    public void trackCounterMetrics(String metricName, double value, String... tags){
        meterRegistry.counter(metricName, tags).increment(value);
    }
}
