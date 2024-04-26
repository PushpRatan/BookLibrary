package com.example.bookManagementAssignment.Controller;

import com.example.bookManagementAssignment.Dto.Response.AddAuthorResponse;
import com.example.bookManagementAssignment.Dto.Response.GetBooksByAuthorResponse;
import com.example.bookManagementAssignment.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestParam String name, @RequestParam String biography){
        try {
            AddAuthorResponse response = authorService.addAuthor(name, biography);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity getAuthor(@RequestParam Long id){
        try {
            AddAuthorResponse response = authorService.getAuthor(id);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBooksByAuthor")
    public ResponseEntity getBooksByAuthor(@RequestParam Long id){
        try {
            GetBooksByAuthorResponse response = authorService.getBooksByAuthor(id);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/updateAuthorBio")
    public ResponseEntity updateAuthor(@RequestParam Long id, @RequestParam String biography){
        try {
            AddAuthorResponse response = authorService.updateAuthorBio(id, biography);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity deleteAuthor(@RequestParam Long id){
        try {
            String response = authorService.deleteAuthor(id);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
