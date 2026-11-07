package com.example.dao;

import com.example.model.TemperatureRecord;
import com.example.model.TemperatureUnit;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureRecordDAOTest {

    private final TemperatureRecordDAO dao = new TemperatureRecordDAO();

    @Test
    void shouldSaveRecord() {
        TemperatureRecord record = new TemperatureRecord(1, 2, 25.0, 77.0);
        assertDoesNotThrow(() -> dao.save(record));
    }

    @Test
    void shouldReturnRecords() {
        List<TemperatureRecord> records = dao.findAll();
        assertNotNull(records);
    }

    @Test
    void shouldReturnAtLeastOneRecord() {
        List<TemperatureRecord> records = dao.findAll();
        assertFalse(records.isEmpty());
    }

    @Test
    void savedRecordShouldAppearInList() {
        TemperatureUnitDAO unitDAO = new TemperatureUnitDAO();
        List<TemperatureUnit> units = unitDAO.findAll();

        int sourceId = units.get(0).getId();
        int targetId = units.get(1).getId();

        TemperatureRecord record = new TemperatureRecord(sourceId, targetId, 123, 253.4);
        dao.save(record);

        List<TemperatureRecord> records = dao.findAll();
        boolean found = records.stream().anyMatch(r -> r.getSourceUnitId() == sourceId && r.getTargetUnitId() == targetId && r.getOriginalValue() == 123 && r.getConvertedValue() == 253.4);

        assertTrue(found);
    }


    @Test
    void shouldLoadCreatedAt() {
        List<TemperatureRecord> records = dao.findAll();
        assertTrue(records.stream().allMatch(r -> r.getCreatedAt() != null));
    }
}