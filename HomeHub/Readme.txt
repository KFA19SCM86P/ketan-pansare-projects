Group 10:
Nishant Pathare A20444988
Soham Satam A20444486
Ketan Pansare A20449186


How to run the Project

1)  Place the "Project" folder in the directory "C:\apache-tomcat-7.0.34\webapps".
2)  Install MySQL db and MySQL workbench on your system.
3)  Install MongoDB version 3.2.2.
4)  Open file "Project\SQL_Queries.sql" in MySQL workbench and run the script to create the tables.
5)  Create a "data" and "db" folder inside C drive as "c:\data\db".
6)  To start Mongo DB server process, locate the “mongod.exe” stored in "C:\ProgramFiles\MongoDB\Server\3.2\bin" and click it.
7)  To start Mongo shell, locate the “mongo.exe” stored in "C:\Program Files\MongoDB\Server\3.2\bin" and click it.
8)  Create a database by entering command "use bestdealmongo" in Mongo shell.
9)  Create a collection manully by entering command "db.createCollection(myReviews)" in Mongo shell.
10)  Open file "Project\WEB-INF\classes\MongoDBDataStoreUtilities.java" and set the database name and collection name on line 18 and 19 respectively.
11)  Open file "Project\WEB-INF\classes\MySqlDataStoreUtilities.java" and set the port number, database name, username, and password on line 15.
12) Start the TOMCAT server.
13) Make sure MySQL server and MongoDB server are running.
14) Open browser and hit URL "http://localhost/Project".
15) Run the Python scripts in the files "BestBuyDealMatches.ipynb" and file "ProductRecommender.ipynb".


  

  Source lines of code written
  A: 12954 LOC

  Features:
  A:

    2.1 User Account/Profile/Transaction management & MySQL
    2.2 Recommender
    2.3 Twitter matches
    2.4 Analytics & Visual Reports                                  
    2.5 Reviews & Trending & MongoDB
    2.6 Auto-Complete Search feature
    2.7 Google MAPS - Near ME search feature
    2.8 Knowledge Graph Searches & Neo4J

 

  Assignments features that are NOT implemented?
  Neo4j

 Assignments features that are attempted but NOT functional?
  All attempted are functional
