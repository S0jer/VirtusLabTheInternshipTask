package com.virtuslab.internship.restapi.service;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.DiscountApplicant;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import com.virtuslab.internship.restapi.controller.BasketInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@org.springframework.stereotype.Service
public class ReceiptService {
    private final ReceiptGenerator receiptGenerator;
    private final ProductDb productDb;
    private final DiscountApplicant discountApplicant;

    @Autowired
    public ReceiptService() {
        this.discountApplicant = new DiscountApplicant();
        this.productDb = new ProductDb();
        this.receiptGenerator = new ReceiptGenerator();
    }

    public Receipt getReceipt(BasketInput basketInput) {
        Basket basket = makeBasket(basketInput.getProducts());
        Receipt receipt = receiptGenerator.generate(basket);
        Receipt receiptAfterDiscount = discountApplicant.applyDiscount(receipt);
        return receiptAfterDiscount;
    }

    public Basket makeBasket(List<String> products) {
        Basket basket = new Basket();
        for (String p : products) {
            basket.addProduct(productDb.getProduct(p));
        }
        return basket;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public static class WrongArgumentExceptionInBasket extends RuntimeException {
        public WrongArgumentExceptionInBasket(String message) {
            super(message);
        }
    }
}
