package com.example.dukh_bank_officialwebsite;

import java.sql.Date;

public class Credit_Card {
    long Card_Number;
    String Card_Network;
    java.sql.Date Expiry_date;
    java.sql.Date Date_of_Issue;
    int CVV;
    double Withdrawl_Limit;
    double Account_Number;
    double Used_Limit;

    public Credit_Card(long Card_Number,
                      String Card_Network,
                      java.sql.Date Expiry_date,
                      java.sql.Date Date_of_Issue,
                      int CVV,
                      double Used_Limit,
                      double Withdrawl_Limit,
                      double Account_Number){

        this.Card_Number= Card_Number;
        this.Card_Network= Card_Network;
        this.Expiry_date= Expiry_date;
        this.Date_of_Issue= Date_of_Issue;
        this.CVV= CVV;
        this.Withdrawl_Limit= Withdrawl_Limit;
        this.Used_Limit= Used_Limit;
        this.Account_Number= Account_Number;
    }
}
