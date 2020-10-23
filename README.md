## TACO-LOCO ORDER APPLICATION

</br>

```
This project contains:
    1. A REST-based microservice API built with Spring-Boot v2.3.4 for processing Taco-Loco customer orders.
    2. A minimalist web-application built with Angular v10.0 to serve as user interface.

Version : 0.0.1
Author  : Dushyant Shukla

SYSTEM REQUIREMENTS:
1. Java SE 8/11/15
2. Maven
3. NodeJS
```
</br>
</br>

## BUILDIND THE APPLICATIONS

</br>

### CLONE THE APPLICATION
```
git clone https://github.com/dushyant-shukla/taco-loco-order-api.git
```

</br>

### STEPS TO BUILD AND RUN SERVER SIDE APPLICATION

* ```run "cd order-api"```
* ```run "mvn clean install"```
* ```run "cd target"```
* ```run "java -jar order-api-0.0.1.jar"```

&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; OR

* ```run "cd order-api"```
* ```run "mvn spring-boot:run"```


</br>

Above steps should download all the relevant external dependencies and start the server on port **7070**.

</br>

Once the server has started, an interactive document of the API can be accessed at:
```
http://localhost:7070/taco-loco/swagger-ui/
```


</br>
</br>

### STEPS TO BUILD AND RUN THE WEB APP
* ```run "cd web-app"```
* ```run "npm install"```
* ```run "npm start"```

</br>

Once the application has started, the application should be accessible at:
```
http://localhost:4200
```

</br>
</br>

### ACCESS IN-MEMORY DATABASE CONSOLE AT
```
http://localhost:7070/taco-loco/h2-console

username: user
password: youshallnotpass
```

</br>

### API
---

#### BASE URL:
```
http:://{host}:{port}/taco-loco/api
```

</br>

#### HTTP STATUS CODE SUMMARY
```
200 - OK              :   Everything worked as expected.
400 - BAD REQUEST     :   The request was unacceptable, often due to missing a required parameter.
404 - NOT FOUND       :   The requested resource doesn't exist.
500 - SERVER ERROR    :   Something went wrong on server end.
```

</br>

### API ENDPOINTS

</br>

**MENU ITEMS**
```
GET /menu/items
```

</br>

DESCRIPTION</br>
```
Get all available menu items.
```

</br>

EXAMPLE
```
curl -X GET "http://localhost:7070/taco-loco/api/menu/items" -H "accept: application/json"
```
</br>
RETURNS

```
{
  "items": [
    {
      "id": 1,
      "name": "Veggie Taco",
      "price": 2.5
    },
    {
      "id": 2,
      "name": "Chicken Taco",
      "price": 3
    },
    {
      "id": 3,
      "name": "Beef Taco",
      "price": 3
    },
    {
      "id": 4,
      "name": "Chorizo Taco",
      "price": 3.5
    }
  ]
}
```

</br>
</br>

**ORDER**
```
POST /order
```

</br>

DESCRIPTION</br>
```
Get the total price for an order.
```

</br>

EXAMPLE
```
curl -XPOST  -H "accept: application/json" -H "Content-type: application/json" -d '{
  "items": [
    {
      "id": 1,
      "name": "Veggie Taco",
      "price": 2.5,
      "quantity": 4
    },
    {
      "id": 2,
      "name": "Chicken Taco",
      "price": 3,
      "quantity": 2
    },
    {
      "id": 3,
      "name": "Beef Taco",
      "price": 3,
      "quantity": 2
    }
  ]
}' 'http://localhost:7070/taco-loco/api/order'
```
</br>
RETURNS

```
{
  "items": [
    {
      "id": 1,
      "name": "Veggie Taco",
      "price": 2.5,
      "quantity": 4
    },
    {
      "id": 2,
      "name": "Chicken Taco",
      "price": 3,
      "quantity": 2
    },
    {
      "id": 3,
      "name": "Beef Taco",
      "price": 3,
      "quantity": 2
    }
  ],
  "total-price": 22,
  "discounted-price": 17.6
}
```

</br>
</br>
