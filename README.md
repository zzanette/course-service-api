# Online Course Service
Service to management courses, content's course and author's course. The main tools used on this project are Spring boot, Gradle, Swagger, Spring JPA and Autho 0.

## How to run...

##### - Pre-requirements: 
To run this project you need to install Java 8, docker and docker-compose. You can check the go to documentation and install this items:
 *  [Install Java 8](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
 *  [Install docker](https://docs.docker.com/install/)
 *  [Install docker-compose](https://docs.docker.com/compose/install/)

##### - Running the application: 
To run the application just build the service, located at *course-service* folder. After the build, go back to the project root folder and run the application with docker-compose:

```sh
$ cd course-service #Go to api folder 
$ ./gradlew clean build #Build the application
$ cd ../ #Go back to the root folder
$ docker-compose up -d #Just run docker compose
```
if all is running ok just check the ping url:

```
http://localhost:8080/ping
```


### API Endpoints
Just access swagger to see all endpoints available, if you are running the application locally, just access the follow url:
```
http://localhost:8080/swagger-ui.html
```

### Todos
 - Write more Tests and Integration Test;
 - Run application over Kubernets.

