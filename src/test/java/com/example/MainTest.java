package com.example;

import com.example.model.TemperatureUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {
    private final TemperatureUnit celsius =
            new TemperatureUnit(1, "Celsius", "Celsius", "Celsius", "摂氏", "سيلسيوس", "C");

    private final TemperatureUnit fahrenheit =
            new TemperatureUnit(2, "Fahrenheit", "Fahrenheit", "Fahrenheit", "華氏", "فهرنهايت", "F");

    @Test
    void shouldReturnSameValueWhenUnitsAreEqual() {
        double result = Main.convertTemperature(100, celsius, celsius);
        assertEquals(100, result);
    }

    @Test
    void shouldConvertCelsiusToFahrenheit() {
        double result = Main.convertTemperature(100, celsius, fahrenheit);
        assertEquals(212, result);
    }

    @Test
    void shouldConvertFahrenheitToCelsius() {
        double result = Main.convertTemperature(32, fahrenheit, celsius);
        assertEquals(0, result);
    }

    @Test
    void shouldThrowExceptionWhenTargetUnitIsInvalid() {
        TemperatureUnit invalid =
                new TemperatureUnit(3, "Invalid", "Invalid", "Invalid", "Invalid", "Invalid", "X");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Main.convertTemperature(100, celsius, invalid));
        assertEquals("Invalid temperature units", exception.getMessage());
    }
}