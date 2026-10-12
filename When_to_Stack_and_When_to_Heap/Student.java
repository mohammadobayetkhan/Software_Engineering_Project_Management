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
