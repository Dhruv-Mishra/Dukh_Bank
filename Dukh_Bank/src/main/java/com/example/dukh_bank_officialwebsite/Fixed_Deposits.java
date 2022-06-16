package com.example.dukh_bank_officialwebsite;

import java.sql.Date;

public class Fixed_Deposits implements Comparable{

    int FD_Number;
    double Principal_Amount;
    double Current_Amount;
    int Interest_Rate;
    java.sql.Date Date_of_Opening;
    String Maturation_Period;

    public Fixed_Deposits(int FD_Number,
                          double Principal_Amount,
                          double Current_Amount,
                          int Interest_Rate,
                          java.sql.Date Date_of_Opening,
                          String Maturation_Period){

        this.FD_Number= FD_Number;
        this.Principal_Amount= Principal_Amount;
        this.Current_Amount= Current_Amount;
        this.Interest_Rate= Interest_Rate;
        this.Date_of_Opening= Date_of_Opening;
        this.Maturation_Period= Maturation_Period;
    }

    @Override
    public int compareTo(Object o) {
        Fixed_Deposits t2 = (Fixed_Deposits) o;
        return -(this.Date_of_Opening.compareTo(t2.Date_of_Opening));
    }

}
