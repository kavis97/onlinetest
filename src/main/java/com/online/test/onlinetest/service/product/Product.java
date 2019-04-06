package com.online.test.onlinetest.service.product;

import java.util.List;
import java.util.Map;

public class Product {

    private String productId;
    private String title;
    private List<Map<String, String>> colorSwatches;
    private String nowPrice;
    private String priceLabel;

    public String getProductId() {
        return productId;
    }

    public Product setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Product setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<Map<String, String>> getColorSwatches() {
        return colorSwatches;
    }

    public Product setColorSwatches(List<Map<String, String>> colorSwatches) {
        this.colorSwatches = colorSwatches;
        return this;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public Product setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
        return this;
    }

    public String getPriceLabel() {
        return priceLabel;
    }

    public Product setPriceLabel(String priceLabel) {
        this.priceLabel = priceLabel;
        return this;
    }
}
