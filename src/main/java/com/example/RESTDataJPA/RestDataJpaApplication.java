package com.example.RESTDataJPA;

import com.example.RESTDataJPA.models.Book;
import com.example.RESTDataJPA.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class RestDataJpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RestDataJpaApplication.class, args);
		BookRepository repo = context.getBean(BookRepository.class);

		Book book1 = new Book (null, "HP 7", "JK. Rowling", 480, 2300.00, LocalDate.of(2018,12,1), true);
		Book book2 = new Book (null, "HP 6", "JK. Rowling", 520, 1900.00, LocalDate.of(2016,12,1), true);

		repo.save(book1);
		repo.save(book2);

		System.out.println("Los Libros que hay en la bd son: " + repo.findAll());
	}

}
