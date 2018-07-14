package com.aceforeverd;


public class ResultItem {
    private Currency name;

    private double income;

    private double expend;

    private String time;

    public ResultItem(Currency name, double income, double expend, String time) {
        this.name = name;
        this.income = income;
        this.expend = expend;
        this.time = time;
    }

    public ResultItem(Currency n, String t) {
        this.name = n;
        this.income = 0.0;
        this.expend = 0.0;
        this.time = t;
    }

    public Currency getName() {
        return name;
    }

    public void setName(Currency name) {
        this.name = name;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpend() {
        return expend;
    }

    public void setExpend(double expend) {
        this.expend = expend;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "{" +
                "name:" + name +
                ", income:" + income +
                ", expend:" + expend +
                ", time:" + time +
                '}';
    }

}
