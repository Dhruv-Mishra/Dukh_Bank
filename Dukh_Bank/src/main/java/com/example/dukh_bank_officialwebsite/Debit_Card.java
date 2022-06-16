package com.example.dukh_bank_officialwebsite;

import java.sql.Date;

public class Debit_Card {
    long Card_Number;
    String Card_Network;
    java.sql.Date Expiry_date;
    java.sql.Date Date_of_Issue;
    int CVV;
    double Account_Balance;
    double Account_Number;

    public Debit_Card(long Card_Number, String Card_Network, java.sql.Date Expiry_date, java.sql.Date Date_of_Issue, int CVV, double Account_Balance, double Account_Number){
        this.Card_Number= Card_Number;
        this.Card_Network= Card_Network;
        this.Expiry_date= Expiry_date;
        this.Date_of_Issue= Date_of_Issue;
        this.CVV= CVV;
        this.Account_Balance= Account_Balance;
        this.Account_Number= Account_Number;
    }
}
