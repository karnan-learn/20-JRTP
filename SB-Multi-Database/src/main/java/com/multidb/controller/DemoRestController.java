package com.multidb.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multidb.db1.entities.User;
import com.multidb.db1.repo.UserRepository;
import com.multidb.db2.entities.Book;
import com.multidb.db2.repo.BookRepository;

@RestController
public class DemoRestController {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/addData")
	public String addData2DB() {
		userRepository.saveAll(
				Stream.of(
						new User(744,"Johon","John@gmail.com"),
						new User(745,"Smith","sMITH@gmail.com")
						).collect(Collectors.toList())
				);
		bookRepository.saveAll(
				Stream.of(
						new Book(746,"Spring",15000.00,"Gosling"),
						new Book(747,"Java",25414.23,"James")
						).collect(Collectors.toList())
				);
		return "Data Added Successfully";
	}
	@GetMapping("/users")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	@GetMapping("/books")
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}
}
