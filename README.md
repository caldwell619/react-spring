# Getting React and Spring to play nicely
The purpose of the guide is to understand how to get React to seamlessly integrate with the Spring framework

## Getting Started
To get started, fork this repository. <br>
We start with a starter branch, and a completed branch. <br> We'll be on the starter branch

### Get the project on your computer
Begin by cloning your forked repository. Click the button towards the top right that says _Fork_
<br>

Forking a repository makes an exact copy ( different from cloning ) with the same branches on it, just living on your GitHub
<br>

After forking, go to your own GitHub, and copy the key to clone the project from *your own* repository

- Navigate to where you want the project to live
- Run `git clone WHATEVER_YOUR_FORKED_REPO_CLONE_URL_IS` to create a folder named `react-spring`
- Go into `react-spring/` with `cd react-spring`
- Ensure that you are on the `starter` branch by executing `git branch`.

<br>
Feel free to look at the `completed` branch at anytime, but ensure that you commit any changes to `starter` before running `git checkout completed`

### Create the React content
For the purposes of development, React and Spring will be completely separate
_Side Note_ -> This guide assumes basic familiarity with React. Ensure that `create-react-app` is globally installed with `npm i -g create-react-app`.
<br>
#### Installation
- From the `react-spring/` (root) directory, run `create-react-app frontend`
- From the root, `repository/`, navigate to the `frontend/` directory - `cd frontend/`
- Run `npm run start` to ensure that the React App was installed correctly
    - The `frontend/` app runs on `http://localhost:3000`
<br>

Our React app is now running. Swell. Now to configure the proxy for development

#### Configuration
By default, our app running on `http://localhost:30000` will not communicate with our backend due to them not being on the same port.
<br>

For more information on `CORS`, check out [this link](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS).
<br>

For our purposes, we are going to proxy requests. This essentially _tricks_ Spring into thinking the requests are coming from itself
<br>
- In the `frontend/` directory, find and open the `package.json` file
- Anywhere in the file (preferably towards the bottom), add the line `"proxy": "http://localhost:8080"`
    - Ensure that you put a comma after this line
- This will proxy all `fetch()` requests to the port our Spring application is running on
    - If you want to run your Spring application on a different port, change the above to the desired port
<hr>

### Spring Configuration 
This project is loaded without a `JPA` dependency. If using MySQL, follow _this_ guide

#### Setup
- Ensure that there are no processes running on `http://localhost:8080`
    - Run `lsof -i :8080` to see what is running on that port. Empty return means nothing is running
    - To kill processes on that port, run the following commands:
    - `PORT_NUMBER=8080`
    - `lsof -i tcp:${PORT_NUMBER} | awk 'NR!=1 {print $2}' | xargs kill`
    - Re-run the check to ensure the process is dead
- Navigate to `http://localhost:8080` to see if the page is being served correctly.
    - You will see a `Whitelabel Error Page` page if running successfully

#### Creating our build pack
After ensuring Spring is working, we need to make the `JAR` file to run
- From the root `react-spring`, run the command `mvn clean install`
    - This command wipes the target folder, then re-runs our entire build script
    - This process will complie the Java, create a build pack of React, and package them into one `JAR` file
- After a successful build, find the location of the `JAR` file that was just created
    - From the root, run `find target -name \*.jar` -> copy the output
- To run the build, ensure that port 8080 is not in use with the above commands
- Run `java -jar YOUR_JAR_FILE` with the `JAR` file being what was previously outputted and copied
<br>

This will result in `http://localhost:8080` running Spring, serving your React code, which is just the boilerplate that comes with `create-react-app`

## Current File Structure
The current view of our application will be as follows
root:
```
react-spring
  |_.idea <-- (if using IntelliJ)
  |_.mvn
  |_frontend/
    |_build/
     |_Dont worry about it
    |_node_modules/
     |_Dont worry about it
    |_public/
     |_index.html
     |_manifest.json
    |_src/
     |_App.css
      |_App.js
      |_App.test.js
      |_index.css
      |_index.js
      |_serviceWorker.js
	|_.gitignore
	|_.package.json
  	|_.package-lock.json
  	|_README.md
  |_src/
   |_main/
    |_java/
     |_com
      |_reactsping
       |_ReactSpringApplication.java
        |_resources/
         |_static/
          |_templates/
           |_application.properties
           |_example.properties
	|_test/
	|_target/
	|_.gitignore
	|_mvnw
	|_mvnw.cmd
	|_pom.xml
	|_README.md
```
## Housekeeping
At this point, we can delete some of the boilerplate that comes with `create-react-app`.<br>
You can go into `App.js` and delete the `JSX` in the `return()` statement. This is the entry point into your React application for development purposes.
<br> 

You can also delete the `css` files, or keep them. It's up to you. We will be importing `css` later, but do what makes you happy with the generated files.
<hr>

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
















