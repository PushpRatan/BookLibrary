package com.example.bookManagementAssignment.Service;

import com.example.bookManagementAssignment.Dto.Response.AddAuthorResponse;
import com.example.bookManagementAssignment.Dto.Response.GetBooksByAuthorResponse;
import com.example.bookManagementAssignment.Exception.NotFoundException;
import com.example.bookManagementAssignment.Model.Author;
import com.example.bookManagementAssignment.Model.Book;
import com.example.bookManagementAssignment.Repository.AuthorRepository;
import com.example.bookManagementAssignment.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;
    public AddAuthorResponse addAuthor(String name, String biography) {
        Author author = new Author();
        author.setName(name);
        author.setBiography(biography);

        Author savedAuthor = authorRepository.save(author);

        AddAuthorResponse addAuthorResponse = new AddAuthorResponse();
        addAuthorResponse.setName(savedAuthor.getName());
        addAuthorResponse.setBiography(savedAuthor.getBiography());

        return addAuthorResponse;
    }

    public AddAuthorResponse getAuthor(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(!optionalAuthor.isPresent()){
            throw new NotFoundException("Author doesn't exist");
        }

        Author author = optionalAuthor.get();

        AddAuthorResponse addAuthorResponse = new AddAuthorResponse();
        addAuthorResponse.setName(author.getName());
        addAuthorResponse.setBiography(author.getBiography());

        return addAuthorResponse;
    }

    public AddAuthorResponse updateAuthorBio(Long id, String biography) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(!optionalAuthor.isPresent()){
            throw new NotFoundException("Author doesn't exist");
        }

        Author author = optionalAuthor.get();
        author.setBiography(biography);

        authorRepository.save(author);

        AddAuthorResponse addAuthorResponse = new AddAuthorResponse();
        addAuthorResponse.setName(author.getName());
        addAuthorResponse.setBiography(author.getBiography());

        return addAuthorResponse;
    }

    public GetBooksByAuthorResponse getBooksByAuthor(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(!optionalAuthor.isPresent()){
            throw new NotFoundException("Author doesn't exist");
        }

        Author author = optionalAuthor.get();

        GetBooksByAuthorResponse getBooksByAuthorResponse = new GetBooksByAuthorResponse();
        List<String> bookNames = author.getBooks().stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
        getBooksByAuthorResponse.setBooks(bookNames);
        return getBooksByAuthorResponse;
    }

    public String deleteAuthor(Long id) {
            Optional<Author> optionalAuthor = authorRepository.findById(id);
            if (!optionalAuthor.isPresent()) {
                throw new NotFoundException("Author does not exist.");
            }

            // Delete books by the author
            Author author = optionalAuthor.get();
            bookRepository.deleteAll(author.getBooks());

            // Delete the author
            authorRepository.delete(author);
            return "Author with id " + id + " deleted";
    }
}
