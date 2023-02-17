package com.example.pressarticles.services;

import com.example.pressarticles.entities.PressArticle;
import com.example.pressarticles.repositories.PressArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PressArticleService {

    PressArticleRepository pressArticleRepository;

    public PressArticleService(PressArticleRepository pressArticleRepository) {
        this.pressArticleRepository = pressArticleRepository;
    }

    public PressArticle addPressArticle(PressArticle pressArticle) {
        pressArticle.setSaveDate(LocalDateTime.now().toString());
        return pressArticleRepository.save(pressArticle);
    }

    public PressArticle getPressArticleById(Integer id) {
        return pressArticleRepository.getArticleById(id);
    }

    public void updatePressArticle(Integer id, PressArticle pressArticleNew) {
        PressArticle pressArticleOld = pressArticleRepository.getArticleById(id);

        if(pressArticleNew.getArticleContent() != null) {
            pressArticleOld.setArticleContent(pressArticleNew.getArticleContent());
        }
        if(pressArticleNew.getAuthor() != null) {
            pressArticleOld.setAuthor(pressArticleNew.getAuthor());
        }
        if(pressArticleNew.getName() != null) {
            pressArticleOld.setName(pressArticleNew.getName());
        }
        if(pressArticleNew.getPublicationDate() != null) {
            pressArticleOld.setPublicationDate(pressArticleNew.getPublicationDate());
        }

        pressArticleOld.setSaveDate(LocalDateTime.now().toString());
        pressArticleRepository.save(pressArticleOld);
    }

    public void deletePressArticleById(Integer id) {
        pressArticleRepository.deleteArticleById(id);
    }

    public List<PressArticle> getAllPressArticlesDesc() {
        return pressArticleRepository.getArticlesDesc();
    }

    public List<PressArticle> getAllPressArticlesWithKeyword(String keyword) {
        return pressArticleRepository.getArticlesWithKeyword(keyword);
    }
}
