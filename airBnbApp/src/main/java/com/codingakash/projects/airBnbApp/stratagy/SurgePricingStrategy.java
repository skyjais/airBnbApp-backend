package com.codingakash.projects.airBnbApp.stratagy;

import com.codingakash.projects.airBnbApp.entity.Inventory;
import lombok.RequiredArgsConstructor;


import java.math.BigDecimal;


@RequiredArgsConstructor
public class SurgePricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        return price.multiply(inventory.getSurgeFactor());
    }
}
