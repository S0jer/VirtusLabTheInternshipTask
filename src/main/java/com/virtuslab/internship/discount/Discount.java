package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

public interface Discount {

    boolean shouldApply(Receipt receipt);

    Receipt apply(Receipt receipt);
}
