package com.example.pressarticles.repositories;

import com.example.pressarticles.entities.ArticleContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleContentRepository extends JpaRepository<ArticleContent, Integer> {
}
