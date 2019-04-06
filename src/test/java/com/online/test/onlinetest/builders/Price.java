package com.online.test.onlinetest.builders;

public class Price {

    String was;
    String then1;
    String then2;
    String now;

    public Price(String was, String then1, String then2, String now) {
        this.was = was;
        this.then1 = then1;
        this.then2 = then2;
        this.now = now;
    }

    public String getWas() {
        return was;
    }

    public String getThen1() {
        return then1;
    }

    public String getThen2() {
        return then2;
    }

    public String getNow() {
        return now;
    }
}
