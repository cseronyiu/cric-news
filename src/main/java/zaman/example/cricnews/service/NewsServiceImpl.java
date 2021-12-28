package zaman.example.cricnews.service;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zaman.example.cricnews.dto.*;
import zaman.example.cricnews.entity.NewsEntity;
import zaman.example.cricnews.repository.NewsRepository;
import zaman.example.cricnews.utility.CommonUrl;
import zaman.example.cricnews.utility.Utils;

import java.util.ArrayList;
import java.util.List;


@Service
public class NewsServiceImpl implements NewsService{

    final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public NewsResponse getNews(ServerSidePaginationInformation pageInfo, NewsRequest newsRequest) {
        NewsResponse newsResponse = new NewsResponse();
        List<News> newsList = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(pageInfo.getPageId(), pageInfo.getPageSize());
        Page<NewsEntity> repositoryAll = newsRepository.findAll('%'+newsRequest.getSearchKey()+'%', pageRequest);
        List<NewsEntity> cricnewsEntities = repositoryAll.getContent();
        for (NewsEntity cricnewsEntity : cricnewsEntities) {
            News news = new News();
            news.setTitle(cricnewsEntity.getTitle());
            news.setLink(cricnewsEntity.getLink());
            news.setDescription(cricnewsEntity.getDescription());
            news.setGuid(cricnewsEntity.getGuid());
            newsList.add(news);
        }
        newsResponse.setNewses(newsList);
        newsResponse.setTotal(newsList.size());
        newsResponse.setRecordsTotal(repositoryAll.getTotalElements());
        newsResponse.setRecordsFiltered(repositoryAll.getTotalElements());
        return newsResponse;
    }

    @Override
    @Scheduled(cron = "0 0/5 * * * ?")
    public void addNews() {
        ResponseEntity<String> responseEntity = null;
        RestTemplate restTemplate = new RestTemplate();
        Gson gson = new Gson();
        try {
            responseEntity = restTemplate.getForEntity(CommonUrl.scheduleURL, String.class);
            JSONObject xmlJSONObj = XML.toJSONObject(responseEntity.getBody());
            ObjectResponse objectResponse = gson.fromJson(xmlJSONObj.toString(), ObjectResponse.class);
            if (objectResponse != null && objectResponse.getRss() != null && objectResponse.getRss().getChannel() != null && objectResponse.getRss().getChannel().getItem().size() > 0) {
                saveNews(objectResponse.getRss().getChannel().getItem());
            }
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
    }

    private void saveNews(List<Item> items){
        List<NewsEntity> cricnewsEntities = new ArrayList<>();
        for (Item item :items) {
            NewsEntity cricnewsEntity = new NewsEntity();
            Long identifire = Utils.getIdentityFromURL(item.getGuid());
            cricnewsEntity.setId(identifire);
            cricnewsEntity.setTitle(item.getTitle());
            cricnewsEntity.setLink(item.getLink());
            cricnewsEntity.setGuid(item.getGuid());
            cricnewsEntity.setDescription(item.getDescription());
            cricnewsEntities.add(cricnewsEntity);
        }
        newsRepository.saveAll(cricnewsEntities);
    }
}
