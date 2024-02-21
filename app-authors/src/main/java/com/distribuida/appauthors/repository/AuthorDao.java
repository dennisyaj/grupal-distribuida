package com.distribuida.appauthors.repository;

import com.distribuida.appauthors.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorDao extends CrudRepository<Author, Integer> {
}
