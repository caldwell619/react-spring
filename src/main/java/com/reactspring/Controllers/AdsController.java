package com.reactspring.Controllers;

import com.reactspring.Models.Ad;
import com.reactspring.Models.Category;
import com.reactspring.Repositories.AdRepository;
import com.reactspring.Repositories.CategoryRepository;
import com.reactspring.Repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdsController {

    // dependency injection - this allows all the repositories to be accessible in the controller
    private final AdRepository adDao;
    private final CategoryRepository adCategoryDao;
    private final UserRepository userDao;

    public AdsController(AdRepository adDao, CategoryRepository adCategoryDao, UserRepository userDao) {
        this.adCategoryDao = adCategoryDao;
        this.adDao = adDao;
        this.userDao = userDao;
    }

    // all ads of every user - newest first
    @RequestMapping("/api/ads")
    public List<Ad> all(){
        return adDao.allAds();
    }

    // insert an ad JSON formatted
    @PostMapping("/api/create-ad")
    public void insertAd(@RequestBody Ad ad){
        List<Category> adCategories = new ArrayList<>();
        for(Category category: ad.getCategories()){
            adCategories.add(adCategoryDao.findById(category.getId()));
        }
        ad.setCategories(adCategories);
        ad.setUser(userDao.getUserById(ad.getUser().getId()));
        adDao.save(ad);
    }
}
