package com.example.bookManagementAssignment.Repository;


import com.example.bookManagementAssignment.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
