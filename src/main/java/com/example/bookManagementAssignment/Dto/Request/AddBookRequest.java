package com.example.bookManagementAssignment.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddBookRequest {
    String title;
    Long isbn;
    String publicationYear;
    Long authorId;
}
