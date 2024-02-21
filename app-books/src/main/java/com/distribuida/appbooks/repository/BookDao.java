package com.distribuida.appbooks.repository;

import com.distribuida.appbooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {
}
