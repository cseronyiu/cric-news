package zaman.example.cricnews.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zaman.example.cricnews.entity.NewsEntity;


import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    @Query("select ce from NewsEntity ce where UPPER(ce.title) like UPPER(?1) ")
    Page<NewsEntity> findAll(String serchKey, Pageable pageable);
}
