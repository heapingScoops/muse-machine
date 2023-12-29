package com.techelevator.tenmo.model;

public class SummaryDto {

    private String length = "medium";
    private String format = "paragraph";
    private String extractiveness = "high";
    private double temperature = 2.0;
    //private String additionalCommand = "that highlights the most evocative phrases. At the end, pick a surprising art style for the content.";
    private String text = "";

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getExtractiveness() {
        return extractiveness;
    }

    public void setExtractiveness(String extractiveness) {
        this.extractiveness = extractiveness;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
