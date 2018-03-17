You can use this as an intro to RestFul webservice creation using Java. I created this for a test I took recently. There are a couple extra things you'll find in the example like a Verfier for URL parameters and a Cache. I am sure there are a hundred ways to do this so have fun chopping it up and making it your own. For the Cache, EHCache has its own set of Search Apis that you can use against a cache. If you have the time (which I didn't) I'd recommend looking into them. They look pretty sharp.

1. The following code was built and tested on Tomcat 9 running on a Mac.
2. Libraries used: jersey, jackson, joda and ehcache
3. You can use the following urls below to test the services, or modify them and send in any or zero parameters for testing.
4. For testing on my side I used Postman for both GET/PUT operations
5. Built this in Eclipse - Version: Oxygen.2 Release (4.7.2), Build id: 20171218-0600

topseller - GET
http://localhost:8080/rest_api_git/webapi/topsellers?count=10&startDate=2009-12-10 08:28:04&endDate=2010-02-08 17:46:39

getsalesorder - GET
http://localhost:8080/rest_api_git/webapi/getsalesorder?orderId=945161671

putsalesorder - PUT
http://localhost:8080/rest_api_git/webapi/putsalesorder?orderId=25&product=product key 1&count=5&createdAt=8400000&updatedAt=8400000

