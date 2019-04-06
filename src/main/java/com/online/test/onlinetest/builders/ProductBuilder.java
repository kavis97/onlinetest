package com.online.test.onlinetest.builders;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.online.test.onlinetest.service.product.Product;
import com.online.test.onlinetest.service.rgb.RGBService;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;

public final class ProductBuilder {
    public static final String LABEL_SHOW_WAS_NOW = "ShowWasNow";
    public static final String LABEL_SHOW_WAS_THEN_NOW = "ShowWasThenNow";
    public static final String LABEL_SHOW_PERC_DSCOUNT = "ShowPercDiscount";

    private static final String GBP = "£";
    private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("#0.00");
    private static final DecimalFormat NO_DECIMAL_FORMATTER = new DecimalFormat("#0");


    private static final String DEFAULT_LABEL_TYPE = LABEL_SHOW_WAS_NOW;
    private static final String[] LABEL_TYPES = {DEFAULT_LABEL_TYPE, LABEL_SHOW_WAS_THEN_NOW, LABEL_SHOW_PERC_DSCOUNT};
    private static final List<String> VALID_LABEL_TYPES = Arrays.asList(LABEL_TYPES);

    private static final Function<double[], String> WASNOW = w -> "Was £" + w[0] + ", now £" + w[1];
    private static final Function<double[], String> WASTHENNOW = w -> "Was £" + w[0] + ", then £" + w[1] + ", now £" + w[2];
    private static final Function<String[], String> PERCDISC = w -> w[0] + "% off - now £" + w[1];

    private Product product;
    private JsonObject json;
    private String labelType;
    private Double now;

    public ProductBuilder(JsonObject json, String labelType) {
        this.json = json;
        this.product = new Product();
        this.labelType = VALID_LABEL_TYPES.contains(labelType) ? labelType : DEFAULT_LABEL_TYPE;
    }

    public Product build() {
        return setTitle()
                .setProductId()
                .setNowPrice()
                .setColorSwatches()
                .setPriceLabel()
                .get();
    }

    private ProductBuilder setPriceLabel() {

        JsonElement priceElement = json.get("price");
        if (priceElement == null)
            return this;

        JsonObject price = priceElement.getAsJsonObject();
        double was = price.get("was").getAsDouble();
        String label = "";
        switch (labelType) {
            case LABEL_SHOW_WAS_THEN_NOW:
                label = getWasThenNow(price, was);
                break;
            case LABEL_SHOW_PERC_DSCOUNT:
                label = getPercDisc(was);
                break;
            default:
                label = WASNOW.apply(new double[]{was, now});
        }

        product.setPriceLabel(label);
        return this;
    }

    private String getPercDisc(double was) {
        int percentage = (int) ((100 * (was - now)) / was);
        return PERCDISC.apply(new String[]{String.valueOf(percentage), String.valueOf(now)});
    }

    private String getWasThenNow(JsonObject price, double was) {
        JsonElement then = price.get("then2");
        if (then == null || "".equals(then.getAsString()))
            then = price.get("then1");

        double thenValue = then == null || "".equals(then.getAsString()) ? 0 : then.getAsDouble();
        return WASTHENNOW.apply(new double[]{was, thenValue, now});
    }

    private Product get() {
        return this.product;
    }

    private ProductBuilder setTitle() {
        product.setTitle(getValue("title"));
        return this;
    }

    private ProductBuilder setProductId() {
        product.setProductId(getValue("productId"));
        return this;
    }

    private String getValue(String label) {
        return Optional.ofNullable(json.get(label)).map(JsonElement::getAsString).orElse("");
    }

    private ProductBuilder setNowPrice() {
        Optional<JsonElement> element = Optional.ofNullable(json.get("price"));
        JsonElement element1 = element.map(e -> e.getAsJsonObject().get("now")).orElse(null);
        if (element1 != null) {
            if (!(element1 instanceof JsonPrimitive))
                element1 = ((JsonObject) element1).get("to");
            now = element1.getAsDouble();
            product.setNowPrice(new StringBuilder(GBP)
                    .append(now < 10 ? DECIMAL_FORMATTER.format(now) : NO_DECIMAL_FORMATTER.format(now))
                    .toString());
        }
        return this;
    }

    private ProductBuilder setColorSwatches() {
        JsonArray colorSwatches = json.getAsJsonArray("colorSwatches");

        if (colorSwatches != null) {
            List<Map<String, String>> result = new ArrayList<>(colorSwatches.size());
            for (int i = 0; i < colorSwatches.size(); i++) {
                Map<String, String> cs = new HashMap<>(3);
                JsonObject colorSwatch = colorSwatches.get(i).getAsJsonObject();
                cs.put("color", colorSwatch.get("color").getAsString());
                cs.put("rgbcolor", RGBService.getRgb(colorSwatch.get("basicColor").getAsString()));
                cs.put("skuId", colorSwatch.get("skuId").getAsString());
                result.add(cs);
            }

            product.setColorSwatches(result);
        }
        return this;
    }
}
