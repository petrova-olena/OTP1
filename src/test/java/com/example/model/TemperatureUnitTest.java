package com.example.model;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureUnitTest {
    private final TemperatureUnit unit = new TemperatureUnit(
            1,
            "Celsius",
            "Celsius",
            "Celsius",
            "摂氏",
            "سيلسيوس",
            "C"
    );

    @Test
    void shouldCreateUnitUsingConstructor() {
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
        unit.setId(99);
        assertEquals(99, unit.getId());
    }

    @Test
    void shouldSetAndGetName() {
        unit.setNameEn("Kelvin");
        assertEquals("Kelvin", unit.getNameEn());
    }

    @Test
    void shouldSetAndGetSymbol() {
        unit.setSymbol("K");
        assertEquals("K", unit.getSymbol());
    }

    @Test
    void shouldCreateEmptyUnit() {
        assertNotNull(unit);
    }

    @Test
    void shouldSetAndGetEnglishName() {
        unit.setNameEn("English");
        assertEquals("English", unit.getNameEn());
    }

    @Test
    void shouldSetAndGetFinnishName() {
        unit.setNameFi("Celsius");
        assertEquals("Celsius", unit.getNameFi());
    }

    @Test
    void shouldSetAndGetSwedishName() {
        unit.setNameSv("Celsius");
        assertEquals("Celsius", unit.getNameSv());
    }

    @Test
    void shouldSetAndGetJapaneseName() {
        unit.setNameJa("摂氏");
        assertEquals("摂氏", unit.getNameJa());
    }

    @Test
    void shouldSetAndGetArabicName() {
        unit.setNameAr("سيلسيوس");
        assertEquals("سيلسيوس", unit.getNameAr());
    }

    @Test
    void shouldReturnEnglishName() {
        assertEquals("Celsius", unit.getLocalizedName(Locale.ENGLISH));
    }

    @Test
    void shouldReturnFinnishName() {
        assertEquals("Celsius", unit.getLocalizedName(new Locale("fi")));
    }

    @Test
    void shouldReturnSwedishName() {
        assertEquals("Celsius", unit.getLocalizedName(new Locale("sv")));
    }

    @Test
    void shouldReturnJapaneseName() {
        assertEquals("摂氏", unit.getLocalizedName(new Locale("ja")));
    }

    @Test
    void shouldReturnArabicName() {
        assertEquals("سيلسيوس", unit.getLocalizedName(new Locale("ar")));
    }

    @Test
    void shouldReturnEnglishNameForDefaultLocale() {
        assertEquals("Celsius",
                unit.getLocalizedName(Locale.US));
    }

    @Test
    void shouldReturnEnglishNameForUnsupportedLocale() {
        assertEquals("Celsius",
                unit.getLocalizedName(Locale.GERMANY));
    }

    @Test
    void shouldReturnEnglishNameInToString() {
        assertEquals("Celsius", unit.toString());
    }
}