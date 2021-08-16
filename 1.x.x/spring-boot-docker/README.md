# spring-boot-dockerized

Important commands :

To build the image  <br>
$ mvn package docker:build 

To run the image <br>
$ docker run -p 8080:8080 springio/dockerized-spring-boot

To check the status of container running <br>
$ docker ps -a

To stop the container
$ docker stop containerID

For details please refer : https://spring.io/guides/gs/spring-boot-docker/
