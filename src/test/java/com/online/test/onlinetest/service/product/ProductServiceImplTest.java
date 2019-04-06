package com.online.test.onlinetest.service.product;

import com.online.test.onlinetest.builders.JsonObjectBuilder;
import com.online.test.onlinetest.builders.ProductBuilder;
import com.online.test.onlinetest.service.http.HttpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    HttpService mockHttpService;

    @Test
    public void givenNullJsonWhenGetProductsThenEmptyList() {
        Mockito.when(mockHttpService.getJson()).thenReturn(null);
        ProductService s = new ProductServiceImpl(mockHttpService);
        List<Product> products = s.getProducts(null);
        assertNotNull(products);
        assertEquals(0, products.size());
    }
    @Test
    public void givenJsonWhenGetProductsThenList() {
        Mockito.when(mockHttpService.getJson()).thenReturn(JsonObjectBuilder.getProductsJson());
        ProductService s = new ProductServiceImpl(mockHttpService);
        List<Product> products = s.getProducts(null);
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Was £99.0, now £85.0", products.get(0).getPriceLabel());
        assertEquals("Was £100.0, now £75.0", products.get(1).getPriceLabel());

    }
    @Test
    public void givenJsonWhenGetProductsForShowWhenThenThenList() {
        Mockito.when(mockHttpService.getJson()).thenReturn(JsonObjectBuilder.getProductsJson());
        ProductService s = new ProductServiceImpl(mockHttpService);
        List<Product> products = s.getProducts(ProductBuilder.LABEL_SHOW_WAS_THEN_NOW);
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Was £99.0, then £92.0, now £85.0", products.get(0).getPriceLabel());
        assertEquals("Was £100.0, then £922.0, now £75.0", products.get(1).getPriceLabel());

    }
    @Test
    public void givenJsonWhenGetProductsShowPercDiscThenList() {
        Mockito.when(mockHttpService.getJson()).thenReturn(JsonObjectBuilder.getProductsJson());
        ProductService s = new ProductServiceImpl(mockHttpService);
        List<Product> products = s.getProducts(ProductBuilder.LABEL_SHOW_PERC_DSCOUNT);
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("14% off - now £85.0", products.get(0).getPriceLabel());
        assertEquals("25% off - now £75.0", products.get(1).getPriceLabel());

    }
}