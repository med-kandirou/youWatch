package com.medkandirou.youwatch.video;

import com.medkandirou.youwatch.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VideoRepository extends JpaRepository<Video,Long> {
    Page<Video> findAll(Pageable pageable);

    List<Video> findVideoByChannelId(Long channel_id);


    @Query("SELECT v FROM Video v order by v.nbrVues DESC limit 7")
    List<Video> trending();

    @Query("SELECT v FROM Video v WHERE " +
            "LOWER(v.title) LIKE LOWER(CONCAT('%', :inputSearch, '%')) OR " +
            "LOWER(v.category.name) LIKE LOWER(CONCAT('%', :inputSearch, '%')) OR " +
            "LOWER(CONCAT(v.channel.firstname, v.channel.lastname)) LIKE LOWER(CONCAT('%', :inputSearch, '%'))")
    List<Video> search(String inputSearch);


    List<Video> findByCategory(Category category);




}
