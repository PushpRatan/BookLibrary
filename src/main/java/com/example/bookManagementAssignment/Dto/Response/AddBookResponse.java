package com.example.bookManagementAssignment.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddBookResponse {
    String title;
    Long isbn;
    String publicationYear;
    String authorName;
}
