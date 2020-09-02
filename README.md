#GIT URL
git@github.com:tuhindaw/tuhin-projects.git

Clone the project using below command
git clone git@github.com:tuhindaw/tuhin-projects.git

#RESTful Microservice-description
This is a Spring Boot application (2.3.3.RELEASE) project that exposes an HTTP POST REST endpoint. This endpoint accepts Set of Strings in a specific JSON format
and reruns longest common substring of the "values" in the POST body. 

# RESTful Endpoint
POST Endpoint : http://localhost:[port]/api/substring/lcs - Typically this application runs on port 8080 so the endpoint can be reached by
url http://localhost:8080/api/substring/lcs. The endpoint can be hit using any Rest client such as Postman.

Sample request - Pass the below JSON in Request body (Under Body tab in Postman)

{
    "setOfStrings": [
        {
            "value": "comcast"
        },
        {
            "value": "comcastic"
        },
        {
            "value": "broadcaster"
        }
    ]
}

Sample output:-

{
    "lcs": [
        {
            "value": "cast"
        }
    ]
}


# Validation
There are some request validation in place around this endpoint. For example, the input Strings in the request body must be unique otherwise
BadRequestException(Http 400) would be thrown.
Sample Request:-
{
    "setOfStrings": [
        {
            "value": "comcast"
        },
        {
            "value": "comcast"
        },
        {
            "value": "broadcaster"
        }
    ]
}

Sample Response:-
BadRequestException (Http 400)

# Build/Run

This application can be built and run in following couple of ways.

Procedure #1 -> Since this is a Springboot web application so it comes with a inbuilt tomcat container. Just execute below commands to build & run.
    
    Prerequisites : Your system need to have the following minimum requirements to create a Spring Boot
                    application:
                     Java 8
                     Gradle 4.x 
    ./gradlew build -> to build project
    ./gradlew bootrun -> start application

Procedure #2 -> It can also be deployed with any web server. the 1st command will create a .war file named lcs-0.0.1-SNAPSHOT.war under 
the \build\libs directory. Run the Tomcat Server, and deploy the WAR file under the webapps directory. 


