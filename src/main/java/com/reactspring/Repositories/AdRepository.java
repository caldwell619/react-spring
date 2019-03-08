package com.reactspring.Repositories;

import com.reactspring.Models.Ad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdRepository extends CrudRepository<Ad,Long>{
    // I want the ads to come newest first, so I make a custom query
    @Query(value = "select * from ads order by id desc", nativeQuery = true)
    List<Ad> allAds();

}
