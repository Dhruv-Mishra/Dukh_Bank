package com.example.dukh_bank_officialwebsite;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class LoanController {

    @FXML
    private Label amount;

    @FXML
    private Label dateofsanction;

    @FXML
    private Label loantype;

    @FXML
    private Label interestrate;

    @FXML
    private Label nextdueamount;

    @FXML
    private Label nextpaymentdate;

    @FXML
    private Label tenure;

    @FXML
    private Label amountpaid;

    public void set_data(Loans l ){

        amount.setText("Loan Amount: " + NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(l.Amount));
        dateofsanction.setText("Date Of Sanction: " + l.Date_of_Sanction.toString());
        loantype.setText("Loan Type: " + l.Loan_Type);
        interestrate.setText("Interest Rate: " + Double.toString(l.Interest_Rate) + " %");
        nextdueamount.setText("Next Due Amount: " + Double.toString(l.Next_Due_Amount));
        nextpaymentdate.setText("Next Payment Date: "+ l.Next_Payment_Date.toString() );
        tenure.setText("Tenure: "+ Integer.toString(l.Tenure));
        amountpaid.setText("Amount Paid: "+ NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(l.Amount_Paid));

    }

}
