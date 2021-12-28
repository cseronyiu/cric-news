package zaman.example.cricnews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zaman.example.cricnews.dto.News;
import zaman.example.cricnews.dto.NewsRequest;
import zaman.example.cricnews.dto.NewsResponse;
import zaman.example.cricnews.dto.ServerSidePaginationInformation;
import zaman.example.cricnews.service.NewsService;
import zaman.example.cricnews.service.PageManagementService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NewsApiController {

final NewsService newsService;


    @Autowired
    public NewsApiController(NewsService newsService) {
        this.newsService = newsService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(path = "/getNews")
    public NewsResponse getNews(NewsRequest newsRequest, HttpServletRequest request) {
        NewsResponse newsResponse=new NewsResponse();
        PageManagementService pageManagementService=new PageManagementService();
        ServerSidePaginationInformation pageInfo = pageManagementService.GetPageInfo(request);
        newsResponse= newsService.getNews(pageInfo,newsRequest);
        return newsResponse;
    }
}
