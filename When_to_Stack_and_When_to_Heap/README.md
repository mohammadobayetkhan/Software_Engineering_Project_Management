## 1) Java Lab Practice Problem (1 Points)
Problem: Demonstrate Stack and Heap Memory in Java

Write a Java program that creates a Student class with an instance variable age and a method display(). Inside the method, declare a local variable marks. Create a Student object in the main() method and display the instance variable, local variable, and object identity using System.identityHashCode().

Sample Input
text
Age: 20
Marks: 85
Sample Output
text
Student Object Created
Object Identity Hash Code: 12345678
Instance Variable (age): 20
Local Variable (marks): 85
Note: The identity hash code may differ each time the program runs. It is not the actual memory address.

Mark Distribution (3 Points)
1 point: Correctly define the Student class and instance variable.

1 point: Declare and display the local variable inside display().

1 point: Create the object and display its identity hash code.

## Solution :



package Lab_Practice_Problem;

import java.sql.SQLOutput;
import java.util.Scanner;
public class Student {
    int age;

    void display(int marks) {
        System.out.println("Student Object Created");
        System.out.println("Object identify Hash Code : "+System.identityHashCode(this));
        System.out.println("Instance Variable(age) : "+age);
        System.out.println("Local Variable(marks) : "+marks);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Student s = new Student();
        System.out.print("Age : ");
        s.age = sc.nextInt();

        System.out.print("Marks : ");
        int marks = sc.nextInt();

        s.display(marks);
        sc.close();
    }
}




## Java Lab Practice Problem — Intermediate Level (3 Points)
Title: Demonstrating Stack Frames, Heap Objects, and Inner Classes

Problem:

Write a Java program that defines an Employee class with an instance variable salary and an instance method display(). Inside display(), declare a local variable bonus and create a local inner class named Report with a method show().

Your program should:

Create an Employee object and display its identity hash code.

Display the instance variable salary and local variable bonus.

Create a Report object and display its identity hash code and a message from its show() method.

Sample Input
text
Enter salary: 30000
Enter bonus: 5000
Sample Output
text
Employee Object Created
Employee Identity Hash Code: 12345678

Inside display() Method:
Salary (Instance Variable): 30000
Bonus (Local Variable): 5000

Report Object Created
Report Identity Hash Code: 87654321
Report: Employee salary is 30000

Memory Concept:
Employee object -> Heap
Report object -> Heap
salary -> Instance field
bonus -> Local variable in display()
show() -> Executes in its own method frame
Note: Identity hash codes are illustrative and may differ between executions. They are not actual memory addresses.

Mark Distribution (3 Points)
1 point: Correctly implement the Employee class, instance variable, and display() method.

1 point: Implement the local Report class and its show() method.

1 point: Display the object identity hash codes and correctly identify the instance field, local variable, and method execution frame.



## Solution : 

package Lab_Practice_Problem;

import java.util.Scanner;

public class Employee {
    int salary;

    Employee() {
        System.out.println("Employee Object Created");
        System.out.println("Employee Identity Hash Code: " + System.identityHashCode(this));
    }

    void display(int bonus) {
        System.out.println();
        System.out.println("Inside display() Method:");
        System.out.println("Salary (Instance Variable): " + salary);
        System.out.println("Bonus (Local Variable): " + bonus);

        class Report {
            void show() {
                System.out.println("Report: Employee salary is " + salary);
            }
        }

        Report r = new Report();
        System.out.println();
        System.out.println("Report Object Created");
        System.out.println("Report Identity Hash Code: " + System.identityHashCode(r));
        r.show();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter salary: ");
        int salary = sc.nextInt();
        System.out.print("Enter bonus: ");
        int bonus = sc.nextInt();

        System.out.println();
        Employee emp = new Employee();   
        emp.salary = salary;

        emp.display(bonus);

        System.out.println();
        System.out.println("Memory Concept:");
        System.out.println("Employee object -> Heap");
        System.out.println("Report object -> Heap");
        System.out.println("salary -> Instance field");
        System.out.println("bonus -> Local variable in display()");
        System.out.println("show() -> Executes in its own method frame");

        sc.close();
    }
}
