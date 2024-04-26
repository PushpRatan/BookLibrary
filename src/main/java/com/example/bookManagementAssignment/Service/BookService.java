package com.example.bookManagementAssignment.Service;

import com.example.bookManagementAssignment.Dto.Request.AddBookRequest;
import com.example.bookManagementAssignment.Dto.Response.AddAuthorResponse;
import com.example.bookManagementAssignment.Dto.Response.AddBookResponse;
import com.example.bookManagementAssignment.Exception.NotFoundException;
import com.example.bookManagementAssignment.Model.Author;
import com.example.bookManagementAssignment.Model.Book;
import com.example.bookManagementAssignment.Repository.AuthorRepository;
import com.example.bookManagementAssignment.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public AddBookResponse addBook(AddBookRequest addBookRequest) {
        Optional<Author> optionalAuthor = authorRepository.findById(addBookRequest.getAuthorId());
        if(!optionalAuthor.isPresent()){
            throw new NotFoundException("Author doesn't exist");
        }
        Author author = optionalAuthor.get();
        Book book = new Book();
        book.setTitle(addBookRequest.getTitle());
        book.setIsbn(addBookRequest.getIsbn());
        book.setPublicationYear(addBookRequest.getPublicationYear());
        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);

        author.getBooks().add(book);
        authorRepository.save(author);

        AddBookResponse addBookResponse = new AddBookResponse();
        addBookResponse.setAuthorName(author.getName());
        addBookResponse.setIsbn(savedBook.getIsbn());
        addBookResponse.setTitle(savedBook.getTitle());
        addBookResponse.setPublicationYear(savedBook.getPublicationYear());

        return addBookResponse;
    }

    public AddBookResponse getBook(Long id) {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if(!optionalBook.isPresent()){
                throw new NotFoundException("Book not found");
            }

            Book book = optionalBook.get();

            AddBookResponse addBookResponse = new AddBookResponse();
            addBookResponse.setTitle(book.getTitle());
            addBookResponse.setIsbn(book.getIsbn());
            addBookResponse.setAuthorName(book.getAuthor().getName());
            addBookResponse.setPublicationYear(book.getPublicationYear());

            return addBookResponse;
    }

    public AddBookResponse updateBookPublicationYear(Long id, String publicationYear) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent()){
            throw new NotFoundException("Book not found");
        }

        Book book = optionalBook.get();

        book.setPublicationYear(publicationYear);
        bookRepository.save(book);

        AddBookResponse addBookResponse = new AddBookResponse();
        addBookResponse.setTitle(book.getTitle());
        addBookResponse.setIsbn(book.getIsbn());
        addBookResponse.setAuthorName(book.getAuthor().getName());
        addBookResponse.setPublicationYear(book.getPublicationYear());

        return addBookResponse;

    }

    public String deleteBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent()){
            throw new NotFoundException("Book not found");
        }

        Book book = optionalBook.get();
        bookRepository.delete(book);

        return "Book deleted";
    }
}
