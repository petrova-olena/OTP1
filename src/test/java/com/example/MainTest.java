
package com.example;

import com.example.model.TemperatureUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final TemperatureUnit celsius =
            new TemperatureUnit(
                    1, "Celsius", "Celsius", "Celsius",
                    "摂氏", "سيلسيوس", "C"
            );

    private final TemperatureUnit fahrenheit =
            new TemperatureUnit(
                    2, "Fahrenheit", "Fahrenheit", "Fahrenheit",
                    "華氏", "فهرنهايت", "F"
            );

    private final TemperatureUnit invalid =
            new TemperatureUnit(
                    3, "Invalid", "Invalid", "Invalid",
                    "Invalid", "Invalid", "X"
            );

    @Test
    void shouldReturnSameValueWhenUnitsAreEqual() {
        double result =
                Main.convertTemperature(100, celsius, celsius);

        assertEquals(100, result);
    }

    @Test
    void shouldReturnSameValueWhenDifferentUnitsHaveSameSymbol() {
        TemperatureUnit anotherCelsius =
                new TemperatureUnit(
                        4, "Another Celsius", "Another Celsius",
                        "Another Celsius", "摂氏", "سيلسيوس", "C"
                );

        double result =
                Main.convertTemperature(25, celsius, anotherCelsius);

        assertEquals(25, result);
    }

    @Test
    void shouldConvertCelsiusToFahrenheit() {
        double result =
                Main.convertTemperature(100, celsius, fahrenheit);

        assertEquals(212, result);
    }

    @Test
    void shouldConvertFahrenheitToCelsius() {
        double result =
                Main.convertTemperature(32, fahrenheit, celsius);

        assertEquals(0, result);
    }

    @Test
    void shouldConvertNegativeCelsiusToFahrenheit() {
        double result =
                Main.convertTemperature(-40, celsius, fahrenheit);

        assertEquals(-40, result);
    }

    @Test
    void shouldConvertNegativeFahrenheitToCelsius() {
        double result =
                Main.convertTemperature(-40, fahrenheit, celsius);

        assertEquals(-40, result);
    }

    @Test
    void shouldConvertDecimalCelsiusToFahrenheit() {
        double result =
                Main.convertTemperature(37.5, celsius, fahrenheit);

        assertEquals(99.5, result, 0.0001);
    }

    @Test
    void shouldThrowExceptionWhenTargetUnitIsInvalid() {
        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> Main.convertTemperature(100, celsius, invalid)
                );

        assertEquals("Invalid temperature units", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSourceUnitIsInvalid() {
        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> Main.convertTemperature(100, invalid, celsius)
                );

        assertEquals("Invalid temperature units", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBothUnitsAreDifferentAndInvalid() {
        TemperatureUnit anotherInvalid =
                new TemperatureUnit(
                        5, "Other", "Other", "Other",
                        "Other", "Other", "Y"
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> Main.convertTemperature(100, invalid, anotherInvalid)
        );
    }
}
