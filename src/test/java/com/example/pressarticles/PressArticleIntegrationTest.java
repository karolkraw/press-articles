package com.example.pressarticles;

import com.example.pressarticles.entities.ArticleContent;
import com.example.pressarticles.entities.Author;
import com.example.pressarticles.entities.PressArticle;
import com.example.pressarticles.repositories.PressArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PressArticleIntegrationTest {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PressArticleRepository pressArticleRepository;

    @Test
    public void test_get_all_press_articles_with_keyword_integration_test() {
        //given
        String keyword = "thisIsMyContent";
        PressArticle pressArticle = pressArticleRepository.getArticleById(3);

        //when
        ResponseEntity<List<PressArticle>> response = restTemplate.exchange(
                "http://localhost:" + serverPort + "/api/v1/matching-articles/" + keyword,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                "keyword"
        );

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).containsOnly(pressArticle);
    }


    @Test
    public void test_add_press_article_integration_test() throws URISyntaxException {
        //given
        Author author1 = Author.builder().id(1).firstName("John1").lastName("Miller1").build();
        ArticleContent articleContent1 = ArticleContent.builder().id(1).content("myContent").title("myTitle").build();
        String publicationDate1 = LocalDateTime.of(2022, 6, 23, 12, 34).toString();
        String saveDate1 = LocalDateTime.now().toString();
        PressArticle pressArticle = PressArticle.builder().id(1).name("myArticle1")
                .author(author1).articleContent(articleContent1).saveDate(saveDate1).publicationDate(publicationDate1).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RequestEntity<PressArticle> requestEntity = new RequestEntity<>(pressArticle, headers, HttpMethod.POST,
                new URI("http://localhost:" + serverPort + "/api/v1/article"));

        //when
        ResponseEntity<PressArticle> response = restTemplate.exchange(requestEntity, PressArticle.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isEqualTo(pressArticle);
    }
}
