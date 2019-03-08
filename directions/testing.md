# Development
Now begins the development of your application. Lets start with the general flow

## General Flow

### Start React Dev Server
- Navigate from root to `frontend/` --> `cd frontend/`
- Run `npm run start` to begin serving on `http://localhost:3000`
- Make changes to the code, saving refreshes the page

### Start Spring Server
- From IntelliJ, click the green play button to start serving Spring on `http://localhost:8080`
- Normal flow from here. If you make changes, you must stop and rerun the server to recompile the code

## Communication between React and Spring in Development
Here comes what is often the most frustrating but simultaneously awesome feature of building out a RESTful API
- We will be making use of `fetch()` methods to request data from our Spring backend
- We will be using a library called `axios` to serve as the `fetch()` method
- If making a `GET` request, the syntax will be `axios.get()`
- Subsequent `HTTP` methods follow, subtituting `.get()`

## Spring Route Handlers
Just like any other request handler, we will build Controllers for categories of requests

### Setup a test request in React
- From the `frontend/` directory, run `npm i -S axios`
    - The `i` is install, the `-S` flag saves the package requirement to `package.json`, which allows future developers to `npm install` based on what we declare as a dependency
- At the top of `App.js`, you will find the import statements
- Follow the syntax and `import axios from 'axios';`
    - `axios` is now an object with several methods attached to it as properties
- Declare a method on the class called `componentDidMount(){...}`
    - This method is a lifecycle hook, inherited from `React.Component`
- This lifecycle will run after the component has mounted. Lifecycle hooks are *extremely important*. Learn more about them _here_
- Inside of `componentDidMount(){...}`, make a get request, `axios.get("/api/test")`
- This returns a promise, which must be handled in one of 2 ways
    - The 1st is using `async / await` syntax. For more on that, checkout [this link](https://javascript.info/async-await)
    - The syntax we will use is traditional promises with `.then()` and `.catch()`
    <br>
    <hr>
- To start, we will just handle the response with `.console.log()`
    - That looks like `axios.get("/api/test").then(res => console.log(res)).catch(error => console.log(error))`
    - We're taking advantage of arrow functions, which do not need parenthesis with a single argument, nor a curly brace function body if a single expression is used
    - This is the same as `.then((res) => {console.log(res)})` & `.then(function(res) {console.log(res)})`
- The catch will handle the error.
- Notice how we do not need to call `res.json()` & `.then(data => console.log(data))`
    - `axios` does this for us. If you know what's happening behind the scenes and how to do it without `axios`, good. If not, learn `fetch()`

 ### Setup the Controller in Spring
 We need to setup a way for Spring to understand and be listening for requests

 - From the root, navigate to the `reactspring/` directory with `cd src/main/java/com/reactspring/`
 - Make a new `package` for the `Controllers` `mkdir Controllers` or just make the package
 - Annotate the method signature with `@RestController`
 - Make a public method, annotated by `@GetMapping("/api/test")` that returns a simple string to see if we are connected
 <br>
 The controller should look like this:
 
 ```
 
 package com.reactspring.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String sendTest(){
        return "Hey";
    }
}
```


### Making Sense of the Response
If everything worked correct, you should see an object in the console representing a successfull response

##### Properties of the Response Object 
- `config`
    - Everything our request sent to the server
- `data` 
    - This is the core of what the server sent back to us. If you followed the above example, this will be `"Hey"`. This property will usually be the only thing we care about
- `headers`
    - The servers response headers
- `status`
    - If you're seeing this view, it will usually be `200`
    - Other options are the `400's`, which are usually invalid request errors, and `500's` which usually mean the server failed to respond for various reasons
<hr>
<br>
Alright, now we have a pattern to follow and cool things to explore. Our app is talking to our backend, and we can procced to develop cool things

# Next Step
Indiana Jones and the Kingdom of the Crystal [API](https://github.com/caldwell619/react-spring/blob/completed/directions/developing-apis.md). 