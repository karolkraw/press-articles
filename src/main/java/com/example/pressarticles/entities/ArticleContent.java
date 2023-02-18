package com.example.pressarticles.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ArticleContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "Id must be greater than 0")
    Integer id;

    @NotBlank(message = "title is required")
    @Size(min = 1, max = 50, message = "title must be between 1 and 50 characters")
    private String title;

    @NotBlank(message = "content is required")
    @Size(min = 1, max = 5000, message = "content must be between 1 and 50 characters")
    private String content;
}
