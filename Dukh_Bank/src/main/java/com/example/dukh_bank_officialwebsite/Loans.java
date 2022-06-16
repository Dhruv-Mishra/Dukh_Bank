package com.example.dukh_bank_officialwebsite;

import java.sql.Date;

public class Loans {
    double Amount;
    long Account_Number;
    java.sql.Date Date_of_Sanction;
    String Loan_Type;
    double Interest_Rate;
    double Next_Due_Amount;
    java.sql.Date Next_Payment_Date;
    int Tenure;
    double Amount_Paid;

    public Loans(double Amount,
                 long Account_Number,
                 java.sql.Date Date_of_Sanction,
                 String Loan_Type,
                 double Interest_Rate,
                 double Next_Due_Amount,
                 java.sql.Date Next_Payment_Date,
                 int Tenure,
                 double Amount_Paid){

        this.Amount= Amount;
        this.Account_Number= Account_Number;
        this.Date_of_Sanction= Date_of_Sanction;
        this.Loan_Type= Loan_Type;
        this.Interest_Rate= Interest_Rate;
        this.Next_Due_Amount= Next_Due_Amount;
        this.Next_Payment_Date= Next_Payment_Date;
        this.Tenure= Tenure;
        this.Amount_Paid= Amount_Paid;
    }
}
