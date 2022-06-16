package com.example.dukh_bank_officialwebsite;

import java.sql.Date;
import java.util.ArrayList;

public class Account {
    private long Account_Number;
    private String First_Name;
    private String Last_Name;
    private java.sql.Date Date_Of_Birth;
    private long Phone_Number;
    private String Email;
    private String Gender;
    private double Balance;
    private String Account_Type;
    private String Address;
    private ArrayList<Debit_Card> debit_cards;
    private ArrayList<Credit_Card> credit_cards;
    private ArrayList<Loans> loans;
    private ArrayList<Transactions> transactions;
    private ArrayList<Fixed_Deposits> fixed_deposits;

    public Account(long Account_Number,
                   String First_Name,
                   String Last_Name,
                   java.sql.Date Date_Of_Birth,
                   long Phone_Number,
                   String Email,
                   String Gender,
                   double Balance,
                   String Account_Type,
                   String Address,
                   ArrayList<Debit_Card> debit_cards,
                   ArrayList<Credit_Card> credit_cards,
                   ArrayList<Fixed_Deposits> fixed_deposits,
                   ArrayList<Loans> loans,
                   ArrayList<Transactions> transactions){

        this.Account_Number= Account_Number;
        this.First_Name= First_Name;
        this.Last_Name= Last_Name;
        this.Date_Of_Birth= Date_Of_Birth;
        this.Phone_Number= Phone_Number;
        this.Email= Email;
        this.Gender= Gender;
        this.Balance= Balance;
        this.Account_Type= Account_Type;
        this.Address= Address;
        this.debit_cards = debit_cards;
        this.credit_cards = credit_cards;
        this.loans= loans;
        this.transactions= transactions;
        this.fixed_deposits= fixed_deposits;
    }

    public void setAccount_Number(long account_Number) {
        Account_Number = account_Number;
    }

    public void setAccount_Type(String account_Type) {
        Account_Type = account_Type;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public void setDate_Of_Birth(Date date_Of_Birth) {
        Date_Of_Birth = date_Of_Birth;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setCredit_cards(ArrayList<Credit_Card> credit_cards) {
        this.credit_cards = credit_cards;
    }

    public void setDebit_cards(ArrayList<Debit_Card> debit_cards) {
        this.debit_cards = debit_cards;
    }

    public void setFixed_deposits(ArrayList<Fixed_Deposits> fixed_deposits) {
        this.fixed_deposits = fixed_deposits;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public void setLoans(ArrayList<Loans> loans) {
        this.loans = loans;
    }

    public void setPhone_Number(long phone_Number) {
        Phone_Number = phone_Number;
    }

    public void setTransactions(ArrayList<Transactions> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<Debit_Card> getDebit_cards() {
        return debit_cards;
    }

    public ArrayList<Credit_Card> getCredit_cards() {
        return credit_cards;
    }

    public ArrayList<Fixed_Deposits> getFixed_deposits() {
        return fixed_deposits;
    }

    public Date getDate_Of_Birth() {
        return Date_Of_Birth;
    }

    public ArrayList<Loans> getLoans() {
        return loans;
    }

    public ArrayList<Transactions> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return Balance;
    }

    public long getAccount_Number() {
        return Account_Number;
    }

    public long getPhone_Number() {
        return Phone_Number;
    }

    public String getAccount_Type() {
        return Account_Type;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return Email;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public String getGender() {
        return Gender;
    }

    public String getLast_Name() {
        return Last_Name;
    }

}
