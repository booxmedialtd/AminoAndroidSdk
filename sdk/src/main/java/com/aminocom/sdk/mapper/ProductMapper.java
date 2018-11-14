package com.aminocom.sdk.mapper;

import com.aminocom.sdk.model.client.Product;
import com.aminocom.sdk.model.network.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    private ProductMapper() {
    }

    static List<Product> from(List<ProductItem> items) {
        List<Product> result = new ArrayList<>();

        for (ProductItem item : items) {
            result.add(from(item));
        }

        return result;
    }

    private static Product from(ProductItem item) {
        Product result = new Product();

        result.setId(item.id);
        result.setTitle(item.title);

        return result;
    }
}