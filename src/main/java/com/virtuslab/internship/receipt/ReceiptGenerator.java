package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        Map<Product, Long> uniqueProducts = basket.getProducts().stream().collect(Collectors.groupingBy(Function.identity(), counting()));
        for (Product product : uniqueProducts.keySet()) {
            receiptEntries.add(new ReceiptEntry(product, Math.toIntExact(uniqueProducts.get(product))));
        }
        return new Receipt(receiptEntries);
    }
}
