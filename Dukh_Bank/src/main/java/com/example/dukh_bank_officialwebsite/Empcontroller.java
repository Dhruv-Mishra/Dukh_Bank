package com.example.dukh_bank_officialwebsite;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;

public class Empcontroller {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label Account_Holder;

    @FXML
    private ImageView exitButton;

    @FXML
    private ImageView Account_Image;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView newLoanButton;

    @FXML
    private ImageView newFDButton;

    @FXML
    private AnchorPane optionsPane;

    @FXML
    private ImageView createcard;

    @FXML
    private ImageView withdraw;

    @FXML
    private ImageView createacc;

    @FXML
    private Group depo_withd;

    @FXML
    private TextField transactionAmount;

    @FXML
    private TextField receiverAccountNumber;

    @FXML
    private Label transactionStatus;

    @FXML
    private Group newcard;

    @FXML
    private TextField create_accno;

    @FXML
    private Label fdStatus;

    @FXML
    private Group newBreak;

    @FXML
    private Group newLoanBreak;

    @FXML
    private TextField firstname;

    @FXML
    private Label FDStatus1;

    @FXML
    private TextField lastname;

    @FXML
    private TextField DOB;

    @FXML
    private TextField Phonenumber;

    @FXML
    private TextField Email;

    @FXML
    private TextField type;

    @FXML
    private TextField address;

    @FXML
    private TextField Gender;

    Employee e;
    backend system;
    @FXML
    private Label designation;

    @FXML
    void withdraw_animation(){
        optionsPane.setDisable(true);
        mainPane.setDisable(true);
        depo_withd.setDisable(false);
        transactionStatus.setText("");
        depo_withd.toFront();
        depo_withd.setLayoutX(600);
        depo_withd.setLayoutY(300.5);
    }

    @FXML
    void withdraw() throws SQLException {
        if(system.withdraw(Long.parseLong(receiverAccountNumber.getText()),Double.parseDouble(transactionAmount.getText()))){
            transactionStatus.setText("Success");

        }
        else{
            transactionStatus.setText("Failure");
        }

    }


    @FXML
    void deposit() throws SQLException {
        if(system.deposit(Long.parseLong(receiverAccountNumber.getText()),Double.parseDouble(transactionAmount.getText()))){
            transactionStatus.setText("Success");

        }
        else{
            transactionStatus.setText("Failure");
        }


    }

    @FXML
    void newcard_animation(){
        optionsPane.setDisable(true);
        mainPane.setDisable(true);
        newcard.setDisable(false);
        fdStatus.setText("");
        newcard.toFront();
        newcard.setLayoutX(408);
        newcard.setLayoutY(300.5);
    }

    @FXML
    void newcard_debit() throws SQLException {
      if( system.create_DebitCard(Long.parseLong(create_accno.getText()), "Visa")){
          fdStatus.setText("Success");
      }
      else{
          fdStatus.setText("Failure");
      }
    }

    @FXML
    void newcard_credit() throws SQLException {
        if(system.create_CreditCard(Long.parseLong(create_accno.getText()), "Visa",10000)){
            fdStatus.setText("Success");
        }
        else{
            fdStatus.setText("Failure");
        }
    }

    @FXML
    void closenewcard(){
        newcard.setDisable(true);
        optionsPane.setDisable(false);
        mainPane.setDisable(false);
        newcard.toBack();
        newcard.setLayoutX(800);
        newcard.setLayoutY(1300);

    }

    @FXML
    void closewithdraw(){
        depo_withd.setDisable(true);
        optionsPane.setDisable(false);
        mainPane.setDisable(false);
        depo_withd.toBack();
        depo_withd.setLayoutX(800);
        depo_withd.setLayoutY(1300);

    }

    public void setEmployee(Employee e){
        this.e=e;
    }

    public void setDesignation(String s){
        designation.setText(s);
        Account_Holder.setText(e.First_Name);
    }

    void sendBackend(backend system){this.system=system;}

    @FXML
    void createacc() throws SQLException {

        if(system.create_account(firstname.getText(),lastname.getText(),DOB.getText(),Long.parseLong(Phonenumber.getText()),Email.getText(),Gender.getText(),type.getText(),address.getText(),"1234")){
            FDStatus1.setText("Success");
        }
        else {
            FDStatus1.setText("Failure");
        }

    }
    @FXML
    void createaccanimation(){
        newLoanBreak.setDisable(false);
        optionsPane.setDisable(true);
        mainPane.setDisable(true);
        FDStatus1.setText("");
        newLoanBreak.toFront();
        newLoanBreak.setLayoutX(600);
        newLoanBreak.setLayoutY(50);

    }
    @FXML
    void closecreateacc(){
        newLoanBreak.setDisable(true);
        optionsPane.setDisable(false);
        mainPane.setDisable(false);
        newLoanBreak.toBack();
        newLoanBreak.setLayoutX(800);
        newLoanBreak.setLayoutY(1300);

    }





}






