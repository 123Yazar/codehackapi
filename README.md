# CoderHack
This project is a simple RESTful API for managing Leaderboard for a Coding Platform.It allows users to register,update score,retrieve all users according to the score,retrieve a particular user using id,and remove user.
This api is build using Spring Boot and MongoDB to persist the user data.

# Getting Started
To get started with the project,follow these steps:

1. Clone the repository to your local machine.
2. Ensure MongoDB is installed in your local machine and running.
3. Open it using your prefered IDE.
4. Run the main file CodehackapiApplication.java
5. Alternatively, you can build and run project using Gradle: `./gradlew bootrun`

# Usage
## Get all Users
Endpoint: `GET /users`
Retrieves all users in the system.

## Get a particular User
Endpoint: `GET /users/id`
Replace user id to get that particular user details.

## Adding a User
Endpoint: `POST /users`
```
{
"id" : "User id",
"username" : "user name"
}
```
Replace `"id"` and `"username"` with actual details.

## Updating a User to add score
Endpoint: `PUT /users/id`
Replace `id` with actual user id to update
```
{
"score":"score_val"
}
```
Replace `"score_val"` with actual score between 1-100

## Removing a User 
Endpoint: `DELETE /users/id` 
Replace `id` with actual user id to remove

# Postman Collections
For testing api endpoints you can the postman collection provided in the link:
[Coder Hack Api Postman Collection](https://elements.getpostman.com/redirect?entityId=33792984-88bdcba5-b689-44af-8f45-a897780e4b11&entityType=collection)
