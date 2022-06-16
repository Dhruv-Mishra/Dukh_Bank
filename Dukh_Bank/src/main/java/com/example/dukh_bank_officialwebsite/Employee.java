package com.example.dukh_bank_officialwebsite;

import java.sql.Date;

public class Employee {
    long EmpID;
    String First_Name;
    String Last_Name;
    java.sql.Date Date_Of_Birth;
    long Phone_Number;
    String Email;
    String Gender;
    double Salary;
    String Designation;
    String Address;

    public Employee(long EmpID,
                    String First_Name,
                    String Last_Name,
                    java.sql.Date Date_Of_Birth,
                    long Phone_Number,
                    String Email,
                    String Gender,
                    double Salary,
                    String Designation,
                    String Address){

        this.EmpID= EmpID;
        this.First_Name= First_Name;
        this.Last_Name= Last_Name;
        this.Date_Of_Birth= Date_Of_Birth;
        this.Phone_Number= Phone_Number;
        this.Email= Email;
        this.Gender= Gender;
        this.Salary= Salary;
        this.Designation= Designation;
        this.Address= Address;
    }
}
