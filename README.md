# FLIMCHOS

Made by: Linda, Fredrik and Madeleine

----------------

### Booking REST API for our restaurants called Flimchos.

You can add a guest, restaurant and make a booking connected to a guest and restaurant. If you want you can search
for a specific restaurant, guest or booking and see if there is any connection between.

---

### Setting up environmental variables

Set up environmental variables for your database. Specify a separate database for the tests to keep the production data 
and test data isolated from each other. These environmental variables are used in both `application.properties` and 
`application-test.properties`, where the latter is used as active profile in the tests. 

| Name          | 
|---------------|
| `DB_URL`      |
| `DB_USER`     |
| `DB_PASSWORD` |

---

## Interacting with the REST API

Interact with the REST API using HTTP requests.

---

### Booking ("/bookings")

When making a POST request, a BookingCreationDTO is passed as request body.
Syntax for creating a booking

````json
{
  "date": "2024-07-20",
  "time": "19:00:00",
  "guests": 4,
  "guestId": 3,
  "restaurantId": 2
}
````

| Command | Operation                     | Endpoint                     | Returns            |
|---------|-------------------------------|------------------------------|--------------------|
| POST    | Create a new booking          |                              | `BookingDTO`       |
| GET     | Get all bookings              |                              | `List<BookingDTO>` |
|         | Get a booking by ID           | `/{id}`                      | `BookingDTO`       |
|         | Get bookings by restaurant ID | `/restaurant/{restaurantId}` | `List<BookingDTO>` |
|         | Get bookings by guest ID      | `/guest/{guestId}`           | `List<BookingDTO>` |


----

### Guest ("/guests")

Syntax for creating a guest

````json
{
"name": "Madde",
"email": "madde@email.com"
}
````

| Command | Operation                     | Endpoint | Returns |
|---------|-------------------------------|----------|---------|
| POST    | Create a new guest            |          | `Guest` |
| GET     | Get guest with id             | `/{id}`  | `Guest` |
| DELETE  | Delete guest with id          | `/{id}`  | `Void`  |
| PUT     | Update email on guest with id | `/{id}`  | `Guest` |

----
### Restaurant ("/restaurants")

````json
{
"email": "flimchos_lillstan@example.com",
"city": "Lillstan"
}
````
| Command | Operation                 | Endpoint | Returns      |
|---------|---------------------------|----------|--------------|
| POST    | Create a new Restaurant   |          |              |
| GET     | Get all restaurants       |          | `Restaurant` |
| GET     | Get Restaurant with id    | `/{id}`  | `Restaurant` |
| PUT     | Update email and city     | `/{id}`  | `Restaurant` |
| DELETE  | Delete Restaurant with id | `/{id}`  | `Void`       |

-----
