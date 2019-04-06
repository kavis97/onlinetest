package com.online.test.onlinetest.service.rgb;

import java.util.HashMap;
import java.util.Map;

public final class RGBService {
    private static final Map<String, String> RGB_MAP = new HashMap<>();

    static {
        RGB_MAP.put("red", "#FF0000");
        RGB_MAP.put("blue", "#0000FF");
        RGB_MAP.put("black", "#000000");
        RGB_MAP.put("white", "#FFFFFF");
        RGB_MAP.put("pink", "#FFC0CB");
        RGB_MAP.put("green", "#00FF00");
        RGB_MAP.put("grey", "#808080");
        RGB_MAP.put("multi", "multi");
        RGB_MAP.put("orange", "#FFA500");
        RGB_MAP.put("purple", "#800080");
        RGB_MAP.put("yellow", "#FFFF00");
    }

    public static String getRgb(String basicColor) {
        return RGB_MAP.get(basicColor.toLowerCase());
    }
}
