package com.example.pressarticles;

import com.example.pressarticles.entities.PressArticle;
import com.example.pressarticles.repositories.PressArticleRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PressArticleRepositoryTest {
    @Autowired
    PressArticleRepository pressArticleRepository;

    @Test
    @Order(1)
    void should_return_all_articles_desc_by_publication_date() {
        //given
        //when
        List<PressArticle> result = pressArticleRepository.getArticlesDesc();

        //then
        assertEquals(3, result.size());
        assertEquals("2022", result.get(0).getPublicationDate().toString().substring(0, 4));
        assertEquals("2018", result.get(1).getPublicationDate().toString().substring(0, 4));
        assertEquals("2012", result.get(2).getPublicationDate().toString().substring(0, 4));
    }

    @Test
    @Order(2)
    void should_return_all_articles_with_keyword() {
        //given
        //when
        List<PressArticle> result = pressArticleRepository.getArticlesWithKeyword("this");

        //then
        assertEquals(2, result.size());
        assertThat(result.get(0).getName()).isIn("myArticle2", "myArticle3");
        assertThat(result.get(1).getName()).isIn("myArticle2", "myArticle3");
    }

    @Test
    @Order(3)
    void given_id_should_delete_article() {
        //given
        Integer id = 1;

        //when
        pressArticleRepository.deleteArticleById(id);
        long numOfEntities = pressArticleRepository.count();

        //then
        assertEquals(2, numOfEntities);
    }
}
