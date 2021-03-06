package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TenPercentDiscount implements Discount {

    private static final String NAME = "TenPercentDiscount";

    @Override
    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.9)).setScale(2, RoundingMode.CEILING);
            var discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    @Override
    public boolean shouldApply(Receipt receipt) {
        return receipt.totalPrice().compareTo(BigDecimal.valueOf(50)) >= 0;
    }
}
