# Distributed Systems
This is the REST implementation on JAX-RS/Jersey of the 2018 Distributed Systems project. This web application was developed on Tomcat 7.0.92 using JDK1.8. It may run on newer versions of Tomcat but it can't run with JDK1.9 or newer.  An XML Schema Definition (XSD) document called *CarHireSchema.xsd* is provided at the root of this repository. XML documents can be generated from and validated against this document. This web application can handle requests based on JSON format, but is not the main feature of the project.
## Execution
This webapplication is packed in the booking-server.war file found at the root of this repository. This file can be passed to the Manager app of Tomcat to deploy it. It serves requests on Tomcat's default tcp port, usually port 8080.  
## Service Endpoints
Service URLs are of the format:  
`http://localhost:8080/carhire/{endpoint}`  

The following endpoints are available:  
### Car/Cars
* `http://localhost:8080/carhire/cars` *GET method* returns a list of rental cars
* `http://localhost:8080/carhire/car/{id}` *GET method* returns the specified car by {id}, which is a positive integer (ids 1-3 are already provisioned) 
* `http://localhost:8080/carhire/car` *POST method* creates a car specified by the XML document embedded in the body of the *POST* request
* `http://localhost:8080/carhire/car` *PUT method* updates a car specified by the XML document embedded in the body of the *PUT* request
* `http://localhost:8080/carhire/car/{id}` *DELETE method* removes the specified car by {id}, which is a positive integer  
### Customer/Customers
* `http://localhost:8080/carhire/customers` *GET method* returns a list of rental customers
* `http://localhost:8080/carhire/customer/{id}` *GET method* returns the specified customer by {id}, which is a positive integer (ids 1-3 are already provisioned) 
* `http://localhost:8080/carhire/customer` *POST method* creates a customer specified by the XML document embedded in the body of the *POST* request
* `http://localhost:8080/carhire/customer` *PUT method* updates a customer specified by the XML document embedded in the body of the *PUT* request
* `http://localhost:8080/carhire/customer/{id}` *DELETE method* removes the specified customer by {id}, which is a positive integer  
### Booking/Bookings
* `http://localhost:8080/carhire/bookings` *GET method* returns a list of rental bookings
* `http://localhost:8080/carhire/booking/{serialnumber}` *GET method* returns the specified booking by {serialnumber}, which is a string of the following format: three letters followed one or more digits (serial numbers 'AAA1234', 'AAA5678' and 'AAA9101' are already provisioned) 
* `http://localhost:8080/carhire/booking` *POST method* creates a booking specified by the XML document embedded in the body of the *POST* request
* `http://localhost:8080/carhire/booking` *PUT method* updates a booking specified by the XML document embedded in the body of the *PUT* request
* `http://localhost:8080/carhire/booking/{serialnumber}` *DELETE method* removes the specified booking by {serialnumber}, which is a string of the following format: three letters followed one or more digits  
## XML Sample Documents
### Car
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<car xmlns="http://sw.gmit.ie/ds/CarHire/model" id="1">
    <color>blue</color>
    <brand>Hyundai I10</brand>
    <model>I10</model>
    <purchaseDate>2018-12-11Z</purchaseDate>
</car>
```
### Customer
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<customer xmlns="http://sw.gmit.ie/ds/CarHire/model" id="1">
    <firstName>Tom</firstName>
    <lastName>Kelly</lastName>
    <mobileNumber>0893672867</mobileNumber>
    <email>TomKelly@yahoo.ie</email>
    <country>Ireland</country>
</customer>
```
### Booking
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<booking xmlns="http://sw.gmit.ie/ds/CarHire/model" number="AAA9101">
    <date>2018-12-11Z</date>
    <customer id="3">
        <firstName>Mark</firstName>
        <lastName>Healy</lastName>
        <mobileNumber>0896757835</mobileNumber>
        <email>Mark@gmail.com</email>
        <country>Ireland</country>
    </customer>
    <car id="3">
        <color>white</color>
        <brand>Hyundai Tucson</brand>
        <model>Tucson</model>
        <purchaseDate>2018-12-11Z</purchaseDate>
    </car>
</booking>
```