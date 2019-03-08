# npm plugin for Spring
This plug in goes into the `pom.xml` file
<br>
It will create a production build of our React app, and put it into the `resources/` directory of our Spring app
## Lets breakdown the steps

### The Build

#### `<workingDirectory>`
- `frontend` is the "_target_"
- This initiates the command from the `frontend/` folder

#### `<installDirectory>`
- This is where the build pack will be placed upon completion

#### `<executions>`
This begins the command line execution of our build commands
- `install node and npm`
    - Understand that this virtual version of our production build doesnt have anything installed
- `npm install`
    - This looks at your `package.json` file, and installs the JavaScript dependencies necessary for your application to run
- `npm run build`
    - This command takes a screen shot of our current `frontend/` code, and minifies it.
    - The minified code will be run through babel and webpack to be a universally accessible codebase

### Putting the build into Spring's Reach
Now that we have a production build, we need to give it to Spring

#### `<copy>`
- `todir="..."`
    - This command puts the build file into the `target/` directory
- `<fileset dir="..."` 
    - This is the location of our build


# Plugins To Copy
```
<plugin>
                <groupId>com.github.eirslett</groupId>
                <artifactId>frontend-maven-plugin</artifactId>
                <version>1.6</version>
                <configuration>
                    <workingDirectory>frontend</workingDirectory>
                    <installDirectory>target</installDirectory>
                </configuration>
                <executions>
                    <execution>
                        <id>install node and npm</id>
                        <goals>
                            <goal>install-node-and-npm</goal>
                        </goals>
                        <configuration>
                            <nodeVersion>v8.9.4</nodeVersion>
                            <npmVersion>5.6.0</npmVersion>
                        </configuration>
                    </execution>
                    <execution>
                        <id>npm install</id>
                        <goals>
                            <goal>npm</goal>
                        </goals>
                        <configuration>
                            <arguments>install</arguments>
                        </configuration>
                    </execution>
                    <execution>
                        <id>npm run build</id>
                        <goals>
                            <goal>npm</goal>
                        </goals>
                        <configuration>
                            <arguments>run build</arguments>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

            <plugin>
                <artifactId>maven-antrun-plugin</artifactId>
                <executions>
                    <execution>
                        <phase>generate-resources</phase>
                        <configuration>
                            <target>
                                <copy todir="${project.build.directory}/classes/public">
                                    <fileset dir="${project.basedir}/frontend/build"/>
                                </copy>
                            </target>
                        </configuration>
                        <goals>
                            <goal>run</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```
