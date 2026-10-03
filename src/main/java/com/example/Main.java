package com.example;

import com.example.dao.TemperatureRecordDAO;
import com.example.dao.TemperatureUnitDAO;
import com.example.model.TemperatureRecord;
import com.example.model.TemperatureUnit;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main extends Application{

    public static double convertTemperature(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals(toUnit)) {
            return value;
        } else if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
            return TemperatureConverter.celsiusToFahrenheit(value);
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
            return TemperatureConverter.fahrenheitToCelsius(value);
        } else {
            throw new IllegalArgumentException("Invalid temperature units");
        }
    }

    public void start(Stage stage) {
        Label unit1Label = new Label("Unit 1:");
        ComboBox<String> unit1ComboBox = new ComboBox<>();
        unit1ComboBox.getItems().addAll("Celsius", "Fahrenheit");

        Label unit2Label = new Label("Unit 2:");
        ComboBox<String> unit2ComboBox = new ComboBox<>();
        unit2ComboBox.getItems().addAll("Celsius", "Fahrenheit");

        Label valueLabel = new Label("Value:");
        TextField valueTextField = new TextField();

        Button convertButton = new Button("Convert");
        Label resultLabel = new Label();

        TemperatureUnitDAO unitDAO = new TemperatureUnitDAO();
        TemperatureRecordDAO recordDAO = new TemperatureRecordDAO();

        // Create results table with history of convertions
        TableView<TemperatureRecord> historyTable = new TableView<>();
        historyTable.setPrefHeight(250);
        historyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<TemperatureRecord, Double> inputColumn = new TableColumn<>("Input");
        inputColumn.setCellValueFactory(new PropertyValueFactory<>("originalValue"));

        TableColumn<TemperatureRecord, Double> resultColumn = new TableColumn<>("Result");
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("convertedValue"));

        TableColumn<TemperatureRecord, LocalDateTime> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        dateColumn.setCellFactory(column ->
                new TableCell<>() {

                    @Override
                    protected void updateItem(
                            LocalDateTime item,
                            boolean empty
                    ) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
                        }
                    }
                }
        );

        historyTable.getColumns().addAll(inputColumn, resultColumn, dateColumn );
        historyTable.getItems().addAll(recordDAO.findAll());

        convertButton.setOnAction(e -> {
            try {
                double value = Double.parseDouble(valueTextField.getText());
                String fromUnit = unit1ComboBox.getValue();
                String toUnit = unit2ComboBox.getValue();

                double result = convertTemperature(value, fromUnit, toUnit);
                result = Math.round(result * 100.0) / 100.0;

                TemperatureUnit source = unitDAO.findByName(fromUnit);
                TemperatureUnit target = unitDAO.findByName(toUnit);

                if (source != null && target != null) {
                    TemperatureRecord record = new TemperatureRecord(source.getId(), target.getId(), value, result);

                    recordDAO.save(record);
                    historyTable.getItems().clear();
                    historyTable.getItems().addAll(recordDAO.findAll());
                }
                resultLabel.setText(
                        String.format(
                                "%.2f %s → %.2f %s",
                                value,
                                source.getSymbol(),
                                result,
                                target.getSymbol()
                        )
                );

            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input");

            } catch (IllegalArgumentException ex) {
                resultLabel.setText(ex.getMessage());
            }
        });

        VBox root = new VBox(10,
                unit1Label, unit1ComboBox,
                unit2Label, unit2ComboBox,
                valueLabel, valueTextField,
                convertButton,
                resultLabel,
                new Label("Conversion History:"),
                historyTable
        );
        root.setPadding(new Insets(20));
        root.setSpacing(10);

        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Temperature Converter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}