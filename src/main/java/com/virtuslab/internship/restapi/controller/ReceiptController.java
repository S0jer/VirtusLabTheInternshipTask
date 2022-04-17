package com.virtuslab.internship.restapi.controller;


import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.restapi.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
class ReceiptController {
    private final ReceiptService receiptGenerator;

    @Autowired
    public ReceiptController(ReceiptService receiptGenerator) {
        this.receiptGenerator = receiptGenerator;
    }

    @PutMapping("/receipt")
    public Receipt receipt(@RequestBody BasketInput basket) {
        return receiptGenerator.getReceipt(basket);
    }
}
