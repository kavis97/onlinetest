package com.online.test.onlinetest.service.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.online.test.onlinetest.builders.ProductBuilder;
import com.online.test.onlinetest.service.http.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
final class ProductServiceImpl implements ProductService {

    private static final Predicate<String> IS_EMPTY = s -> s == null || s.isEmpty();

    private HttpService httpService;

    @Autowired
    public ProductServiceImpl(HttpService httpService) {
        this.httpService = httpService;
    }

    @Override
    public List<Product> getProducts(String labelType) {

        Optional<JsonObject> jsonObject = Optional.ofNullable(httpService.getJson());

        Optional<JsonArray> arr = jsonObject.isPresent() ?
                Optional.ofNullable(jsonObject.get().getAsJsonArray("products")) : Optional.empty();

        return arr.isPresent() ?
                IntStream.range(0, arr.get().size())
                .mapToObj(i -> arr.get().get(i).getAsJsonObject())
                .filter(j -> includeProduct(j))
                .map(j -> new ProductBuilder(j, labelType).build())
                .collect(Collectors.toList()) : Collections.emptyList();
    }

    private boolean includeProduct(JsonObject product) {
        return !IS_EMPTY.test(product.get("price").getAsJsonObject().get("was").getAsString());
    }
}
