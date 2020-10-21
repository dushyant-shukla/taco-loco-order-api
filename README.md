## TACO-LOCO ORDER APPLICATION

</br>

```
This project contains:
    1. A REST-based microservice API for processing Taco-Loco customer orders.
    2. A minimalist web-application to to server as unser interface.

Version : 0.0.1
Author  : Dushyant Shukla
```
</br>
</br>

## BUILDIND THE APPLICATION

</br>

### STEPS TO BUILD AND RUN SERVER SIDE APPLICATION

* ```git clone https://github.com/dushyant-shukla/taco-loco-order-api.git```
* ```cd order-api```
* ```mvn clean install```
* ```cd target```
* ```java -jar order-api-0.0.1.jar```

OR

* ```git clone https://github.com/dushyant-shukla/taco-loco-order-api.git```
* ```cd order-api```
* ```mvn spring-boot:run```

</br>

Above steps should download all the relevant external dependencies and start the server on port **7070**.

</br>

Once the server has started, an interactive document of the API can be accessed at:
```
http://localhost:7070/taco-loco/swagger-ui/
```


</br>

### API
---

#### BASE URL:
```
http::{host}:{port}/api
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