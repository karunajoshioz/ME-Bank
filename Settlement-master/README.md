# ME Coding Challenge

Application will  load financial Transaction records from CSV file and calculate Relative Account balance 
of an Account at a given point of Time.

# Technologies Used:
Spring Boot, JPA, Restful Webservices, Jackson Data format CSV, H2 IN-Memory Database .
Csv File is read using Jackson data format csv library.

# Api Specifications:
Transaction records will be saved to database when user hit URL localhost:8080/api/v1/settlement.

Relative Account Balance is calculated Using:-
	The relative account balance is the sum of funds that were transferred to / from an
account in a given time frame, it does not account for funds that were in that account
prior to the timeframe.

If a transaction has a reversing transaction, this transaction will be omitted from the calculation, 
even if the reversing transaction is outside the given time frame.


# Endpoint for loading Transactions:localhost:8080/api/v1/settlement

# Endpoint for calculating relative Account balance:

localhost:8080/api/v1/settlement/transactions

Request Body:
 { "accountId" :"ACC334455", "fromDate" : "20/10/2018 12:00:00", "toDate": "20/10/2018 19:00:00" }


# Steps to run the code:

Maven  clean and build. 
The jar file will be available in the target file.
Copy jar and using cmd  type "java -jar Settlement-0.0.1-SNAPSHOT.jar"

