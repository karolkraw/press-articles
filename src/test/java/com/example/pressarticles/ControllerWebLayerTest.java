package com.example.pressarticles;

import com.example.pressarticles.controllers.PressArticleController;
import com.example.pressarticles.entities.ArticleContent;
import com.example.pressarticles.entities.Author;
import com.example.pressarticles.entities.PressArticle;
import com.example.pressarticles.services.PressArticleService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = PressArticleController.class)
public class ControllerWebLayerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PressArticleService pressArticleService;

    @Test
    void should_add_article_and_return_it() throws Exception {
        Author author = new Author();
        author.setId(1);
        author.setFirstName("John");
        author.setLastName("Miller");
        ArticleContent articleContent = new ArticleContent();
        articleContent.setId(1);
        articleContent.setContent("content1");
        articleContent.setTitle("title1");

        PressArticle pressArticle = new PressArticle();
        pressArticle.setAuthor(author);
        pressArticle.setArticleContent(articleContent);
        pressArticle.setName("myArticle");
        pressArticle.setId(1);
        String saveDate = LocalDateTime.now().toString();
        pressArticle.setSaveDate(saveDate);
        String publicationDate = LocalDateTime.of(2012, 6, 30, 12, 0).toString();
        pressArticle.setPublicationDate(publicationDate);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()).configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);



        given(pressArticleService.addPressArticle(any(PressArticle.class))).willReturn(pressArticle);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/article")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(pressArticle));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        PressArticle result = mapper.readValue(responseBodyAsString, PressArticle.class);

        // Assert
        Assertions.assertEquals(pressArticle, result);



    }
/*
ReceiverRequestDto receiverRequest = new ReceiverRequestDto(List.of(1, 2, 300, 4, 5, 6));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(receiverRequest));

        LotteryTicketDto lotteryTicketDto = new LotteryTicketDto(null, new ArrayList<>(receiverRequest.numbers()),
                null, TicketMessage.INVALID.message);
        given(numberReceiverFacade.inputNumbers(receiverRequest.numbers())).willReturn(lotteryTicketDto);


        //when
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();


        //then
        assertEquals(HttpStatus.ACCEPTED.value(),
                mvcResult.getResponse().getStatus(),
                "Incorrect HTTP Status Code returned");
 */






    /*
    @GetMapping("/article/{id}")
    ResponseEntity<PressArticle> addPressArticle(@PathVariable Integer id) {
        PressArticle pressArticle = pressArticleService.getArticleById(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(pressArticle);
    }
     */


    /*
    @GetMapping("/article/{id}")
    ResponseEntity<PressArticle> addPressArticle(@PathVariable Integer id) {
        PressArticle pressArticle = pressArticleService.getArticleById(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(pressArticle);
    }
     */



    /*
       RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailsRequestModel));

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        UserRest createdUser = new ObjectMapper()
                .readValue(responseBodyAsString, UserRest.class);

        // Assert
        Assertions.assertEquals(userDetailsRequestModel.getFirstName(),
                createdUser.getFirstName(), "The returned user first name is most likely incorrect");
     */



}
