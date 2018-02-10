# stackoverflow-microservices-workshop

www.stackoverflow.com  

Join us for a deep dive into the nuances of breaking a large complex system into smaller independently scalable microservices.

This will be a hands on workshop that will introduce you to the microservices ecosystem. We will be looking into the design considerations and the components that one might need to put together to build and manage such an application.

By following along and building the application you will learn concepts such as

Gateway/edge services
Service discovery
Load balancing
Inter-service communication and protocols
Resilience
Fault tolerance
Circuit breaker pattern
Autoscaling
We will be using SpringBoot to build the application and Pivotal Cloud Foundry to deploy the same. Bring your laptops and join us for the ride.

 
 Outline/structure of the Session
Iteration 0
Introduction to problem statement
Design exercise
Iteration 1
Starting with a service
Deploying to Pivotal Cloud Foundry
Iteration 2
Introducing a Gateway
Client side load balancing with built in support
Iteration 3
Adding service discovery with Eureka
MarketPlace Eureka on Pivotal Cloud Foundry
Iteration 4
Authenticating requests at gateway
Break
Iteration 5
Consuming APIs with Feign
Client side load balancing with Ribbon
Iteration 6
Autoscaling services
Iteration 7
Circuit Breaker with Hystrix
Monitoring with Hystrix Dashboard
MarketPlace Hystrix on Pivotal Cloud Foundry
Q & A
Each iteration will be 5 to 10 min in duration.

Learning Outcome
Architecting with scale in mind
Breaking down complex systems into small, independently scalable microservices
The need for gateways, service registry, auto-scaling and circuit breaker patterns
A primer for anyone looking to get started with building Cloud Native applications
Target Audience
Software engineers, architects, technical leaders and anyone interested in micro-services and cloud architecture.


## Features for MVP

* Post questions 
* Answer questions
* upvote/downvote questions
* upvote/downvote answers
* Search questions



## THE MICRO-SERVICES ARCHITECTURE

![alt text](https://github.com/raghav-a/stackoverflow-microservices-workshop/blob/master/Stackoverflow.jpg)

## Micro Services APIS

**1) Users**
```
saveProfile 
request -  userdetails {“name”:”..” ...}
response - 201 {“userId”:”..”}

editProfile (authenticated)
request -  userId, userdetails {“name”:”” ...}
response - 200 

getProfile (authenticated)
request  -  userId
response - 200 userdetails {“name”:”” ...}

```

**2) Sessions**
```
login
request  -  {“userId”:”...”, “password”:”...”}
response - 200 {“token”:”..”}

validateToken
request -  {“token”:”...”}
response - 200 {“userId”:”..”}

```
**3) Questions**
```
post (authenicated)
request  -  {“quetion”:”...”, “tags”:”...”}
response - 201 {“questionId”:”..”}

edit (authenicated)
request  -  {“quetionId”:”...”, “quetion”:”...”, “tags”:”...”}
response - 200 

upvote (authenicated)
request  -  {“quetionId”:”...”}
response - 200 

downvote (authenicated)
request  -  {“quetionId”:”...”}
response - 200 
get
request  -  {“quetionId”:”...”}
response - 200 {“questionId”:”..”, “question”:”...”, “votes”:”...”, “tags”:”...”}
```

**4) Answers**

```post (authenicated)
request  -  {“quetionId”:”...”, “answer”:”...”}
response - 201 {“answerId”:”..”}

edit (authenicated)
request  -  request  -  {“quetionId”:”...”, “answer”:”...”}
response - 200 

upvote (authenicated)
request  -  {“answerId”:”...”}
response - 200 

downvote (authenicated)
request  -  {“answerId”:”...”}
response - 200 

get 
request  -  {“answerId”:”...”}
response - 200 {“questionId”:”..”, “answer”:”...”, “votes”:”...”, “userName”:”...”}

getAll
request  -  {“questionId”:”...”}
response - 200         [
 	{“answer”:”...”, “votes”:”...”, “userName”:”...”},
            {“answer”:”...”, “votes”:”...”, “userName”:”...”},
             ...
           ]
```           
**5) Search**
```
search
request  -  {“searchText”:”...”}
response - 200 [
 {“questionId”:”...”, “question”:”...”,“votes”:”...”, “userName”:”...”},
 {“questionId”:”...”, “question”:”...”,“votes”:”...”, “userName”:”...”},
      ...
  ]
```
