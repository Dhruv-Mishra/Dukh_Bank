package com.example.dukh_bank_officialwebsite;

import javafx.scene.control.Label;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class  backend {
    Connection mydb;

    backend(int num) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password = "root";
        try {
            Connection mydb = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

         if(num==0){
             mydb= DriverManager.getConnection("jdbc:mysql://localhost/dukh_bank","user","user");
         }
         if (num==1){
             mydb= DriverManager.getConnection("jdbc:mysql://localhost/dukh_bank","employee","employee");
         }
         if(num==2){
             mydb = DriverManager.getConnection("jdbc:mysql://localhost/dukh_bank", "root", "root");
         }

        String query = "show tables;";
        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getString(1).toString());
        }

    }

    int  verify_login_details(String username, String password) throws SQLException {
        int success = 0;
        String query;
        if (username == "" || password == "") {
            return 0;
        }
        try {
            Long.parseLong(username);
        } catch (NumberFormatException e) {
            return 0;
        }
        if (username.length() != 12) {
            String query3 = "Drop view if exists passwd_view ";
            Statement s3=mydb.createStatement();
            s3.executeUpdate(query3);

            String query2 = "Create view passwd_view AS SELECT `Password Hash`,`EmpID` FROM `Employee Login Details` Where `EmpID` = " + username ;
            Statement st = mydb.createStatement();
            st.executeUpdate(query2);
            query = "Select `Password Hash` from passwd_view where `EmpID` = " + username;
//            query = "Select `Password Hash` from `Employee Login Details` Where `EmpID` = " + username;

        }else {
            String query3 = "Drop view if exists passwd_view ";
            Statement s3=mydb.createStatement();
            s3.executeUpdate(query3);

            String query2 = "Create view passwd_view AS SELECT `Password Hash`,`Account Number` FROM `Customer Login Details` Where `Account Number` = " + username ;
            Statement st = mydb.createStatement();
            st.executeUpdate(query2);
            query = "Select `Password Hash` from passwd_view where `Account Number` = " + username;

        }

        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery(query);

        if (!rs.isBeforeFirst()) {
            success = 0;
        } else {
            rs.next();
            String org_passwd = (rs.getString(1).toString());
            System.out.println(org_passwd);
            if (org_passwd.equals(password) && username.length()!=12) {
                success = -1;
            }
            if(org_passwd.equals(password) && username.length()==12){
                success = 1;
            }
        }
        return success;
    }

    int make_transaction(Account sender, long receiver_no, double transaction_value, String mode) throws SQLException {
        if (sender.getBalance() < transaction_value) {
            return -1;
        } else if (account_details(receiver_no) == null) {
            return 0;
        } else {
            Account receiver = account_details(receiver_no);
            System.out.println(sender.getBalance());
           // System.out.println("UPDATE Accounts SET Balance = " + Double.toString(sender.getBalance()) + " WHERE `Account Number` = " + Long.toString(sender.getAccount_Number()) + ";");
            sender.setBalance(sender.getBalance() - transaction_value);
            receiver.setBalance(receiver.getBalance() + transaction_value);
            Statement st = mydb.createStatement();
            System.out.println(sender.getBalance());
            System.out.println("UPDATE Accounts SET Balance = " + Double.toString(sender.getBalance()) + " WHERE `Account Number` = " + Long.toString(sender.getAccount_Number()) + ";");
            st.executeUpdate("UPDATE Accounts SET Balance = " + Double.toString(sender.getBalance()) + " WHERE `Account Number` = " + Long.toString(sender.getAccount_Number()) + ";");
            st.executeUpdate("UPDATE Accounts SET Balance = " + Double.toString(receiver.getBalance()) + " WHERE `Account Number` = " + Long.toString(receiver.getAccount_Number()) + ";");

            ResultSet rs = st.executeQuery("select MAX(TransactionID) FROM Transactions");
            rs.next();
            long transaction_id = rs.getInt(1) + 1;

            java.util.Date date = new Date();
            java.sql.Timestamp ts = new Timestamp(date.getTime());
            String newts = ts.toString().substring(0,ts.toString().length()-1);
            System.out.println(String.format("INSERT into TRANSACTIONS values(%d,%f,%d,%d,%s,%s) ", transaction_id, transaction_value, sender.getAccount_Number(), receiver.getAccount_Number(), mode, newts));
            st.executeUpdate(String.format("INSERT into TRANSACTIONS values(%d,%f,%d,%d,\"%s\",\"%s\") ", transaction_id, transaction_value, sender.getAccount_Number(), receiver.getAccount_Number(), mode, newts));
            return 1;
        }
    }

    public Account account_details(long Number) throws SQLException {

        String query = "Select * from `Accounts` where `Account Number` =" + Long.toString(Number);
        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery(query);

        if (!rs.isBeforeFirst()) {
            return null;
        }

        rs.next();
        Long Account_Number = rs.getLong(1);
        String First_Name = rs.getString(2);
        String Last_Name = rs.getString(3);
        java.sql.Date Date_Of_Birth = rs.getDate(4);
        Long Phone_Number = rs.getLong(5);
        String Email = rs.getString(6);
        String Gender = rs.getString(7);
        double Balance = rs.getDouble(8);
        String Account_Type = rs.getString(9);
        String Address = rs.getString(10);
        Account a = new Account(Account_Number, First_Name, Last_Name, Date_Of_Birth, Phone_Number, Email, Gender, Balance, Account_Type, Address, get_debit_card_details(Account_Number), get_credit_card_details(Account_Number), get_fixed_deposits_details(Account_Number), get_loans(Account_Number), get_send_transactions(Account_Number));
        return a;
    }

    public Employee employee_details(long Number) throws SQLException {

        String query = "Select * from `Employee` where `EmpID` =" + Long.toString(Number);
        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery(query);

        if (!rs.isBeforeFirst()) {
            return null;
        }

        rs.next();
        Long EmpID = rs.getLong(1);
        String First_Name = rs.getString(2);
        String Last_Name = rs.getString(3);
        Double Salary = rs.getDouble(5);
        java.sql.Date Date_Of_Birth = rs.getDate(4);
        String Designation = rs.getString(6);
        String Address = rs.getString(7);
        String Gender = rs.getString(8);
        Long Phone_Number = rs.getLong(9);
        String Email = rs.getString(10);
        Employee e = new Employee(EmpID, First_Name, Last_Name, Date_Of_Birth,Phone_Number,Email, Gender, Salary,Designation,Address);
        return e;
    }

    void update_account_details(long Account_Number, String First_Name, String Last_Name, long Phone_Number, String Email, String Gender, String Address) throws SQLException {
        Statement st = mydb.createStatement();
        st.executeUpdate(String.format("UPDATE Accounts SET `First Name`=%s , `Last Name`=%s, `Phone Number`=%d, `Email`=%s, `Gender`=%s, `Address`=%s WHERE `Account Number`=%d", First_Name, Last_Name, Phone_Number, Email, Gender, Address, Account_Number));
    }

    boolean create_account(String First_Name, String Last_Name, String DOB, long Phone_Number, String Email, String Gender, String Account_Type, String Address, String Password) throws SQLException {
        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery("Select MAX(`Account Number`) FROM Accounts");
        if (!rs.isBeforeFirst()) return false;
        rs.next();
        long Account_Number = rs.getInt(1) + 1;

        st.executeQuery(String.format("INSERT INTO Accounts VALUES(%d,\"%s\",\"%s\",\"%s\",%d,\"%s\",\"%s\",0,\"%s\",\"%s\")", Account_Number, First_Name, Last_Name, DOB.toString(), Phone_Number, Email, Gender, Account_Type, Address));
        st.executeQuery(String.format("INSERT INTO `Customer Login Details` VALUES(%d,%s)", Account_Number, Password));
        return true;
    }

    boolean deposit(long Account_Number, double Amount) throws SQLException {
        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery("Select Balance from Accounts where `Account Number`=" + Long.toString(Account_Number));
        if (!rs.isBeforeFirst()) return false;
        rs.next();
        double val = rs.getDouble(1) + Amount;

        st.executeUpdate(String.format("Update Accounts Set Balance=%f where `Account Number`=%d", val, Account_Number));
        return true;
    }

    boolean withdraw(long Account_Number, double Amount) throws SQLException {
        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery("Select Balance from Accounts where `Account Number`=" + Long.toString(Account_Number));
        if (!rs.isBeforeFirst()) return false;
        rs.next();
        double val = rs.getDouble(1);
        if(val<Amount) return false;

        st.executeUpdate(String.format("Update Accounts Set Balance=balance-%f where `Account Number`=%d", Amount, Account_Number));
        return true;
    }



    boolean create_FD(long Account_Number, double Principal_Amount, double Interest_Rate, double Maturation_Period) throws SQLException {
        java.util.Date date = new Date();
        java.sql.Date ts = new java.sql.Date(date.getTime());
        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery("Select MAX(`FD Number`) FROM `Fixed Deposits`");
        if (!rs.isBeforeFirst()) return false;
        rs.next();
        long fd_number = rs.getInt(1) + 1;

        rs = st.executeQuery(String.format("Select Balance From Accounts where `Account Number`=%d",Account_Number));
        if (!rs.isBeforeFirst()) return false;
        rs.next();

        if(rs.getDouble(1)<Principal_Amount) return false;

        String maturation_period = Integer.toString((int)Maturation_Period) +" years";
        st.executeUpdate(String.format("INSERT INTO `Fixed Deposits` VALUES(%d,%f,%f,%f,\"%s\",\"%s\")", fd_number, Principal_Amount, Principal_Amount, Interest_Rate, ts.toString(),maturation_period ));
        st.executeUpdate(String.format("INSERT INTO `Creates FD` VALUES(%d,%d)", Account_Number, fd_number));
        st.executeUpdate(String.format("Update Accounts set Balance=Balance-%f where `Account number`=%d",Principal_Amount,Account_Number));
        return true;
    }

    boolean break_FD(long Account_Number, long FD_Number) throws SQLException {
        String query = "Select `Account Number` from `Creates FD` where `FD Number` =" + Long.toString(FD_Number);
        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (!rs.isBeforeFirst()) return false;
        rs.next();

        if (rs.getLong(1) != Account_Number) return false;
        else {
//             ResultSet r= st.executeQuery(String.format("SELECT `Current Amount` FROM `Fixed Deposits` where `FD Number=%d`",FD_Number));
//             r.next();
//             double amount=r.getDouble(1);
//             ResultSet r1= st.executeQuery(String.format("SELECT `Balance` FROM `Accounts` where `Account Number=%d`",Account_Number));
//             r1.next();
//             amount+=r1.getDouble(1);
//
//             st.executeUpdate(String.format("UPDATE Accounts SET Balance=%f WHERE `Account Number`=%d",amount,Account_Number));
            st.executeUpdate(String.format("Delete FROM `Fixed Deposits` WHERE `FD Number`=%d", FD_Number));
            return true;
        }
    }

    boolean create_CreditCard(long Account_Number, String Card_network, double WithDrawl_Limit) throws SQLException {
        java.util.Date date = new Date();
        java.sql.Date ts = new java.sql.Date(date.getTime());
//        java.util.Calendar expiry_date = date_of_issue;
//        expiry_date.set(date_of_issue.getWeekYear(), );
//
        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery("Select MAX(`Card Number`) FROM `Credit Card`");
        if (!rs.isBeforeFirst()) return false;
        rs.next();
        long card_number = rs.getLong(1) + 1;

        int cvv = (int) ((Math.random() * 1000) % 900 + 100);
        st.executeUpdate(String.format("INSERT INTO `Credit Card`(`Card Number`,`Card Network`,`Date Of Issue`,`CVV`,`Withdrawl Limit`,`Account Number`, `Used Limit`) VALUES(%d,\"%s\",\"%s\",%d,%f,%d,0)", card_number, Card_network,ts.toString(), cvv, WithDrawl_Limit, Account_Number));
        return true;
    }

    boolean deactivate_DebitCard(long Account_Number, long Card_Number) throws SQLException {
        String query = "Select `Account Number` from `Debit Card` where `Card Number` =" + Long.toString(Card_Number);
        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (!rs.isBeforeFirst()) return false;
        rs.next();

        if (rs.getLong(1) != Account_Number) return false;
        else {
            st.executeQuery(String.format("Delete FROM `Debit Card` WHERE `Card Number`=%d", Card_Number));
            return true;
        }
    }

    boolean deactivate_CreditCard(long Account_Number, long Card_Number) throws SQLException {
        String query = "Select `Account Number` from `Credit Card` where `Card Number` =" + Long.toString(Card_Number);
        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (!rs.isBeforeFirst()) return false;
        rs.next();

        if (rs.getLong(1) != Account_Number) return false;
        rs = st.executeQuery(String.format("Select `Balance` from Accounts where `Account Number`=%d", Account_Number));
        ResultSet r = st.executeQuery("Select `Used Limit` from `Credit Card` where `Card Number` =" + Long.toString(Card_Number));
        rs.next();
        r.next();
        if (rs.getDouble(1) < r.getDouble(1)) return false;
        else {
            st.executeQuery(String.format("Delete FROM `Credit Card` WHERE `Card Number`=%d", Card_Number));
            return true;
        }
    }

    boolean create_DebitCard(long Account_Number, String Card_network) throws SQLException {
        java.util.Date date = new Date();
        java.sql.Date ts = new java.sql.Date(date.getTime());
//        expiry_date.set(date_of_issue.getWeekYear(), );
//
        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery("Select MAX(`Card Number`) FROM `Debit Card`");
        if (!rs.isBeforeFirst()) return false;
        rs.next();
        long card_number = rs.getLong(1) + 1;

        int cvv = (int) ((Math.random() * 1000) % 900 + 100);

        double Balance = account_details(Account_Number).getBalance();

        st.executeUpdate(String.format("INSERT INTO `Debit Card`(`Card Number`,`Card Network`,`Date Of Issue`,CVV,`Account Balance`,`Account Number`) VALUES(%d,\"%s\",\"%s\",%d,%f,%d)", card_number, Card_network, ts.toString(), cvv, Balance, Account_Number));
        return true;
    }

    void take_loan(long Account_Number, double Amount, String Loan_Type, double Interest_Rate, double Tenure) throws SQLException {
        java.util.Date date_of_sanction = new Date();
        java.sql.Date ts = new java.sql.Date(date_of_sanction.getTime());

        double next_due_amount = (Amount / Tenure) * (1 + Interest_Rate);
        Statement st = mydb.createStatement();
        st.executeUpdate(String.format("INSERT INTO Loans(Amount,`Account Number`,`Date of Sanction`,`Loan Type`,`Interest rate`,`next due amount`,`tenure`,`Amount paid`) VALUES(%f,%d,\"%s\",\"%s\",%f,%f,%d,0)", Amount, Account_Number, ts.toString(), Loan_Type, Interest_Rate, next_due_amount, (int)Tenure));
    }

    boolean pay_loan(long Account_Number, String Loan_Type) throws SQLException{

        Statement st = mydb.createStatement();
        ResultSet rs = st.executeQuery(String.format("Select `Next Due Amount` from Loans Where `Account Number`=%d and `Loan Type`=\"%s\"",Account_Number,Loan_Type));
        if (!rs.isBeforeFirst()) return false;
        rs.next();
        double val = rs.getDouble(1);

        rs = st.executeQuery(String.format("Select `Balance` from Accounts Where `Account Number`=%d",Account_Number));
        if (!rs.isBeforeFirst()) return false;
        rs.next();

        if(val>rs.getDouble(1)) return false;
        st.executeUpdate(String.format("Update Loans set `Next due amount`=`Next due amount`- %d,`Amount Paid`=`Amount Paid`+ %d  where `Account Number`=%d and `Loan Type`=\"%s\"",(int)val,(int)val,Account_Number,Loan_Type));
        st.executeUpdate(String.format("Update Accounts set `Balance`=`Balance`-%f where `Account Number`=%d",val,Account_Number));
        return true;
    }

     ArrayList<Debit_Card> get_debit_card_details (long Number) throws SQLException {
         String query = "Select * from `Debit Card` where `Account Number` =" + Long.toString(Number);
         Statement st= mydb.createStatement();
         ResultSet rs= st.executeQuery(query);
         ArrayList<Debit_Card> list_of_cards = new ArrayList<Debit_Card>();
         if(!rs.isBeforeFirst()){ return list_of_cards ; }
         while( rs.next()) {

            Long Card_Number = rs.getLong(1);
            String Card_Network = rs.getString(2);
            java.sql.Date Expiry_Date = rs.getDate(3);
            java.sql.Date Date_Of_Issue = rs.getDate(4);
            int CVV = rs.getInt(5);
            Float Account_Balance = rs.getFloat(6);
            long Account_Number = rs.getLong(7);
            Debit_Card d = new Debit_Card(Card_Number, Card_Network, Expiry_Date, Date_Of_Issue, CVV, Account_Balance, Account_Number);
            list_of_cards.add(d);
         }

         return list_of_cards;

     }

     ArrayList<Credit_Card> get_credit_card_details(long Number) throws SQLException {

        String query = "Select * from `Credit Card` where `Account Number` =" + Long.toString(Number);
        Statement st= mydb.createStatement();
        ResultSet rs= st.executeQuery(query);
        ArrayList<Credit_Card> list_of_cards = new ArrayList<Credit_Card>();
        if(!rs.isBeforeFirst()){ return list_of_cards ; }

        while( rs.next()) {

            Long Card_Number = rs.getLong(1);
            String Card_Network = rs.getString(2);
            java.sql.Date Expiry_Date = rs.getDate(3);
            java.sql.Date Date_Of_Issue = rs.getDate(4);
            int CVV = rs.getInt(5);
            Float Withdrawl_limit = rs.getFloat(6);
            long Account_Number = rs.getLong(7);
            Float Used_limit = rs.getFloat(8);
            Credit_Card c = new Credit_Card(Card_Number, Card_Network, Expiry_Date, Date_Of_Issue, CVV, Used_limit, Withdrawl_limit,Account_Number);
            list_of_cards.add(c);
        }

        return list_of_cards;

    }

     ArrayList<Fixed_Deposits> get_fixed_deposits_details(long Account_Number ) throws SQLException {

        String query = "Select `FD Number` from `Creates FD` where `Account Number` =" + Long.toString(Account_Number);
        Statement st= mydb.createStatement();
        ResultSet rs= st.executeQuery(query);
        ArrayList<Integer> list_of_FD_numbers = new ArrayList<Integer>();
        ArrayList<Fixed_Deposits> list_of_FDs = new ArrayList<Fixed_Deposits>();
        if(!rs.isBeforeFirst()){ return list_of_FDs ; }

        while(rs.next()){
            Integer FD_number  = rs.getInt(1);
            list_of_FD_numbers.add(FD_number);
        }


        for(Integer FD_number : list_of_FD_numbers){

            String query2 = "Select * from `Fixed Deposits` where `FD Number` =" + Integer.toString(FD_number);
            Statement st2 = mydb.createStatement();
            ResultSet rs2 = st.executeQuery(query2);

            while( rs2.next()) {

                Float Principal_Amount = rs2.getFloat(2);
                Float Current_Amount  = rs2.getFloat(3);
                Integer Interest_rate = rs2.getInt(4);
                java.sql.Date Date_Of_Opening= rs2.getDate(5);
                String Maturation_Period  = rs2.getString(6);
                Fixed_Deposits f = new Fixed_Deposits(FD_number, Principal_Amount,Current_Amount, Interest_rate, Date_Of_Opening,Maturation_Period);
                list_of_FDs.add(f);
            }

        }
        return list_of_FDs;
    }

     ArrayList<Loans> get_loans(long Account_Number) throws SQLException {

        String query = "Select * from `loans` where `Account Number` =" + Long.toString(Account_Number);
        Statement st= mydb.createStatement();
        ResultSet rs= st.executeQuery(query);
        ArrayList<Loans> list_of_loans = new ArrayList<Loans>();
        if(!rs.isBeforeFirst()){ return list_of_loans ; }

        while( rs.next()) {
            Float Loan_Amount = rs.getFloat(1);
            String loan_type = rs.getString(4);
            java.sql.Date date_of_sanction = rs.getDate(3);
            java.sql.Date next_payment_date = rs.getDate(7);
            Float interest_rate = rs.getFloat(5);
            Float next_due_amount = rs.getFloat(6);
            int Tenure = rs.getInt(8);
            Float amount_paid = rs.getFloat(9);
            Loans l = new Loans(Loan_Amount,Account_Number,date_of_sanction,loan_type,interest_rate,next_due_amount,next_payment_date,Tenure,amount_paid);
            list_of_loans.add(l);
        }

        return list_of_loans;

    }

     ArrayList<Transactions> get_send_transactions (long Number) throws SQLException {

        String query = "Select * from `Transactions` where `Sender` =" + Long.toString(Number);
        Statement st= mydb.createStatement();
        ResultSet rs= st.executeQuery(query);
        ArrayList<Transactions> list_of_transactions = new ArrayList<Transactions>();
        if(rs.isBeforeFirst()) {

            while (rs.next()) {

                Integer Transaction_ID = rs.getInt(1);
                Float Amount = rs.getFloat(2);
                Long receiver_Account_Number = rs.getLong(4);
                String Mode_of_Payment = rs.getString(5);
                java.sql.Timestamp time_stamp = rs.getTimestamp(6);
                Transactions t = new Transactions(Transaction_ID, Amount, Number, receiver_Account_Number, Mode_of_Payment, time_stamp, true);
                list_of_transactions.add(t);

            }
        }


         String query2 = "Select * from `Transactions` where `receiver` =" + Long.toString(Number);
         Statement st2= mydb.createStatement();
         ResultSet rs2= st2.executeQuery(query2);
         if(!rs2.isBeforeFirst()){ return list_of_transactions ; }

         while( rs2.next()) {

             Integer Transaction_ID = rs2.getInt(1);
             Float Amount = rs2.getFloat(2);
             Long sender_Account_Number = rs2.getLong(3);
             String Mode_of_Payment = rs2.getString(5);
             java.sql.Timestamp time_stamp = rs2.getTimestamp(6);
             Transactions t = new Transactions(Transaction_ID,Amount,sender_Account_Number,Number,Mode_of_Payment,time_stamp,false);
             list_of_transactions.add(t);

         }

        return list_of_transactions;

    }






}






