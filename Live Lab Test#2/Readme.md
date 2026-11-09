# Problem Statement : Develop a web-based game that presents four mathematical challenges covering three areas:
01.Prime Number
   Determine whether a given integer is prime.
02.Modulus Operation
   Calculate the remainder of integer division.
03.Trigonometry
   Calculate sine, cosine or tangent values.

Players must enter their names, answer four questions, submit their responses and view their final scores.
The system must save each player's name, score, difficulty level and date/time in a database. Display the top 10 scores in a leaderboard.


# Game Rules : 
Prime number: Return 1 if prime; otherwise return 0. Numbers less than 2 are not prime.
Modulus: Accept integers only. The divisor must not be zero.
Trigonometry: Calculate the requested sine, cosine or tangent using Java's Math class.
Use degrees as input and convert them to radians before calculation.
Compare decimal answers using an appropriate tolerance.


# Development Tasks – Step by Step :

## TASK 01  Project Setup : 
### Screenshot : 

1. Apache Tomcate Configuration :
                            <img width="1093" height="912" alt="image" src="https://github.com/user-attachments/assets/01d72049-2cc9-420e-8256-c7f427ac7a3a" />


2. Project Structure :
                            <img width="811" height="868" alt="image" src="https://github.com/user-attachments/assets/075509a3-e853-457e-969d-b7088f51ab02" />
3. Database :
                            <img width="1042" height="860" alt="image" src="https://github.com/user-attachments/assets/1db60ac3-cb9b-40ff-ad20-da1910c0e25a" />

## TASK 02  Design the Game Interface : 
### Code : 
### Screenshot : 
1. Home Interface :
   <img width="747" height="550" alt="image" src="https://github.com/user-attachments/assets/701bc68f-f912-4378-a780-d8b2a16cb3a0" />
2. Asking For Prime number's checking integer : 
   <img width="752" height="422" alt="image" src="https://github.com/user-attachments/assets/30c4ea41-c148-4e89-b83e-8f655e1068ce" />
3. Question's Interface : 
   <img width="747" height="845" alt="image" src="https://github.com/user-attachments/assets/a8ef93ea-2371-4cdd-b0f6-2a96207373d6" />
4. Result Interface :
   <img width="755" height="746" alt="image" src="https://github.com/user-attachments/assets/b44f2bb8-2d27-423b-ae8d-242855d41862" />
5. Leaderboard Interface : 
   <img width="746" height="496" alt="image" src="https://github.com/user-attachments/assets/7a26c8e0-3adc-4f58-9cf7-4118a80d3e8e" />

## TASK 03  Develop Prime Number Function :
### Screenshot : 
   <img width="605" height="382" alt="image" src="https://github.com/user-attachments/assets/50879dfc-cb94-48c1-a59c-97c097feb69d" />
   
## TASK 04  Develop Modulus Function :
### Screenshot : 
   <img width="637" height="286" alt="image" src="https://github.com/user-attachments/assets/66bbd092-ba5e-40cd-b3ed-9fbf5dc2e4fa" />
   
## TASK 05  Develop Trigonometric Functions :
### Screenshot :
   <img width="700" height="755" alt="image" src="https://github.com/user-attachments/assets/7caacb31-c191-4e04-b09c-11f54f7b1123" />

## TASK 06  Implement Game Logic: 
Integrate the mathematical functions into the game.

Generate four questions: one prime, one modulus and two trigonometric.
Accept player answers.
Evaluate answers using the respective Java methods.
Calculate the total score. 

Answer : Actually game logic was implemented in two jsp files and two java files . They are GameServlet.java, MathService.java , game.jsp , prime-input.jsp
### JSP FIle : 
### Java File : 

## TASK 07  Database Design Using JDBC : 
### Screnshot  :
   <img width="1030" height="242" alt="image" src="https://github.com/user-attachments/assets/bda130e4-581c-4fc6-b4c0-f4427ec24b89" />

## TASK 08  Display Top 10 Scores : 
### Scrennshot : 
   <img width="745" height="532" alt="image" src="https://github.com/user-attachments/assets/c12054b7-fc7d-4db2-8448-5167fbf52572" />

## TASK 09  Testing and Validation :
These testing already provided in previous tasks . And the answer is valid for all numbers.

## TASK 10  Final Documentation and GitHub Submission : 
### Code and Other thing : 
   

