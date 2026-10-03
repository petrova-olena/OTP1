package com.example.dao;

import com.example.model.TemperatureUnit;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureUnitDAOTest {
    private final TemperatureUnitDAO dao = new TemperatureUnitDAO();

    @Test
    void shouldReturnAllUnits() {
        List<TemperatureUnit> units = dao.findAll();
        assertNotNull(units);
        assertFalse(units.isEmpty());
    }

    @Test
    void shouldContainCelsius() {
        List<TemperatureUnit> units = dao.findAll();

        boolean found = units.stream().anyMatch(u -> u.getName().equals("Celsius"));
        assertTrue(found);
    }

    @Test
    void shouldContainFahrenheit() {
        List<TemperatureUnit> units = dao.findAll();
        boolean found = units.stream().anyMatch(u -> u.getName().equals("Fahrenheit"));
        assertTrue(found);
    }

    @Test
    void shouldFindCelsiusByName() {
        TemperatureUnit unit = dao.findByName("Celsius");
        assertNotNull(unit);
        assertEquals("Celsius", unit.getName());
        assertEquals("C", unit.getSymbol());
    }

    @Test
    void shouldFindFahrenheitByName() {
        TemperatureUnit unit = dao.findByName("Fahrenheit");
        assertNotNull(unit);
        assertEquals("Fahrenheit", unit.getName());
        assertEquals("F", unit.getSymbol());
    }

    @Test
    void shouldReturnNullForUnknownTemperatureUnit() {
        TemperatureUnit unit = dao.findByName("Unknown");
        assertNull(unit);
    }
}