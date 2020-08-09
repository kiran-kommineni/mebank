# mebank
Description: The purpose of this project is to load transaction records from csv file during boot time, and based on user input provide respective balance and no of transactions involved.

Design Considerations:
</br>
    Programming Language: Java 1.8
  </br>
    Build Tool: Maven
    Packaging: Jar

BootStrap Instructions:
  On project root please run the below commands
     a) mnv clean package
     b) java -jar target/Transaction-0.0.1-SNAPSHOT.jar data.csv
   
   OutPut: 
Data file loaded successfully, 5 records parsed
Enter AccountName:
ACC334455
Enter FromDate 'dd/MM/yyyy HH:mm:ss':
 20/10/2018 12:00:00 
Enter ToDate 'dd/MM/yyyy HH:mm:ss':
20/10/2018 19:00:00            
========================================================
Transaction count is 2
Relative balance for the period is35.50

    
