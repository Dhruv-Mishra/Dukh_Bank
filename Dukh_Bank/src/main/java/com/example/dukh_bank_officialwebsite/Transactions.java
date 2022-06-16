package com.example.dukh_bank_officialwebsite;

import java.sql.Timestamp;
import java.util.Comparator;

public class Transactions implements Comparable {
    long TransactionID;
    double Amount;
    long Sender;
    long Receiver;
    String Mode_Of_Payment;
    java.sql.Timestamp DATETIME;
    boolean send;

    public Transactions (long TransactionID,
                        double Amount,
                        long Sender,
                        long Receiver,
                        String Mode_Of_Payment,
                        java.sql.Timestamp DATETIME, boolean send){
        this.TransactionID= TransactionID;
        this.Amount= Amount;
        this.Sender= Sender;
        this.Receiver= Receiver;
        this.Mode_Of_Payment= Mode_Of_Payment;
        this.DATETIME= DATETIME;
        this.send=send;
    }

    @Override
    public int compareTo(Object o) {
        Transactions t2 = (Transactions) o;
        return -(this.DATETIME.compareTo(t2.DATETIME));
    }
}
