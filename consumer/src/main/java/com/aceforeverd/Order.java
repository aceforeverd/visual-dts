package com.aceforeverd;

import org.bson.Document;

public class Order {
    private Currency src;

    private Currency dst;

    private double value;

    private String time;

    private String date;

    public Order(Currency src, Currency dst, double value, String time) {
        this.src = src;
        this.dst = dst;
        this.value = value;
        this.time = time;
        this.date = time.substring(16);
    }

    public Order(Document doc) {
        this.src = Currency.getCurrency(doc.getString("src_name"));
        this.dst = Currency.getCurrency(doc.getString("dst_name"));
        this.value = doc.getDouble("value");
        this.time = doc.get("time", String.class);
        this.date = time.substring(16);
    }

    public Currency getSrc() {
        return src;
    }

    public void setSrc(Currency src) {
        this.src = src;
    }

    public Currency getDst() {
        return dst;
    }

    public void setDst(Currency dst) {
        this.dst = dst;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
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
                "src_name: " + src.getName() +
                ", dst_name: " + dst.getName() +
                ", value: " + value +
                ", time: " + time +
                '}';
    }
}
