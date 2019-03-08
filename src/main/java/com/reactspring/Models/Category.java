package com.reactspring.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categories")
public class Category {
        @Id
        @GeneratedValue
        private long id;

        @Column(nullable = false)
        private String name;

        @ManyToMany(mappedBy = "categories")
        @JsonBackReference
        private List<Ad> ads;

        public Category(){}

        public Category(String name, List<Ad> ads) {
            this.name = name;
            this.ads = ads;
        }
        public Category(String id) {
        this.id = Long.parseLong(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}
