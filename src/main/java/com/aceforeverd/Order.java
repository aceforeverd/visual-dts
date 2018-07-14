package com.aceforeverd;

import org.bson.Document;

public class Order {
    private Currency initator;

    private Currency recipient;

    private double turnover;

    private String time;

    private String date;

    public Order(Currency initator, Currency recipient, double turnover, String time) {
        this.initator = initator;
        this.recipient = recipient;
        this.turnover = turnover;
        this.time = time;
        this.date = time.substring(16);
    }

    public Order(Document doc) {
        this.initator = Currency.getCurrency(doc.getString("src_name"));
        this.recipient = Currency.getCurrency(doc.getString("dst_name"));
        this.turnover = doc.getDouble("value");
        this.time = doc.get("time", String.class);
    }

    public Currency getInitator() {
        return initator;
    }

    public void setInitator(Currency initator) {
        this.initator = initator;
    }

    public Currency getRecipient() {
        return recipient;
    }

    public void setRecipient(Currency recipient) {
        this.recipient = recipient;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "initator: " + initator +
                ", recipient: " + recipient +
                ", turnover: " + turnover +
                ", time: " + time +
                '}';
    }
}
