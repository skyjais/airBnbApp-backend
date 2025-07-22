package com.codingakash.projects.airBnbApp.stratagy;

import com.codingakash.projects.airBnbApp.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);

}
