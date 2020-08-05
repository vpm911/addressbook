package com.book.addressbook.rest;

import com.book.addressbook.entity.AddressBook;
import com.book.addressbook.service.AddressBookService;
import com.book.addressbook.service.BookEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    AddressBookService bookService;

    @Autowired
    BookEntryService entryService;

    /**
     * Get All Books by date created
     */
    @GetMapping("/book")
    public ResponseEntity<Object> getAllBooks(){
        List<AddressBook> books = bookService.getAllBooks();
        return ResponseEntity.ok().body(books);
    }

    @PostMapping("/book")
    public ResponseEntity<Object> createNewBook(@RequestParam String name){
        AddressBook book = bookService.createNewBook(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(book.getId());
    }

}
