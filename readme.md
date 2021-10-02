# Basic Rest Apis
This application is made on Spring boot and Mysql is used for Database. The application consist of four api.

### Pre requisite
- JVM
- Spring boot 
- MySQL DB

### Architecture

- Spring MVC architecture is used in the development of the assignment.The architecture has 3 main
pillars , Controller, Service layer and Dao Layer <br/></br>
  
    - **Controller** - it takes the request from the front end and puts that request in the request object and 
send it to the Service layer<br/></br>

    - **Service layer** - it takes the request object from the controller and perform the bussiness logic and 
put it the entity object and send to Dao layer<br/></br>

    - **Dao layer**- it basically perform the operation on the database.It takes the object from the service layer
and do the required operation on the database.<br/></br>

- Every layer sends the response object back to previous layer . So the reponse object is sended from the Dao
to Service to Controller to FrontEnd

### Architecture Diagram
  https://github.com/Shivam4819/Nuchange-Inforatics/blob/master/src/architecture.png

### Api:

1. **storeurl**:
   
    This api store the url, count and short key into the database. <br/></br>

        curl --location --request GET 'http://localhost:8080/storeurl?url=MAIL.com'
2. **get**:
   
    This api increment the count value for the url and also send the shortkey as the response.
If the data is not present it send 404 error code in the response. <br/></br>

        curl --location --request GET 'http://localhost:8080/geturl?url=MAIL.com'
3. **count**: 
   
    This api return the count value for the url as the response.
 IF the data is not present it send 404 error code in the response.<br/></br>

        curl --location --request GET 'http://localhost:8080/count?url=MAIL.com'
 4. **list**:
    
    This api sends all the data from database in response in JSON format.

        curl --location --request GET 'http://localhost:8080/list'

### Database Schema:

- shortkey (Primary key, Integer)<br>
- url (Varchar)<br>
- count (Integer)
