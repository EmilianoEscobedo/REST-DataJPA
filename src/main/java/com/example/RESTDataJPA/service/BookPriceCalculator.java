package com.example.RESTDataJPA.service;

import com.example.RESTDataJPA.models.Book;

public class BookPriceCalculator {

    public double calculatePrice(Book book){
        double price = book.getPrice();
        final double IVA = 0.21;
        return price+(price*IVA);
    }

}
