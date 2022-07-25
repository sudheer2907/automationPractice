### This is an automation suit based on java-selenium-cucumber.

**About folder Structure**

- drivers -- keep all the drivers in this folder.
- src
  - main -- keep all framwork levelfile into main package
    - utils - all the utilities methods are kept inside src/.../utilities package
  - test
     - java -- keep all the test cases related files into test.java pakcage
     - resources -- keep all non java files like, properties files, test data files etc into resources folder
 - code compliance - all the files related to code compliance are kept into code complaince folder
 
 **Important maven commands required while doing automation**
 1. mvn clean compile -- to clean the project.
 2. mvn clean test -- to execute the test cases.
 3. mvn clean install -- to run and to download all the required maven depenancies.
 4. mvn versions:display-dependency-updates - to find out latest version of all the maven dependencies.
 5. mvn test -- to execute test cases without cleaning target folder.
 
 **Important GIT commands**
 1. git fetch
 2. git gui
 3. git stash
 4. git pop
 5. git checkout branchName
 6. git reset --hard origin master
 7. git push
 8. git pull
