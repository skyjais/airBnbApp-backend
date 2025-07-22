package com.codingakash.projects.airBnbApp.stratagy;

import com.codingakash.projects.airBnbApp.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@RequiredArgsConstructor
public class HolidayPricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;


    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        boolean isTodayHoilday = true; //call an api or check with locale date

        if(isTodayHoilday){
            price = price.multiply(BigDecimal.valueOf(1.25));
        }

        return price;
    }
}
