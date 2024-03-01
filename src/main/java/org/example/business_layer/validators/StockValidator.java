package org.example.business_layer.validators;

import org.example.model.Product;

/**
 * checks if the integer introduced as the stock for a product is equal to or greater than 0
 */
public class StockValidator {

    public static void validate(Product t) {
        if (t.getStock() < 0) {
            throw new IllegalArgumentException("Under stock!");
        }
    }
}
