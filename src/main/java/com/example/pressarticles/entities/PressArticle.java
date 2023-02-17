package com.example.pressarticles.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PressArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    private String name;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn
    private ArticleContent articleContent;

    private String publicationDate;

    private String saveDate;

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public ArticleContent getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(ArticleContent articleContent) {
        this.articleContent = articleContent;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }*/


    /*@Override
    public String toString() {
        return "PressArticle{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", articleContent=" + articleContent +
                ", publicationDate=" + publicationDate +
                ", saveDate=" + saveDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PressArticle that = (PressArticle) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(author, that.author) && Objects.equals(articleContent, that.articleContent) && Objects.equals(publicationDate, that.publicationDate) && Objects.equals(saveDate, that.saveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, articleContent, publicationDate, saveDate);
    }*/
}
