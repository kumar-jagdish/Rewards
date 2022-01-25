# RewardsApplication

1. Pre-requisites : Eclipse with Java version 8 or above.
2. Download source code and import as import maven project in eclipse.
3. Right click project root and run as springboot app.
4. Hit the url : http://localhost:8080/rewards/customer/get/1 to get Response from the API where 1 represents customerId for whom points summary is fetched.
  
  This end point accepts a customer id and returns rewards points earned in each month and over all points earned. 
  
  Response : {"customerId":1,"monthlySummary":[{"yearMonth":"2021-11","points":300},{"yearMonth":"2021-12","points":384},{"yearMonth":"2022-01","points":5}],"totalPoints":689}
  
  This shows summary of points for customer with customerId =1 where yearMonth field represent month and the year with earned points and total points.

5. Points calculation logic : A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

6. Applicatiom is built using Spring Boot framework with in memory db h2.
7. URL can be used for other customersId=2 or 3 by replacing 1 at the end  of the url.

