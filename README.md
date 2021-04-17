[![Build Status](https://travis-ci.com/darkrevan85/cake-manager.svg?branch=master)](https://travis-ci.com/darkrevan85/cake-manager)
![Docker Cloud Automated build](https://img.shields.io/docker/cloud/automated/darkrevan85/cake-manager)

Run the app with Docker:

docker build -t cake-manager .
docker run --publish 8080:8080  cake-manager




Run the app locally skipping sec layer for testing purposes:

mvn spring-boot:run -Dspring-boot.run.profiles=test




Run the app locally:

mvn spring-boot:run


Test web service post via command line to add new cake:

curl -d  "{\"title\":\"Fabbb Lemon cheesecake\",\"description\":\"empty\",\"image\":\"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"}" -H "Content-Type: application/json" http://localhost:8080/createCake




Changes:
Re-implemented the app with Spring Boot framework, the port to run the app now is the standard 8080
JPA and repository used for persistence layer and interacting with h2 DB (changed some settings in the entity class)
Used Spring security for auth2 (github auth)
Tests implemented with Spring
Added docker file for running the app in a container
Added support for Travis CI, added travis yml file
Added support for Cloud CI by integrating github, docker and Travis CI
