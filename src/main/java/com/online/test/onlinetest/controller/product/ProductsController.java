package com.online.test.onlinetest.controller.product;

import com.online.test.onlinetest.service.product.Product;
import com.online.test.onlinetest.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/*")
    public List<Product> getProducts(@RequestParam(required = false) String labelType) {
        return productService.getProducts(labelType);
    }

}
