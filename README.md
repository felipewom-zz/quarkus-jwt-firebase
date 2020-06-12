# Quarkus using Firebase Auth

This project uses Quarkus with Firebase JWT authentication from request.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev -DPROJECT_ID=${your firebase project id here}

```

and you can test via:

```http request

Get email if authenticated or anonimous
curl --location --request GET 'http://0.0.0.0:8080/jwt'


Get All Claims
curl --location --request GET 'http://0.0.0.0:8080/jwt/claims' \
    --header 'Authorization: Bearer {jwt_token}'

```