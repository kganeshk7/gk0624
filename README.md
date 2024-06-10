# How to run this app at Local


# Tech stacks used 
Sprint boot 3 , JPA, H2(In memory) , Lombok 

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
