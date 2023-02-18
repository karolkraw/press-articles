package com.example.pressarticles.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Validated
public class PressArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "Id must be greater than 0")
    Integer id;

    @NotBlank(message = "name is required")
    @Size(min = 1, max = 50, message = "name must be between 1 and 50 characters")
    private String name;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn
    @NotNull
    private Author author;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn
    @NotNull
    private ArticleContent articleContent;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS")
    private LocalDateTime publicationDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS")
    private LocalDateTime saveDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PressArticle that = (PressArticle) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
                && Objects.equals(author, that.author) && Objects.equals(articleContent, that.articleContent)
                && Objects.equals(publicationDate, that.publicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, articleContent, publicationDate, saveDate);
    }
}
