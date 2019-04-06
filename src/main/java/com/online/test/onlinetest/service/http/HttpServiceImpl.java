package com.online.test.onlinetest.service.http;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
final class HttpServiceImpl implements HttpService {

    private static final String URL_STRING = "https://jl-nonprod-syst.apigee.net/v1/categories/600001506/products?key=2ALHCAAs6ikGRBoy6eTHA58RaG097Fma";

    @Override
    public JsonObject getJson() {
        JsonObject obj = null;
        try {
            URL url = new URL(URL_STRING);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoOutput(true);

            InputStream is = con.getInputStream();
            obj = (JsonObject) new JsonParser().parse(new JsonReader(new InputStreamReader(is)));
            is.close();
            con.disconnect();
        } catch (Exception e) {

        }

        return obj;
    }
}
