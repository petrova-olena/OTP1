package com.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureUnitTest {

    @Test
    void shouldCreateUnitUsingConstructor() {
        TemperatureUnit unit = new TemperatureUnit(1, "Celsius", "Celsius", "Celsius", "摂氏", "سيلسيوس", "C");
        assertEquals(1, unit.getId());
        assertEquals("Celsius", unit.getNameEn());
        assertEquals("Celsius", unit.getNameFi());
        assertEquals("Celsius", unit.getNameSv());
        assertEquals("摂氏", unit.getNameJa());
        assertEquals("سيلسيوس", unit.getNameAr());
        assertEquals("C", unit.getSymbol());
    }

    @Test
    void shouldSetAndGetId() {
        TemperatureUnit unit = new TemperatureUnit();
        unit.setId(99);
        assertEquals(99, unit.getId());
    }

    @Test
    void shouldSetAndGetName() {
        TemperatureUnit unit = new TemperatureUnit();
        unit.setNameEn("Kelvin");
        assertEquals("Kelvin", unit.getNameEn());
    }

    @Test
    void shouldSetAndGetSymbol() {
        TemperatureUnit unit = new TemperatureUnit();
        unit.setSymbol("K");
        assertEquals("K", unit.getSymbol());
    }

    @Test
    void shouldCreateEmptyUnit() {
        TemperatureUnit unit = new TemperatureUnit();
        assertNotNull(unit);
    }
}