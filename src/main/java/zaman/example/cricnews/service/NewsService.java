package zaman.example.cricnews.service;

import zaman.example.cricnews.dto.NewsRequest;
import zaman.example.cricnews.dto.NewsResponse;
import zaman.example.cricnews.dto.ServerSidePaginationInformation;

public interface NewsService {
    void addNews();

    NewsResponse getNews(ServerSidePaginationInformation pageInfo, NewsRequest newsRequest);
}
