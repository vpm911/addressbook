package com.book.addressbook.rest;

import com.book.addressbook.dto.EntryDto;
import com.book.addressbook.dto.Response;
import com.book.addressbook.entity.AddressBook;
import com.book.addressbook.service.AddressBookService;
import com.book.addressbook.service.BookEntryService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Data
@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    AddressBookService bookService;

    @Autowired
    BookEntryService entryService;

    // All books
    @GetMapping("/book")
    public ResponseEntity<Response> getAllBooks(){
        List<AddressBook> books = bookService.getAllBooks();
        Response response = new Response("OK",books);
        return ResponseEntity.ok().body(response);
    }

    // Book by id
    @GetMapping("/book/{id}")
    public ResponseEntity<Response> getBookById(@PathVariable  int id){
        AddressBook book =  bookService.getBookById(id);
        Response response = new Response("OK", book);
        return ResponseEntity.ok().body(response);
    }

    // create new book for name
    @PostMapping("/book")
    public ResponseEntity<Object> createNewBook(@RequestParam String name){
        AddressBook book = bookService.createNewBook(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(book.getId());
    }

    // add entry to book
    @PostMapping("/book/{id}/entry")
    public ResponseEntity<Response> addEntryToBook(@PathVariable int id, @RequestBody @Valid EntryDto dto){
        bookService.addEntryToBook(id,dto);
        return ResponseEntity.ok().build();
    }

}
