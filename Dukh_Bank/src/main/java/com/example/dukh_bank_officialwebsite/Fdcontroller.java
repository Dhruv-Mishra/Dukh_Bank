package com.example.dukh_bank_officialwebsite;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.NumberFormat;
import java.util.Locale;

public class Fdcontroller {

    @FXML
    private Label fd_no;

    @FXML
    private Label principalamount;

    @FXML
    private Label currentamount;

    @FXML
    private Label interestrate;

    @FXML
    private Label dateofopenening;

    @FXML
    private Label maturationperiod;

    public void set_data(Fixed_Deposits f ){

        fd_no.setText("FD Number " + Integer.toString(f.FD_Number));
        principalamount.setText("Principal Amount : - " + NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(f.Principal_Amount));
        currentamount.setText("Current Amount : - " + NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(f.Current_Amount));
        interestrate.setText("Interset Rate : - " + Integer.toString(f.Interest_Rate) + " %");
        dateofopenening.setText("Date of Opening : - " + f.Date_of_Opening.toString());
        maturationperiod.setText("Maturation Period : - " + f.Maturation_Period.toString());


    }


}
