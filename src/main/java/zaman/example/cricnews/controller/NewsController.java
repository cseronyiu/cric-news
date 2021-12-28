package zaman.example.cricnews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import zaman.example.cricnews.service.NewsService;

@Controller

public class NewsController {

    final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping(path = "/news")
    public String getAllNews() {
        return "news/view_news";
    }
}
