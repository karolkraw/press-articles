package com.example.pressarticles;

import com.example.pressarticles.controllers.PressArticleController;
import com.example.pressarticles.entities.ArticleContent;
import com.example.pressarticles.entities.Author;
import com.example.pressarticles.entities.PressArticle;
import com.example.pressarticles.services.PressArticleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(controllers = PressArticleController.class)
public class ControllerWebLayerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PressArticleService pressArticleService;

    @Autowired
    private ObjectMapper mapper;

    static private PressArticle pressArticle;
    static private PressArticle pressArticle2;
    static private PressArticle pressArticle3;

    @BeforeAll
    static void init() {
        Author author1 = Author.builder().id(1).firstName("John1").lastName("Miller1").build();
        ArticleContent articleContent1 = ArticleContent.builder().id(1).content("myContent").title("myTitle").build();
        String publicationDate1 = LocalDateTime.of(2022, 6, 23, 12, 34).toString();
        String saveDate1 = LocalDateTime.now().toString();
        pressArticle = PressArticle.builder().id(1).name("myArticle1")
                .author(author1).articleContent(articleContent1).saveDate(saveDate1).publicationDate(publicationDate1).build();

        Author author2 = Author.builder().id(2).firstName("John2").lastName("Miller2").build();
        ArticleContent articleContent2 = ArticleContent.builder().id(1).content("myContent").title("myTitle").build();
        String publicationDate2 = LocalDateTime.of(2018, 6, 12, 12, 38).toString();
        String saveDate2 = LocalDateTime.now().toString();
        pressArticle2 = PressArticle.builder().id(1).name("myArticle2")
                .author(author2).articleContent(articleContent2).saveDate(saveDate2).publicationDate(publicationDate2).build();

        Author author3 = Author.builder().id(3).firstName("John3").lastName("Miller3").build();
        ArticleContent articleContent3 = ArticleContent.builder().id(1).content("myContent").title("myTitle").build();
        String publicationDate3 = LocalDateTime.of(2012, 6, 14, 12, 56).toString();
        String saveDate3 = LocalDateTime.now().toString();
        pressArticle3 = PressArticle.builder().id(1).name("myArticle3")
                .author(author3).articleContent(articleContent3).saveDate(saveDate3).publicationDate(publicationDate3).build();
    }

    @Test
    void should_add_article_and_return_it_with_accepted_status() throws Exception {
        //given
        given(pressArticleService.addPressArticle(any(PressArticle.class))).willReturn(pressArticle);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/article")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(pressArticle));


        //when
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        PressArticle result = mapper.readValue(responseBodyAsString, PressArticle.class);


        //then
        verify(pressArticleService, times(1)).addPressArticle(any(PressArticle.class));

        assertEquals(pressArticle, result);
        assertEquals(HttpStatus.ACCEPTED.value(),
                mvcResult.getResponse().getStatus(),
                "Incorrect HTTP Status Code returned");
    }

    @Test
    void should_return_all_articles_desc_by_publication_date_and_return_it_with_accepted_status() throws Exception {
        //given
        List<PressArticle> pressArticles = new ArrayList<>();
        pressArticles.add(pressArticle);
        pressArticles.add(pressArticle2);
        pressArticles.add(pressArticle3);

        given(pressArticleService.getAllPressArticlesDesc()).willReturn(pressArticles);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);


        //when
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        List<PressArticle> result = mapper.readValue(responseBodyAsString, new TypeReference<>() {});


        //then
        verify(pressArticleService, times(1)).getAllPressArticlesDesc();

        assertEquals(pressArticles, result);
        assertEquals(HttpStatus.ACCEPTED.value(),
                mvcResult.getResponse().getStatus(),
                "Incorrect HTTP Status Code returned");
    }

    @Test
    void should_invoke_service_method_deletePressArticleById_with_passed_path_variable_and_return_accepted_status() throws Exception {
        //given
        Integer id = 1;

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/article/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);


        //when
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();


        //then
        verify(pressArticleService, times(1)).deletePressArticleById(id);

        assertEquals(HttpStatus.ACCEPTED.value(),
                mvcResult.getResponse().getStatus(),
                "Incorrect HTTP Status Code returned");
    }

    @Test
    void should_invoke_service_method_updatePressArticle_and_return_accepted_status() throws Exception {
        //given
        Integer id = 1;


        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/api/v1/article/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(pressArticle));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();


        //then
        verify(pressArticleService, times(1)).updatePressArticle(id, pressArticle);

        assertEquals(HttpStatus.ACCEPTED.value(),
                mvcResult.getResponse().getStatus(),
                "Incorrect HTTP Status Code returned");
    }
}
