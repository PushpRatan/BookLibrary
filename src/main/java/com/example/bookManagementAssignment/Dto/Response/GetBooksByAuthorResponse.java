package com.example.bookManagementAssignment.Dto.Response;

import com.example.bookManagementAssignment.Model.Book;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class GetBooksByAuthorResponse {
    List<String> books = new ArrayList<>();
}
