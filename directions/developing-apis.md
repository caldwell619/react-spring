# Developing the API's
This guide is to further flesh out the process of building the REST Controllers

## Adding a database
Add this dependency to the `pom.xml` file

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

- In order for our application to function, we need also need to add `application.properties` to the `resource/` directory
    - Follow the `example.properties` file to build your `application.propeties` file    

## Creating the model entities
In order for Spring to do its witchcraft, annotations must be everywhere

### Ads
I recommend you look [here]()

 