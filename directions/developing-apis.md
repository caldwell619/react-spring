# Developing the API's
This guide is to further flesh out the process of building the REST Controllers

## Adding a database
Add this dependency to the `pom.xml` file. It allows us to use all the wild annotations we are going to need. It also requires we configure a database in `applications.properties`

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

- In order for our application to function, we need also need to add `application.properties` to the `resource/` directory
    - Follow the `example.properties` file to build your `application.propeties` file    

## Creating the Annotation Sensation Sweeping the Nation
In order for Spring to do its witchcraft, annotations, `@`, must be everywhere

### Repositories
Pretty straightforward. Follow the pattern [here](https://github.com/caldwell619/react-spring/tree/completed/src/main/java/com/reactspring/Repositories). If you have any custom queries, here is where to declare them

### Entities
I recommend you look [here](https://github.com/caldwell619/react-spring/blob/completed/src/main/java/com/reactspring/Models) for the `Entities`. Spring is doing a lot behind the scenes. 
<br>

Refer to the [curriculum](https://java.codeup.com/spring/fundamentals/repositories/) for a more detailed explanation
<br>

##### Highlights:
- Spring maps the relationships between the different class properties for you. 
- All you need is an empty constructor, and Spring will do the rest. <- Rest, lol. Get it?
- [This line](https://github.com/caldwell619/react-spring/blob/aedce23742f7721d09969b180f07acd9924feb78/src/main/java/com/reactspring/Models/Ad.java#L21) begins the relationship necessary for the `Category` array to come back

### Controllers
[Here](https://github.com/caldwell619/react-spring/blob/completed/src/main/java/com/reactspring/Controllers/AdsController.java) we go. Finally we are setting up to send back actual data.
<br>

1. We have to inject the dependencies ( _repositories_ ), to be able to access the `CRUD` methods in them
2. Begin planning out all that you want your application to do.
    - Good place to start is by using the `CRUD` methods on the `Daos`. (`.all()`, `findById()`, `.save()`, etc)

#### Why we're here
Sending a `post` is common enough. However, we're going to send a `post` from React, to Spring, with a `json` body!
 
I'm jazzed.

So we have to setup the controller to accept `json` requests.
- Create a route handler that looks like this:
```$xslt
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
```

Lets break this down.
- `@RequestBody Ad ad` - this annotation automatically maps the incoming `post` request body to an entity.. It's almost frightening
    - _side note:_ This can be hard to debug, which is why our `Enitiy` declarations are so important. That's usually where the failure is 
- Randomly making a list, even though we're sending an array already? 
    - Yep. Essentially what's happening is Spring will get caught in an infinite loop of trying to auto-populate the annotated fields
    - It will see the relationships we've declared, and continuously try to find the end point, which isn't possible. It will just repeat the cycle of looking for them.
    - By defining a separate `ArrayList`, we can just tell the `Dao` what the `Category` field looks like, manually
- Same for the `User` property as above
- Finally, saving the `Ad`. If all went well, you should be able to see the new `Ad` in the database. Spring created everything for us.
