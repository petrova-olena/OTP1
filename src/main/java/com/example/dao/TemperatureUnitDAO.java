package com.example.dao;

import com.example.database.DBConnection;
import com.example.model.TemperatureUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TemperatureUnitDAO {

    public List<TemperatureUnit> findAll() {

        List<TemperatureUnit> units = new ArrayList<>();

        String sql = """
            SELECT
                id,
                name_en,
                name_fi,
                name_sv,
                name_ja,
                name_ar,
                symbol
            FROM temperature_unit
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()
        ) {

            while (rs.next()) {

                units.add(
                        new TemperatureUnit(
                                rs.getInt("id"),
                                rs.getString("name_en"),
                                rs.getString("name_fi"),
                                rs.getString("name_sv"),
                                rs.getString("name_ja"),
                                rs.getString("name_ar"),
                                rs.getString("symbol")
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return units;
    }

    public TemperatureUnit findBySymbol(String symbol) {

        String sql = """
            SELECT
                id,
                name_en,
                name_fi,
                name_sv,
                name_ja,
                name_ar,
                symbol
            FROM temperature_unit
            WHERE symbol = ?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, symbol);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return new TemperatureUnit(
                        rs.getInt("id"),
                        rs.getString("name_en"),
                        rs.getString("name_fi"),
                        rs.getString("name_sv"),
                        rs.getString("name_ja"),
                        rs.getString("name_ar"),
                        rs.getString("symbol")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}