package com.example.bookManagementAssignment.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddAuthorResponse {
    String name;
    String biography;
}
