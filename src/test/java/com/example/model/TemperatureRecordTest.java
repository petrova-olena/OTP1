package com.example.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureRecordTest {

    @Test
    void shouldCreateRecordUsingConstructor() {
        TemperatureRecord temperatureRecord = new TemperatureRecord(1, 2, 100.0, 212.0);
        assertEquals(1, temperatureRecord.getSourceUnitId());
        assertEquals(2, temperatureRecord.getTargetUnitId());
        assertEquals(100.0, temperatureRecord.getOriginalValue());
        assertEquals(212.0, temperatureRecord.getConvertedValue());
    }

    @Test
    void shouldSetAndGetCreatedAt() {
        TemperatureRecord temperatureRecord = new TemperatureRecord();
        LocalDateTime now = LocalDateTime.now();
        temperatureRecord.setCreatedAt(now);
        assertEquals(now, temperatureRecord.getCreatedAt());
    }

    @Test
    void shouldCreateEmptyRecord() {
        TemperatureRecord temperatureRecord = new TemperatureRecord();
        assertEquals(0, temperatureRecord.getSourceUnitId());
        assertEquals(0, temperatureRecord.getTargetUnitId());
        assertEquals(0.0, temperatureRecord.getOriginalValue());
        assertEquals(0.0, temperatureRecord.getConvertedValue());
        assertNull(temperatureRecord.getCreatedAt());
        assertNotNull(temperatureRecord);
    }

    @Test
    void shouldAllowNullCreatedAt() {
        TemperatureRecord temperatureRecord = new TemperatureRecord();
        temperatureRecord.setCreatedAt(null);

        assertNull(temperatureRecord.getCreatedAt());
    }
}