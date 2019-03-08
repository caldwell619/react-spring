package com.reactspring.Models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ads")
public class Ad {
    // ---------------------------------------- Defining Data Types ---------------------------------------- //
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

    // -------------------------------- Black Magic Constructor ---------------------------------------- //

    protected Ad(){ }

    // ---------------------------------------- Getter / Setter ---------------------------------------- //

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
