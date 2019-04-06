package com.online.test.onlinetest.builders;

import com.online.test.onlinetest.service.product.Product;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductBuilderTest {

    @Test
    public void givenOnlyProductId() {
        Product product = new ProductBuilder(JsonObjectBuilder.getProductIdOnlyJsonObject(), "").build();
        assertEquals(JsonObjectBuilder.PRODUCT_ID, product.getProductId());
    }

    @Test
    public void validBuild() {
        Product product = new ProductBuilder(JsonObjectBuilder.getJsonObject(), "").build();
        assertNotNull(product);
        assertEquals(JsonObjectBuilder.PRODUCT_ID, product.getProductId());
        assertEquals(2, product.getColorSwatches().size());
        assertEquals("#808080", product.getColorSwatches().get(0).get("rgbcolor"));
        assertEquals("Was £99.0, now £85.0", product.getPriceLabel());
    }

}