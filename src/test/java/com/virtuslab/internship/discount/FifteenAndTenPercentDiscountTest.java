package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FifteenAndTenPercentDiscountTest {

    @Test
    void shouldApply15PercentDiscountWhenContain3GrainProductsAndNot10PercentAfter() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var pork = productDb.getProduct("Pork");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 4));
        receiptEntries.add(new ReceiptEntry(pork, 2));

        var receipt = new Receipt(receiptEntries);
        var discountFifteen = new FifteenPercentDiscount();
        var discountTen = new TenPercentDiscount();

        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(4)).add(pork.price().multiply(BigDecimal.valueOf(2))).multiply(BigDecimal.valueOf(0.85));

        // When
        var receiptAfterDiscount = discountFifteen.apply(receipt);
        var finalReceipt = discountTen.apply(receiptAfterDiscount);

        // Then
        assertEquals(expectedTotalPrice, finalReceipt.totalPrice());
        assertEquals(1, finalReceipt.discounts().size());
    }

    @Test
    void shouldApply15PercentDiscountWhenContain3GrainProductsAnd10PercentAfter() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var pork = productDb.getProduct("Pork");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 4));
        receiptEntries.add(new ReceiptEntry(pork, 3));

        var receipt = new Receipt(receiptEntries);
        var discountFifteen = new FifteenPercentDiscount();
        var discountTen = new TenPercentDiscount();

        var expectedTotalPrice = (bread.price().multiply(BigDecimal.valueOf(4)).add(pork.price().multiply(BigDecimal.valueOf(3))).multiply(BigDecimal.valueOf(0.85))).multiply(BigDecimal.valueOf(0.9));

        // When
        var receiptAfterDiscountFifteen = discountFifteen.apply(receipt);
        var finalReceipt = discountTen.apply(receiptAfterDiscountFifteen);

        // Then
        assertEquals(expectedTotalPrice, finalReceipt.totalPrice());
        assertEquals(2, finalReceipt.discounts().size());
    }


    @Test
    void shouldNotApply15PercentDiscountAnd10PercentAfter() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var pork = productDb.getProduct("Pork");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 2));
        receiptEntries.add(new ReceiptEntry(pork, 1));

        var receipt = new Receipt(receiptEntries);
        var discountFifteen = new FifteenPercentDiscount();
        var discountTen = new TenPercentDiscount();

        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(2)).add(pork.price().multiply(BigDecimal.valueOf(1)));

        // When
        var receiptAfterDiscount = discountFifteen.apply(receipt);
        var finalReceipt = discountTen.apply(receiptAfterDiscount);

        // Then
        assertEquals(expectedTotalPrice, finalReceipt.totalPrice());
        assertEquals(0, finalReceipt.discounts().size());
    }


    @Test
    void shouldNotApply15PercentDiscountButApply10PercentAfter() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var pork = productDb.getProduct("Pork");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 2));
        receiptEntries.add(new ReceiptEntry(pork, 3));

        var receipt = new Receipt(receiptEntries);
        var discountFifteen = new FifteenPercentDiscount();
        var discountTen = new TenPercentDiscount();

        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(2)).add(pork.price().multiply(BigDecimal.valueOf(3))).multiply(BigDecimal.valueOf(0.9));

        // When
        var receiptAfterDiscount = discountFifteen.apply(receipt);
        var finalReceipt = discountTen.apply(receiptAfterDiscount);

        // Then
        assertEquals(expectedTotalPrice, finalReceipt.totalPrice());
        assertEquals(1, finalReceipt.discounts().size());
    }


}
