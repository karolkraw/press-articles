package com.example.pressarticles.repositories;

import com.example.pressarticles.entities.PressArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PressArticleRepository extends JpaRepository<PressArticle, Integer> {
    @Query("SELECT p FROM PressArticle p WHERE p.id = :id")
    PressArticle getArticleById(@Param("id") Integer id);

    @Modifying
    @Query("DELETE FROM PressArticle p WHERE p.id = :id")
    void deleteArticleById(@Param("id") Integer id);

    @Query("SELECT p FROM PressArticle p ORDER BY p.publicationDate DESC")
    List<PressArticle> getArticlesDesc();

    @Query("SELECT p FROM PressArticle p LEFT JOIN p.articleContent a " +
            "WHERE a.title LIKE %:keyword% OR a.content LIKE %:keyword%")
    List<PressArticle> getArticlesWithKeyword(@Param("keyword") String keyword);

}
