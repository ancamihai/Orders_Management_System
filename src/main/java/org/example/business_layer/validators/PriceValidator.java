package org.example.business_layer.validators;

import org.example.model.Product;

/**
 * checks if the integer introduced as the price for a product is greater than 0
 */
public class PriceValidator {

    public static void validate(Product t) {
        if (t.getPrice() <= 0) {
            throw new IllegalArgumentException("The price of the product should be greater than 0!");
        }
    }
}
