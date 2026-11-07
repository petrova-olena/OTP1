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

        boolean found = units.stream().anyMatch(u -> u.getNameEn().equals("Celsius"));
        assertTrue(found);
    }

    @Test
    void shouldContainFahrenheit() {
        List<TemperatureUnit> units = dao.findAll();
        boolean found = units.stream().anyMatch(u -> u.getNameEn().equals("Fahrenheit"));
        assertTrue(found);
    }

    @Test
    void shouldContainSymbolC() {
        List<TemperatureUnit> units = dao.findAll();
        boolean found = units.stream().anyMatch(u -> u.getSymbol().equals("C"));
        assertTrue(found);
    }

    @Test
    void shouldContainSymbolF() {
        List<TemperatureUnit> units = dao.findAll();
        boolean found = units.stream().anyMatch(u -> u.getSymbol().equals("F"));
        assertTrue(found);
    }

    @Test
    void shouldFindCelsiusBySymbol() {
        TemperatureUnit unit = dao.findBySymbol("C");
        assertNotNull(unit);
        assertEquals("C", unit.getSymbol());
        assertEquals("Celsius", unit.getNameEn());
    }

    @Test
    void shouldFindFahrenheitBySymbol() {
        TemperatureUnit unit = dao.findBySymbol("F");
        assertNotNull(unit);
        assertEquals("F", unit.getSymbol());
        assertEquals("Fahrenheit", unit.getNameEn());
    }

    @Test
    void shouldReturnNullForUnknownSymbol() {
        TemperatureUnit unit = dao.findBySymbol("X");
        assertNull(unit);
    }

    @Test
    void shouldReturnLocalizedNames() {

        TemperatureUnit unit = dao.findBySymbol("C");

        assertNotNull(unit.getNameEn());
        assertNotNull(unit.getNameFi());
        assertNotNull(unit.getNameSv());
        assertNotNull(unit.getNameJa());
        assertNotNull(unit.getNameAr());
    }
}