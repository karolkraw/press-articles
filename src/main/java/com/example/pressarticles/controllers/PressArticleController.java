package com.example.pressarticles.controllers;

import com.example.pressarticles.entities.ArticleContent;
import com.example.pressarticles.entities.Author;
import com.example.pressarticles.entities.PressArticle;
import com.example.pressarticles.services.PressArticleService;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Validated
public class PressArticleController {

    PressArticleService pressArticleService;

    public PressArticleController(PressArticleService pressArticleService) {
        this.pressArticleService = pressArticleService;
    }

    @PostMapping("/article")
    ResponseEntity<PressArticle> addPressArticle(@RequestBody PressArticle pressArticle) {
        PressArticle result = pressArticleService.addPressArticle(pressArticle);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(result);
    }

    @GetMapping("/article/{id}")
    ResponseEntity<PressArticle> getPressArticleById(@PathVariable Integer id) {
        PressArticle pressArticle = pressArticleService.getPressArticleById(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(pressArticle);
    }

    @PatchMapping("/article/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updatePressArticle(@RequestBody PressArticle pressArticle, @PathVariable Integer id) {
        pressArticleService.updatePressArticle(id, pressArticle);
    }

    @DeleteMapping("/article/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deletePressArticleById(@PathVariable Integer id) {
        pressArticleService.deletePressArticleById(id);
    }

    @GetMapping("/matching-articles/{keyword}")
    ResponseEntity<List<PressArticle>> getAllPressArticlesWithKeyword(@PathVariable @Size(min = 1, max = 50, message = "keyword must be between 1 and 50 characters")String keyword) {
        List<PressArticle> pressArticles = pressArticleService.getAllPressArticlesWithKeyword(keyword);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(pressArticles);
    }

    @GetMapping("/articles")
    ResponseEntity<List<PressArticle>> getAllPressArticlesDesc() {
        List<PressArticle> pressArticles = pressArticleService.getAllPressArticlesDesc();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(pressArticles);
    }


}
