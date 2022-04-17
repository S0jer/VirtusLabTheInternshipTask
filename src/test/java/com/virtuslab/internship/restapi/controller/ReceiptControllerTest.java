package com.virtuslab.internship.restapi.controller;

import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.restapi.service.ReceiptService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ReceiptControllerTest {

    @Test
    void shouldReturnCorrectReceipt() {
        // Given
        BasketInput basketInput = new BasketInput();
        basketInput.addProduct("Milk");
        basketInput.addProduct("Milk");
        basketInput.addProduct("Bread");
        basketInput.addProduct("Apple");
        ReceiptController controller = new ReceiptController(new ReceiptService());

        // When
        Receipt receipt = controller.receipt(basketInput);

        //Then
        assertEquals(0, receipt.discounts().size());
        assertEquals(new BigDecimal("12.4"), receipt.totalPrice());
    }

    @Test
    void shouldReturnCorrectReceiptWithTenPercentDiscount() {
        // Given
        BasketInput basketInput = new BasketInput();
        basketInput.addProduct("Milk");
        basketInput.addProduct("Milk");
        basketInput.addProduct("Bread");
        basketInput.addProduct("Apple");
        basketInput.addProduct("Pork");
        basketInput.addProduct("Pork");
        basketInput.addProduct("Pork");
        ReceiptController controller = new ReceiptController(new ReceiptService());

        // When
        Receipt receipt = controller.receipt(basketInput);

        //Then
        assertEquals(1, receipt.discounts().size());
        assertEquals(new BigDecimal("54.36"), receipt.totalPrice());
    }

    @Test
    void shouldReturnCorrectReceiptWithFifteenPercentDiscount() {
        // Given
        BasketInput basketInput = new BasketInput();
        basketInput.addProduct("Milk");
        basketInput.addProduct("Milk");
        basketInput.addProduct("Bread");
        basketInput.addProduct("Apple");
        basketInput.addProduct("Bread");
        basketInput.addProduct("Bread");
        basketInput.addProduct("Pork");
        ReceiptController controller = new ReceiptController(new ReceiptService());

        // When
        Receipt receipt = controller.receipt(basketInput);

        //Then
        assertEquals(1, receipt.discounts().size());
        assertEquals(new BigDecimal("32.64"), receipt.totalPrice());
    }

    @Test
    void shouldReturnCorrectReceiptWithFifteenAndTenPercentDiscount() {
        // Given
        BasketInput basketInput = new BasketInput();
        basketInput.addProduct("Milk");
        basketInput.addProduct("Milk");
        basketInput.addProduct("Bread");
        basketInput.addProduct("Apple");
        basketInput.addProduct("Bread");
        basketInput.addProduct("Bread");
        basketInput.addProduct("Pork");
        basketInput.addProduct("Pork");
        basketInput.addProduct("Steak");
        ReceiptController controller = new ReceiptController(new ReceiptService());

        // When
        Receipt receipt = controller.receipt(basketInput);

        //Then
        assertEquals(2, receipt.discounts().size());
        assertEquals(new BigDecimal("79.87"), receipt.totalPrice());
    }

    @Test
    void shouldReturnWrongArgumentExceptionInBasket() {
        // Given
        BasketInput basketInput = new BasketInput();
        basketInput.addProduct("Milk");
        basketInput.addProduct("Milk");
        basketInput.addProduct("Breat");
        basketInput.addProduct("Apple");
        ReceiptController controller = new ReceiptController(new ReceiptService());

        // When and Then
        Exception exception = assertThrows(ReceiptService.WrongArgumentExceptionInBasket.class, () -> controller.receipt(basketInput));
    }
}
