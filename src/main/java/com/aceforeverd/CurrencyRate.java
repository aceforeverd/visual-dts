package com.aceforeverd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CurrencyRate {
    private static final String startTime = "2018-01-01 00:00";

    private static final HashMap<Currency, Double> rates = new HashMap<Currency, Double>();
    static {
        rates.put(Currency.RMB, 2.0);
        rates.put(Currency.USD, 12.0);
        rates.put(Currency.JPY, 0.5);
        rates.put(Currency.EUR, 6.0);
    }

    public static double CurrencyRate(Currency c, String time) {
        return 0.1 * CurrencyRate.sub(time, startTime) + rates.get(c);
    }

    public static long sub(String a, String b) {
        if (a.length() != b.length()) {
            return 0;
        }
        return CurrencyRate.toMins(a) - CurrencyRate.toMins(b);
    }

    public static long toMins(String t) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date date = df.parse(t);
            return date.getTime() / (1000 * 60);
        } catch (Exception e) {
            System.out.printf("invalid time format");
            return 0;
        }
    }
}
