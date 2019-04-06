package com.online.test.onlinetest.builders;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class JsonObjectBuilder {

    public static final String PRODUCT_ID = "3525081";
    public static final String PRODUCT_TITLE = "hush Marble Panel Maxi Dress, Multi";

    public static JsonObject getProductIdOnlyJsonObject() {
        JsonProduct p = new JsonProduct();
        p.productId = PRODUCT_ID;
        Gson g = new Gson();
        String s = g.toJson(p);
        return new JsonParser().parse(s).getAsJsonObject();
    }

    public static JsonObject getJsonObject() {
        Gson g = new Gson();
        String s = g.toJson(get1());
        return new JsonParser().parse(s).getAsJsonObject();
    }

    private static JsonProduct get1() {
        JsonProduct p = new JsonProduct();

        p.colorSwatches = new ArrayList<>();
        ColorSwatches cs1 = new ColorSwatches("Dark Grey", "Grey", "237169362");
        ColorSwatches cs2 = new ColorSwatches("Khaki", "Green", "237473294");
        p.colorSwatches.add(cs1);
        p.colorSwatches.add(cs2);

        p.productId = PRODUCT_ID;
        p.title = PRODUCT_TITLE;

        Price price = new Price("99", "90", "92", "85");
        p.price = price;
        return p;
    }

    public static JsonObject getProductsJson() {
        JsonProducts products = new JsonProducts();

        JsonProduct p1 = get1();
        JsonProduct p2 = get2();
        JsonProduct[] ps = new JsonProduct[2];
        ps[0] = p1;
        ps[1] = p2;

        products.setProducts(ps);
        Gson g = new Gson();
        String s = g.toJson(products);
        return new JsonParser().parse(s).getAsJsonObject();
    }

    public static JsonProduct get2() {
        JsonProduct p = new JsonProduct();

        p.colorSwatches = new ArrayList<>();
        ColorSwatches cs1 = new ColorSwatches("Super Red", "Red", "2300169362");
        ColorSwatches cs2 = new ColorSwatches("Khaki", "Green", "2379973294");
        p.colorSwatches.add(cs1);
        p.colorSwatches.add(cs2);

        p.productId = PRODUCT_ID;
        p.title = PRODUCT_TITLE;

        Price price = new Price("100", "910", "922", "75");
        p.price = price;

        return p;
    }
}
