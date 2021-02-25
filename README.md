## Concert Agency
This project represents a basic template of a Concert Agency API, where user can register and purchase tickets on different musical events.

There is used N-tier architecture with DB layer, DAO layer,
Service layer and Controllers layer.
Controllers endpoints are built according to REST principles and all project 
is developed according to SOLID principles with authorization and authentication.
All these features are realized with:
- MySQL (DB layer)
- Hibernate (DAO layer)
- Java (Service layer)
- Spring MVC (Controllers layer)
- Spring Security (Authentication/Authorization)<br>

All data is transferred as JSON. Every User can have multiple Roles

## Available endpoints, their description and Role access
- __POST__ `/register`<br> 
  New User registration endpoint<br>
  Data format: `{"email" : "yourEmail", "password" : "yourPassword",
  "repeatPassword" : "repeatYourPassword"}`<br>
  Access granted for anybody.
- __GET__ `/concert-halls`<br> 
  Get all available Concert Halls<br>
  Data format: `{"id" : "concert_hall_Id","capacity" : "capacity","description" : "description"}`<br>
  Access granted for authorized users.
- __POST__ `/concert-halls`<br> 
  Creating a Concert Hall<br>
  Data format: `{"capacity" : "capacity","description" : "description"}`<br>
  Access granted for Admin users.
- __GET__ `/performances`<br>
  Get all available Performances<br>
  Data format: `{"id" : "performance_Id","title" : "title","description" : "description"}`<br>
  Access granted for authorized users.
- __POST__ `/performances`<br> 
  Creating a Performance<br>
  Data format: `{"title" : "title","description" : "description"}`<br>
  Access granted for Admin users.
- __GET__ `/events/available?performanceId={performance_id}&date=DD.MM.YYYY`<br>
  Get all available Events of Performance with performance_id on date of format "DD.MM.YYYY"<br>
  Data format: `{"id" : "event_id","performance" : "performance_title",
  "concertHall" : "concert_hall_description","showTime" : "DD.MM.YYYY HH.MM"}`<br> 
  Access granted for authenticated users.
- __POST__ `/events`<br>
  Creating an Event<br>
  Data format: `{"performanceId" : "performance_id", "concertHallId" : "concert_hall_id",
  "showTime" : "DD.MM.YYYY HH.MM"}`<br> 
  Access granted for Admin users.
- __PUT__ `/events/{event_id}`<br> 
  Update event with event_id <br>
  Data format: `{"performanceId" : "performance_id", "concertHallId" : "concert_hall_id",
  "showTime" : "DD.MM.YYYY HH.MM"}`<br> 
  Access granted for Admin users.
- __DELETE__ `/events/{event_id}`<br> 
  Delete event with event_id <br>
  Access granted for Admin users.
- __GET__ `/users/by-email?email={userEmail}`<br> 
  Find User by his email<br>
  Data format: `{"id" : "userId","email" : "user_email"}`<br>
  Access granted for Admin users.
- __POST__ `/shopping-carts/events?eventId={event_id}` <br>
  Adding ticket of Event with event_id into current logged User Shopping Cart<br>
  Access granted for authenticated users.
- __GET__ `/shopping-carts/by-user`<br> 
  Get current authenticated User Shopping Cart<br>
  Data format: `{"id" : "userId","tickets" : "this_user_tickets"}`<br>
  Access granted for authenticated users.
- __POST__ `/orders/complete` <br> 
  Complete order for current authenticated User<br> 
  Access granted for authenticated users.
- __GET__ `/orders` <br> 
  Get all orders of current authenticated User<br>
  Data format: `{"id" : "order_id","tickets" : "order_ticket_id's",
  "orderDate" : "DD.MM.YYYY HH.MM","userEmail" : "user_email"}`<br> 
  Access granted for authenticated users.
  # To start the project you need: <br>
- Download and install the [JDK](https://www.oracle.com/java/technologies/javase-downloads.html "Download JDK") <br>
- Download and install Apache [Tomcat](https://tomcat.apache.org/download-90.cgi "Download Tomcat") <br>
- Download and install [MySQL Server](https://dev.mysql.com/downloads/ "Download MySQL")<br>
- You can send requests using your registered credentials with [Postman](https://www.postman.com/downloads/ "Download Postman")
- Create "concert-agency" schema in your DB.
- Setup credentials in the file "/resources/db.properties" <br>
  + db.driver: leave as is, or use appropriate driver for the other DB than MySQL.  
  + db.url: jdbc:mysql://*"your_DB_URL:port"*/concert-agency  
  + db.use: *"your username"* <br>
  + db.password: *"your password"*<br>
- Setup admin login and password in the file "/util/InitialInject.java". 
  By default they are :<br>
  + ADMIN_EMAIL = "admin"
  + ADMIN_PWD = "1234"
- Run a project
- Add initial values into DB using admin credentials  
- Register new user
## Author
[Serhii Semenykhin](https://github.com/semyonich/ "GitHub")<br>
s.semenykhin@gmail.com
