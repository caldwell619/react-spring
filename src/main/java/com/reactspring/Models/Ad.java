package com.reactspring.Models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String price;
    @OneToOne
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="ads_categories",
            joinColumns={@JoinColumn(name="ad_id")},
            inverseJoinColumns={@JoinColumn(name="category_id")}
    )
    private List<Category> categories;


    protected Ad(){ }

    public Ad(String title, String description, String price, User user, List<Category> categories) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.user = user;
        this.categories = categories;
    }


    public Ad(String title, String description, String price, List<Category> categories) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.categories = categories;
    }


    public Ad(long id, String title, String description, String price, User user, List<Category> categories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.user = user;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
