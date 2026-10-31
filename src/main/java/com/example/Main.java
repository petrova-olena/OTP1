package com.example;

import com.example.dao.TemperatureRecordDAO;
import com.example.dao.TemperatureUnitDAO;
import com.example.model.TemperatureRecord;
import com.example.model.TemperatureUnit;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main extends Application{
    private ResourceBundle bundle;

    private Label unit1Label;
    private Label unit2Label;
    private Label valueLabel;
    private Label resultLabel;
    private Label historyLabel;
    private Button convertButton;
    private Stage primaryStage;
    private TableColumn<TemperatureRecord, Double> inputColumn;
    private TableColumn<TemperatureRecord, Double> resultColumn;
    private TableColumn<TemperatureRecord, LocalDateTime> dateColumn;
    private VBox root;

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
        this.primaryStage = stage;

        bundle = ResourceBundle.getBundle("MessagesBundle", Locale.US);

        HBox upHBox = new HBox(10);
        VBox upVBox = new VBox(10);

        unit1Label = new Label(bundle.getString("unit1"));
        ComboBox<String> unit1ComboBox = new ComboBox<>();
        unit1ComboBox.getItems().addAll("Celsius", "Fahrenheit");

        unit2Label = new Label(bundle.getString("unit2"));
        ComboBox<String> unit2ComboBox = new ComboBox<>();
        unit2ComboBox.getItems().addAll("Celsius", "Fahrenheit");

        upVBox.getChildren().addAll(unit1Label, unit1ComboBox, unit2Label, unit2ComboBox);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ComboBox<String> languageCB = new ComboBox<>();
        languageCB.getItems().addAll("English", "Suomi", "Svenska", "日本語", "العربية");
        languageCB.setValue("English");

        languageCB.setOnAction(event -> {

            String language = languageCB.getValue();

            switch (language) {
                case "Suomi":
                    updateLanguage(new Locale("fi", "FI"));
                    break;
                case "Svenska":
                    updateLanguage(new Locale("sv", "SE"));
                    break;
                case "日本語":
                    updateLanguage(new Locale("ja", "JP"));
                    break;
                case "العربية":
                    updateLanguage(new Locale("ar", "SA"));
                    break;
                default:
                    updateLanguage(Locale.US);
            }
        });

        upHBox.getChildren().addAll(upVBox, spacer, languageCB);

        valueLabel = new Label(bundle.getString("value"));
        TextField valueTextField = new TextField();

        convertButton = new Button(bundle.getString("convert"));
        resultLabel = new Label();

        TemperatureUnitDAO unitDAO = new TemperatureUnitDAO();
        TemperatureRecordDAO recordDAO = new TemperatureRecordDAO();

        // Create results table with history of convertions
        TableView<TemperatureRecord> historyTable = new TableView<>();
        historyTable.setPrefHeight(250);
        historyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        inputColumn = new TableColumn<>(bundle.getString("input"));
        inputColumn.setCellValueFactory(new PropertyValueFactory<>("originalValue"));

        resultColumn = new TableColumn<>(bundle.getString("result"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("convertedValue"));

        dateColumn = new TableColumn<>(bundle.getString("date"));
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
                String arrow = bundle.getLocale().getLanguage().equals("ar")
                        ? "←"
                        : "→";

                resultLabel.setText(
                        String.format(
                                "%.2f %s %s %.2f %s",
                                value,
                                source.getSymbol(),
                                arrow,
                                result,
                                target.getSymbol()
                        )
                );

            } catch (NumberFormatException ex) {
                resultLabel.setText(bundle.getString("invalidInput"));

            } catch (IllegalArgumentException ex) {
                resultLabel.setText(ex.getMessage());
            }
        });

        historyLabel = new Label(bundle.getString("history"));
        root = new VBox(10,
                upHBox,
                valueLabel, valueTextField,
                convertButton,
                resultLabel,
                historyLabel,
                historyTable
        );
        root.setPadding(new Insets(20));
        root.setSpacing(10);

        Scene scene = new Scene(root, 600, 500);
        stage.setTitle(bundle.getString("title"));
        stage.setScene(scene);
        stage.show();
    }

    private void updateLanguage(Locale locale) {

        bundle = ResourceBundle.getBundle(
                "MessagesBundle",
                locale
        );

        if (locale.getLanguage().equals("ar")) {
            root.setNodeOrientation(
                    javafx.geometry.NodeOrientation.RIGHT_TO_LEFT
            );
        } else {
            root.setNodeOrientation(
                    javafx.geometry.NodeOrientation.LEFT_TO_RIGHT
            );
        }

        unit1Label.setText(bundle.getString("unit1"));
        unit2Label.setText(bundle.getString("unit2"));

        valueLabel.setText(bundle.getString("value"));
        convertButton.setText(bundle.getString("convert"));
        historyLabel.setText(bundle.getString("history"));

        inputColumn.setText(bundle.getString("input"));
        resultColumn.setText(bundle.getString("result"));
        dateColumn.setText(bundle.getString("date"));

        primaryStage.setTitle(
                bundle.getString("title")
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}