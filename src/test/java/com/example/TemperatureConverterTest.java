package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {
    @Test
    void fahrenheitToCelsius() {
        assertEquals(0.0, TemperatureConverter.fahrenheitToCelsius(32));
    }

    @Test
    void celsiusToFahrenheit() {
        assertEquals(212.0, TemperatureConverter.celsiusToFahrenheit(100));
    }

    @Test
    void negativeCelsiusToFahrenheit() {
        assertEquals(-40.0, TemperatureConverter.celsiusToFahrenheit(-40));
    }

    @Test
    void negativeFahrenheitToCelsius() {
        assertEquals(-40.0, TemperatureConverter.fahrenheitToCelsius(-40));
    }

    @Test
    void shouldConvertFractionalValues() {
        assertEquals(33.8, TemperatureConverter.celsiusToFahrenheit(1), 0.0001);
    }

    @Test
    void shouldReturnTrueForTemperatureBelowExtremeRange() {
        assertTrue(TemperatureConverter.isExtremeTemperature(-41));
    }

    @Test
    void shouldReturnTrueForTemperatureAboveExtremeRange() {
        assertTrue(TemperatureConverter.isExtremeTemperature(51));
    }

    @Test
    void shouldReturnFalseAtLowerBoundary() {
        assertFalse(TemperatureConverter.isExtremeTemperature(-40));
    }

    @Test
    void shouldReturnFalseAtUpperBoundary() {
        assertFalse(TemperatureConverter.isExtremeTemperature(50));
    }

    @Test
    void shouldReturnFalseForTemperatureInsideNormalRange() {
        assertFalse(TemperatureConverter.isExtremeTemperature(20));
    }


}