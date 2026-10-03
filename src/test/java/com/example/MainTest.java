package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {

    @Test
    void shouldReturnSameValueWhenUnitsAreEqual() {
        double result = Main.convertTemperature(100, "Celsius", "Celsius");
        assertEquals(100, result);
    }

    @Test
    void shouldConvertCelsiusToFahrenheit() {
        double result = Main.convertTemperature(100, "Celsius", "Fahrenheit");
        assertEquals(212, result);
    }

    @Test
    void shouldConvertFahrenheitToCelsius() {
        double result = Main.convertTemperature(32, "Fahrenheit", "Celsius");
        assertEquals(0, result);
    }

    @Test
    void shouldThrowExceptionWhenTargetUnitIsInvalid() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Main.convertTemperature(100, "Celsius", "WrongUnit"));
        assertEquals("Invalid temperature units", exception.getMessage());
    }
}