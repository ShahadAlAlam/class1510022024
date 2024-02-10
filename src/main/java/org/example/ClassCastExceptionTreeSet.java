package org.example;// important import statement
import java.util.*;  

  
public class ClassCastExceptionTreeSet  
{  

    // main method
    public static void main(String[] argvs)
    {
    // creating objects of the class Employee
    Employee obj1 = new Employee();

    Employee obj2 = new Employee();

    TreeSet<Employee> ts =  new TreeSet<Employee>();

    // adding the employee objects to
    // the TreeSet class
    ts.add(obj1);
    ts.add(obj2);

    System.out.println("The program has been executed successfully.");

    }
}  