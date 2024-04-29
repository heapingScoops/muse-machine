package com.techelevator.tenmo.model;

public class SummaryDto {

    private String preamble = "in 150 words, summarize the following: ";
    private double temperature = 0.9;
    //private String additionalCommand = "that highlights the most evocative phrases. At the end, pick a surprising art style for the content.";
    private String message = "";

    public String getPreamble() {
        return preamble;
    }

    public void setPreamble(String preamble) {
        this.preamble = preamble;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String text) {
        this.message = text;
    }
}
