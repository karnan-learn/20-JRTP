package com.multidb.db2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multidb.db2.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
