package com.example.dao;

import com.example.database.DBConnection;
import com.example.model.TemperatureRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TemperatureRecordDAO {

    private static final Logger log = LoggerFactory.getLogger(TemperatureRecordDAO.class);

    public List<TemperatureRecord> findAll() {
        List<TemperatureRecord> records = new ArrayList<>();

        String sql = """
            SELECT
                source_unit_id,
                target_unit_id,
                original_value,
                converted_value,
                created_at
            FROM temperature_conversion
            """;
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()
        ) {

            while (rs.next()) {
                TemperatureRecord temperatureRecord =
                        new TemperatureRecord(
                                rs.getInt("source_unit_id"),
                                rs.getInt("target_unit_id"),
                                rs.getDouble("original_value"),
                                rs.getDouble("converted_value")
                        );

                temperatureRecord.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                records.add(temperatureRecord);
            }

        } catch (Exception e) {
            log.error("error: ", e);
        }

        return records;
    }

    public void save(TemperatureRecord temperatureRecord) {
        String sql = """
                INSERT INTO temperature_conversion
                (
                    source_unit_id,
                    target_unit_id,
                    original_value,
                    converted_value
                )
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, temperatureRecord.getSourceUnitId());
            statement.setInt(2, temperatureRecord.getTargetUnitId());
            statement.setDouble(3, temperatureRecord.getOriginalValue());
            statement.setDouble(4, temperatureRecord.getConvertedValue());

            statement.executeUpdate();

        } catch (Exception e) {
            log.error("error: ", e);
        }
    }
}