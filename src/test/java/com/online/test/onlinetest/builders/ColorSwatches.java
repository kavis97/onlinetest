package com.online.test.onlinetest.builders;

public class ColorSwatches {

    String basicColor;
    String color;
    String skuId;

    public ColorSwatches(String color, String basicColor, String skuId) {
        this.basicColor = basicColor;
        this.color = color;
        this.skuId = skuId;
    }

    public String getBasicColor() {
        return basicColor;
    }

    public String getColor() {
        return color;
    }

    public String getSkuId() {
        return skuId;
    }
}
