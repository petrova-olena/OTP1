package com.example.model;

import java.util.Locale;

public class TemperatureUnit {

    private int id;

    private String nameEn;
    private String nameFi;
    private String nameSv;
    private String nameJa;
    private String nameAr;

    private String symbol;

    public TemperatureUnit() {
    }

    public TemperatureUnit(
            int id,
            String nameEn,
            String nameFi,
            String nameSv,
            String nameJa,
            String nameAr,
            String symbol
    ) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameFi = nameFi;
        this.nameSv = nameSv;
        this.nameJa = nameJa;
        this.nameAr = nameAr;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameFi() {
        return nameFi;
    }

    public void setNameFi(String nameFi) {
        this.nameFi = nameFi;
    }

    public String getNameSv() {
        return nameSv;
    }

    public void setNameSv(String nameSv) {
        this.nameSv = nameSv;
    }

    public String getNameJa() {
        return nameJa;
    }

    public void setNameJa(String nameJa) {
        this.nameJa = nameJa;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getLocalizedName(Locale locale) {
        return switch (locale.getLanguage()) {
            case "fi" -> nameFi;
            case "sv" -> nameSv;
            case "ja" -> nameJa;
            case "ar" -> nameAr;
            default -> nameEn;
        };
    }

    @Override
    public String toString() {
        return nameEn;
    }
}