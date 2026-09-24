package com.example;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {
    TemperatureConverter tc = new TemperatureConverter();

    @org.junit.jupiter.api.Test
    void fahrenheitToCelsius() {
        assertEquals(0, tc.fahrenheitToCelsius(32));
        assertEquals(40, tc.fahrenheitToCelsius(104));
        assertEquals(-5, tc.fahrenheitToCelsius(23));
    }

    @org.junit.jupiter.api.Test
    void celsiusToFahrenheit() {
        assertEquals(32, tc.celsiusToFahrenheit(0));
        assertEquals(104, tc.celsiusToFahrenheit(40));
        assertEquals(23, tc.celsiusToFahrenheit(-5));
    }

    @org.junit.jupiter.api.Test
    void isExtremeTemperature() {
        assertTrue(tc.isExtremeTemperature(-100));
        assertFalse(tc.isExtremeTemperature(0));
        assertTrue(tc.isExtremeTemperature(100));
        assertFalse(tc.isExtremeTemperature(25));
    }
}