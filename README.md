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
- /register<br> New User registration endpoint<br>
  HTTP method: POST<br>
  Data format: {"email" : "yourEmail", "password" : "yourPassword",
  "repeatPassword" : "repeatYourPassword"}
  <br> Access granted for anybody.
- /concert-halls<br> Get all available Concert Halls<br>
  HTTP method: GET<br>
  Data format: {"id" : "concert_hall_Id","capacity" : "capacity","description" : "description"}
  <br> Access granted for authorized users.
- /concert-halls<br> Creating a Concert Halls<br>
  HTTP method: POST<br>
  Data format: {"capacity" : "capacity","description" : "description"}
  <br> Access granted for Admin users.
- /performances<br>
  Get all available Performances<br>
      HTTP method: GET<br>
      Data format: {"id" : "performance_Id","title" : "title","description" : "description"}
      <br> Access granted for authorized users.
- /performances<br> Creating a Concert Halls<br>
  HTTP method: POST<br>
  Data format: {"title" : "title","description" : "description"}
  <br> Access granted for Admin users.
- /events/available?performanceId={performance_id}&date=DD.MM.YYYY
  Get all available Events of Performance with performance_id 
  on date of format "DD.MM.YYYY"<br>
  HTTP method: GET<br>
  Data format: {"id" : "event_id","performance" : "performance_title",
  "concertHall" : "concert_hall_description","showTime" : "DD.MM.YYYY HH.MM"}
  <br> Access granted for authenticated users.
- /events<br> Creating an Event<br>
  HTTP method: POST<br>
  Data format: {"performanceId" : "performance_id", "concertHallId" : "concert_hall_id",
  "showTime" : "DD.MM.YYYY HH.MM"}
  <br> Access granted for Admin users.
- /events/{event_id}<br> Update event with event_id <br>
  HTTP method: PUT<br>
  Data format: {"performanceId" : "performance_id", "concertHallId" : "concert_hall_id",
  "showTime" : "DD.MM.YYYY HH.MM"}
  <br> Access granted for Admin users.
- /events/{event_id}<br> Delete event with event_id <br>
  HTTP method: DELETE
  <br> Access granted for Admin users.
- /users/by-email?email={userEmail}<br> Find User by his email<br>
  HTTP method: GET<br>
  Data format: {"id" : "userId","email" : "user_email"}
  <br> Access granted for Admin users.
- /shopping-carts/events?eventId={event_id} <br> Adding ticket of Event with event_id 
  into current logged User Shopping Cart<br>
  HTTP method: POST
  <br> Access granted for authenticated users.
- /shopping-carts/by-user <br> Get current authenticated User Shopping Cart <br>
  HTTP method: GET<br>
  Data format: {"id" : "userId","tickets" : "this_user_tickets"}
  <br> Access granted for authenticated users.
- /orders/complete <br> Complete order for current authenticated User <br>
  HTTP method: POST
  <br> Access granted for authenticated users.
- /orders <br> Get all orders of current authenticated User<br>
  HTTP method: GET<br>
  Data format: {"id" : "order_id","tickets" : "order_ticket_id's",
  "orderDate" : "DD.MM.YYYY HH.MM","userEmail" : "user_email"}
  <br> Access granted for authenticated users.
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
