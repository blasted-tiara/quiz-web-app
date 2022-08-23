# Quiz web app backend

## Setup

This project uses Maven for build automation and Hibernate with MySQL database server for persistence layer.
In order to run the commands below, you will have to install and add to your PATH variable java 11 sdk and maven.
You will also need to have mysql server running locally with the following configuration:
```json
{
    "hostname": "localhost",
    "port": 3306,
    "username": "root",
    "password": "toor",
    "schema-name": "test"
}
```

Run the following command to build and test:
```
mvn test
```

To start the server, run:

```
mvn exec:java
```

Test the access point:

```
http://localhost:8081/testresource
```
