package com.virtuslab.internship.restapi.controller;


import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.restapi.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
class Controller {
    private final Service receiptGenerator;

    @Autowired
    public Controller(Service receiptGenerator) {
        this.receiptGenerator = receiptGenerator;
    }

    @PutMapping("/receipt")
    public Receipt receipt(@RequestBody BasketInput basket) {
        return receiptGenerator.getReceipt(basket);
    }
}
