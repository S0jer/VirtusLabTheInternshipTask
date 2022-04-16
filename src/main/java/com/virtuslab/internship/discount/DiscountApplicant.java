package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

import java.util.ArrayList;
import java.util.List;

public class DiscountApplicant {
    private List<Discount> discounts;

    public DiscountApplicant() {
        this.discounts = new ArrayList<>();
        discounts.add(new TenPercentDiscount());
        discounts.add(new FifteenPercentDiscount());
    }

    public Receipt applyDiscount(Receipt receipt) {
        for (Discount d : discounts) {
            receipt = d.apply(receipt);
        }
        return receipt;
    }
}
