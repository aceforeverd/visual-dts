package com.aceforeverd;

import org.bson.Document;

import java.util.HashMap;

public class Results {
    private String time;
    private HashMap<Currency, ResultItem> resultMap = new HashMap<>();

    Results(String t) {
        time = t;
        resultMap.put(Currency.RMB, new ResultItem(Currency.RMB, t));
        resultMap.put(Currency.USD, new ResultItem(Currency.USD, t));
        resultMap.put(Currency.JPY, new ResultItem(Currency.JPY, t));
        resultMap.put(Currency.EUR, new ResultItem(Currency.EUR, t));
    }

    public void add(Order order) {
        if (order.getTime().equals(time)) {
            ResultItem item1 = resultMap.get(order.getSrc());
            item1.setExpend(item1.getExpend() + order.getValue());
            resultMap.replace(order.getSrc(), item1);

            ResultItem item2 = resultMap.get(order.getDst());
            item2.setIncome(item2.getIncome()
                    + CurrencyRate.convert(order.getValue(), order.getDst(), order.getSrc(), order.getTime()) );
            resultMap.replace(order.getDst(), item2);
        }
    }

    @Override
    public String toString() {
        String ret =  "{Results: [";
        for (int i = 0 ; i < 4; i++) {
            ret = ret.concat(resultMap.get(Currency.RMB).toString()) + ",";
            ret = ret.concat(resultMap.get(Currency.USD).toString()) + ",";
            ret = ret.concat(resultMap.get(Currency.JPY).toString()) + ",";
            ret = ret.concat(resultMap.get(Currency.EUR).toString());
        }
        ret += "]}";
        return ret;
    }

    public Document toDocument() {
        return Document.parse(toString());
    }
}
