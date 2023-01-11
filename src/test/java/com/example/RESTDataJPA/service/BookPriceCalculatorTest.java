package com.example.RESTDataJPA.service;

import com.example.RESTDataJPA.models.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePriceTest() {
        Book book = new Book(1L, "LOTR", "Tolkien", 500, 100L, LocalDate.now(),true);

        BookPriceCalculator calculator = new BookPriceCalculator();
        double price = calculator.calculatePrice(book);
        System.out.println(price);
        assertTrue(price > 0);
        assertEquals(121.0, price);



    }
}