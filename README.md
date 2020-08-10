# mebank
Description: The purpose of this project is to load transaction records from csv file during boot time, and based on user input provide respective balance and no of transactions involved.

Design Considerations:
</br>
    Programming Language: Java 1.8
  </br>
    Build Tool: Maven
   </br>
    Packaging: Jar
  </br>
BootStrap Instructions:
  </br>
  On project root please run the below commands
    </br>
     a) mnv clean package
       </br>
     b) java -jar target/Transaction-0.0.1-SNAPSHOT.jar data.csv
       </br>
   
   OutPut: 
     </br>
Data file loaded successfully, 5 records parsed
  </br>
Enter AccountName:
  </br>
ACC334455
  </br>
Enter FromDate 'dd/MM/yyyy HH:mm:ss':
  </br>
 20/10/2018 12:00:00 
   </br>
Enter ToDate 'dd/MM/yyyy HH:mm:ss':
  </br>
20/10/2018 19:00:00            
  </br>
========================================================
  </br>
Transaction count is 2
  </br>
Relative balance for the period is 35.50
  </br>

    
