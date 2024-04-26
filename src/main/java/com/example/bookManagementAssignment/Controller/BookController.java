package com.example.bookManagementAssignment.Controller;

import com.example.bookManagementAssignment.Dto.Request.AddBookRequest;
import com.example.bookManagementAssignment.Dto.Response.AddAuthorResponse;
import com.example.bookManagementAssignment.Dto.Response.AddBookResponse;
import com.example.bookManagementAssignment.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody AddBookRequest addBookRequest){
        try {
            AddBookResponse response = bookService.addBook(addBookRequest);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get")
    public ResponseEntity getBook(@RequestParam Long id){
        try {
            AddBookResponse response = bookService.getBook(id);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/updateBookPublicationYear")
    public ResponseEntity updateBookPublicationYear(@RequestParam Long id, @RequestParam String publicationYear){
        try {
            AddBookResponse response = bookService.updateBookPublicationYear(id, publicationYear);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteBook(@RequestParam Long id){
        try{
            String response = bookService.deleteBook(id);
            return  new ResponseEntity(response, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
