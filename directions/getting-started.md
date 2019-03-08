# Getting Started
To get started, fork this repository. <br>
We start with a starter branch, and a completed branch. <br> We'll be on the starter branch

## Get the project on your computer
Begin by cloning your forked repository. Click the button towards the top right that says _Fork_
<br>

Forking a repository makes an exact copy ( different from cloning ) with the same branches on it, just living on your GitHub
<br>

After forking, go to your own GitHub, and copy the key to clone the project from **your own** repository

- Navigate to where you want the project to live
- Run `git clone WHATEVER_YOUR_FORKED_REPO_CLONE_URL_IS` to create a folder named `react-spring`
- Go into `react-spring/` with `cd react-spring` 
- If you'd like to keep the `starter` branch the way it is, checkout onto your own branch, calling it whatever you'd like -> `git checkout -b MY_DESIRED_BRANCH_NAME` 
- If you don't care, edit `starter` directly

<br>

Feel free to look at the `completed` branch at anytime, but ensure that you commit any changes to `starter` before running `git checkout completed` and ensure that you run `npm install` from the `frontend/` directory

## Create the React content
For the purposes of development, React and Spring will be completely separate
_Side Note_ -> This guide assumes basic familiarity with React. Ensure that `create-react-app` is globally installed with `npm i -g create-react-app`.
<br>
### Installation
- From the `react-spring/` (root) directory, run `create-react-app frontend`
- From the root, `repository/`, navigate to the `frontend/` directory - `cd frontend/`
- Run `npm run start` to ensure that the React App was installed correctly
    - The `frontend/` app runs on `http://localhost:3000`
<br>

Our React app is now running. Swell. Now to configure the proxy for development

### Configuration
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

## Spring Configuration 
This project is loaded without a `JPA` dependency. If using MySQL, follow _this_ guide
<br>

### Setup
- To install the proper plugin for Spring to load React, follow [this guide](https://github.com/caldwell619/react-spring/blob/completed/directions/npm-dependency.md)
- Ensure that there are no processes running on `http://localhost:8080`
    - Run `lsof -i :8080` to see what is running on that port. Empty return means nothing is running
    - To kill processes on that port, run the following commands:
    - `PORT_NUMBER=8080`
    - `lsof -i tcp:${PORT_NUMBER} | awk 'NR!=1 {print $2}' | xargs kill`
    - Re-run the check to ensure the process is dead
- Navigate to `http://localhost:8080` to see if the page is being served correctly.
    - You will see a `Whitelabel Error Page` page if running successfully

### Creating our build pack
After ensuring Spring is working, we need to make the `JAR` file to run
- From the root `react-spring`, run the command `mvn clean install`
    - This command wipes the target folder, then re-runs our entire build script
    - This process will compile the Java, create a build pack of React, and package them into one `JAR` file
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

## Next Step
Your mission, should you choose to accept it, is to proceed to [Testing](https://github.com/caldwell619/react-spring/blob/completed/directions/testing.md).