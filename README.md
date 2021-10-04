# myRetailInterview

Java 11
Gradle
MongoDB
Spring Boot
Spring Data MongoDB
IDEA IntelliJ
Insomnia

Usage Instructions

- Install MongoDB

Go to https://www.mongodb.com/try/download/community and download MongoDB and install. Or use homebrew if on mac https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/ .

When installed, run on Mongo Server on default port 27017.

from command line run:

mongo

use product_db

db.getCollection('ProductPrice').save({ "productId": 13860428, "price": 5.99, "currencyCode": "USD" })

- Git Clone this repo

open in IDEA IntelliJ or any other IDE

Open cloned repo

run the MyRetailApplication.java class

- Open Insomnia and setup a new get and put requests

  - For the get request set the URL as http://localhost:8080/api/product/13860428
  
  - For the put request set the URL as http://localhost:8080/api/product/13860428
    - Set the body as JSON and enter     
{
  "id": 0,
  "title": "The Big Lebowski (Blu-ray)",
  "priceInfo": {
    "id": "6157ef99a9977c7b35c67266",
    "productId": 13860428,
    "price": 19.99,
    "currencyCode": "USD"
  }
}

Change price or currency code to any thing wanted.

Run the requests
