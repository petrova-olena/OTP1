package com.example.model;

import org.junit.jupiter.api.Test;

import java.util.Locale;

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

    @Test
    void shouldSetAndGetFinnishName() {
        TemperatureUnit unit = new TemperatureUnit();
        unit.setNameFi("Celsius");
        assertEquals("Celsius", unit.getNameFi());
    }

    @Test
    void shouldSetAndGetSwedishName() {
        TemperatureUnit unit = new TemperatureUnit();
        unit.setNameSv("Celsius");
        assertEquals("Celsius", unit.getNameSv());
    }

    @Test
    void shouldSetAndGetJapaneseName() {
        TemperatureUnit unit = new TemperatureUnit();
        unit.setNameJa("摂氏");
        assertEquals("摂氏", unit.getNameJa());
    }

    @Test
    void shouldSetAndGetArabicName() {
        TemperatureUnit unit = new TemperatureUnit();
        unit.setNameAr("سيلسيوس");
        assertEquals("سيلسيوس", unit.getNameAr());
    }

    private TemperatureUnit createUnit() {
        return new TemperatureUnit(
                1,
                "Celsius",
                "Celsius FI",
                "Celsius SV",
                "摂氏",
                "سيلسيوس",
                "C"
        );
    }

    @Test
    void shouldReturnEnglishName() {
        TemperatureUnit unit = createUnit();
        assertEquals("Celsius", unit.getLocalizedName(Locale.ENGLISH));
    }

    @Test
    void shouldReturnFinnishName() {
        TemperatureUnit unit = createUnit();
        assertEquals("Celsius FI", unit.getLocalizedName(new Locale("fi")));
    }

    @Test
    void shouldReturnSwedishName() {
        TemperatureUnit unit = createUnit();
        assertEquals("Celsius SV", unit.getLocalizedName(new Locale("sv")));
    }

    @Test
    void shouldReturnJapaneseName() {
        TemperatureUnit unit = createUnit();
        assertEquals("摂氏", unit.getLocalizedName(new Locale("ja")));
    }

    @Test
    void shouldReturnArabicName() {
        TemperatureUnit unit = createUnit();
        assertEquals("سيلسيوس", unit.getLocalizedName(new Locale("ar")));
    }

    @Test
    void shouldReturnEnglishNameInToString() {
        TemperatureUnit unit = createUnit();
        assertEquals("Celsius", unit.toString());
    }
}