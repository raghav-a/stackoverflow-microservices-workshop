# stackoverflow-microservices-workshop




www.stackoverflow.com  


Features for MVP 

* Post questions 
* Answer questions
* upvote/downvote questions
* upvote/downvote answers
* Search questions


Micro Services

1) Users
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

2) Sessions
login
request  -  {“userId”:”...”, “password”:”...”}
response - 200 {“token”:”..”}

validateToken
request -  {“token”:”...”}
response - 200 {“userId”:”..”}

3) Questions

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

4) Answers
post (authenicated)
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
5) Search

search
request  -  {“searchText”:”...”}
response - 200 [
 {“questionId”:”...”, “question”:”...”,“votes”:”...”, “userName”:”...”},
 {“questionId”:”...”, “question”:”...”,“votes”:”...”, “userName”:”...”},
      ...
  ]

