package com.aceforeverd.kafkaProducer.model;


public class Order {
    private String src;

    private String dst;

    private double value;

    private String time;

    public Order() {}

    public Order(String src, String dst, double value, String time) {
        this.src = src;
        this.dst = dst;
        this.value = value;
        this.time = time;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
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

    @Override
    public String toString() {
        return "Order{" +
                "src='" + src + '\'' +
                ", dst='" + dst + '\'' +
                ", value=" + value +
                ", time='" + time + '\'' +
                '}';
    }
}
