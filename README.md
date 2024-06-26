# How to run this app at Local

Run as a simple spring boot /Java application on Eclipse/IntelliJ. 

# Tech stacks used 
Sprint boot 3 , JPA, H2(In memory) , Lombok , Junit 5 

# Endpoints
````
```
http://localhost:8080/rentals/checkout 
Request - 
{
    "toolCode": "LADW",
    "rentalDays": 3,
    "discountPercent": 10,
    "checkoutDate":"2020-07-02"
}

Response : 
{
    "toolCode": "LADW",
    "toolType": "Ladder",
    "brand": "Werner",
    "rentalDays": 3,
    "checkoutDate": "2020-07-02",
    "dueDate": "2020-07-05",
    "dailyCharge": 1.99,
    "chargeDays": 3,
    "preDiscountCharge": 5.97,
    "discountPercent": 10,
    "discountAmount": 0.60,
    "finalCharge": 5.37
} 

````
```

The above data gets printed in the console as well in the requested format. 

````
```
Tool code: LADW
Tool type: Ladder
Tool brand: Werner
Rental days: 3
Checkout date: 07/02/20
Due date: 07/05/20
Daily rental charge: $1.99
Charge days: 3
Pre-discount charge: $5.97
Discount percent: 10%
Discount amount: $0.60
Final charge: $5.37


All Junit Test cases are passing as expected.

````
## If Invalid request is passed via API then response would be 400 and will tell what parameter is missing 
```
Request : 
{
    
    "rentalDays": 3,
    "discountPercent": 10,
    "checkoutDate":"2020-07-02"
}
 
````
```

Response :

````
```
{
    "timestamp": "2024-06-10T22:16:02.884+00:00",
    "message": "Validation failed",
    "requestDetails": "uri=/rentals/checkout",
    "errorDetails": [
        {
            "field": "toolCode",
            "code": "NotEmpty",
            "rejectedValue": null,
            "errorMsg": "toolCode must not be empty"
        }
    ]
}

Check GenericExceptionHander.java for more details. 

````
```
