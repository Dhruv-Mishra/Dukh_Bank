package com.example.dukh_bank_officialwebsite;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;


import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;

public class DashboardController{

    @FXML
    private ImageView Account_Image;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView transactions;

    @FXML
    private ImageView myProfile;

    @FXML
    private ImageView fixedDeposits;

    @FXML
    private ImageView loans;

    @FXML
    private ImageView cards;

    @FXML
    private ImageView exitButton;

    @FXML
    private Label balanceDisplay;
    @FXML
    public void selectT() throws InterruptedException {
        Image i = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\transactions2.png");
        transactions.setImage(i);
    }
    @FXML
    public void deselectT(){
        Image i = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\transactions.png");
        transactions.setImage(i);
    }
    @FXML
    public void selectFD() throws InterruptedException {
        Image i = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\fds2.png");
        fixedDeposits.setImage(i);
    }
    @FXML
    public void deselectFD(){
        Image i = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\fds1.png");
        fixedDeposits.setImage(i);
    }
    @FXML
    public void selectC() throws InterruptedException {
        Image i = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\cards2.png");
        cards.setImage(i);
    }
    @FXML
    public void deselectC(){
        Image i = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\cards1.png");
        cards.setImage(i);
    }
    @FXML
    public void selectL() throws InterruptedException {
        Image i = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\loans2.png");
        loans.setImage(i);
    }
    @FXML
    public void deselectL(){
        Image i = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\loans1.png");
        loans.setImage(i);
    }

    //Importing Backend
    protected backend system;
    void sendBackend(backend system){this.system=system;}
    //Importing Backend
    //Setting Dashboard
    public Account user;
    public void setUser(Account user){this.user = user;
    setDashboard();}
    @FXML
    private Label Account_Holder;
    @FXML
    private ImageView rupeeSymbol;
    @FXML
    private ImageView make_trans_button;
    @FXML
    private Button test2;
    @FXML
    private Group newTransaction;

    @FXML
    private TextField receiverAccountNumber;

    @FXML
    private TextField transactionAmount;

    @FXML
    private Label transactionStatus;

    @FXML ImageView newButton;

    @FXML
    private void setDashboard(){
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        Image i1= new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\assets\\newtransaction.png");
        Image i2= new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\assets\\newloan.png");
        Image i3= new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\assets\\newfd.png");
        newFDButton.setImage(i3);
        newLoanButton.setImage(i2);
        newTransactionButton.setImage(i1);
        Image i4 = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\assets\\repay.png");
        breakLoan.setImage(i4);
        balanceDisplay.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(user.getBalance()).substring(1));
        rupeeSymbol.setImage(new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\rupeeSymbol.png"));
        String name_pre = "Ms.";
        Image img = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\assets\\female.png");
        if(user.getGender().equals("male")){
            name_pre = "Mr.";
            img = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\assets\\male.png");
        }
        Account_Image.setImage(img);
        Account_Holder.setText(name_pre+user.getFirst_Name()+" "+user.getLast_Name());
    }

    public void load_dashboard(){
        newFDButton.setDisable(true);
        newLoanButton.setDisable(true);
        newTransactionButton.setDisable(true);
        breakLoan.setVisible(false);
        breakLoan.setDisable(true);
        breakFD.setDisable(true);
        newFDButton.setVisible(false);
        newLoanButton.setVisible(false);
        newTransactionButton.setVisible(false);
        breakFD.setVisible(false);
        grid.getChildren().clear();
        try {
            int column = 0;
            int row = 1;
            ArrayList<Transactions> displayTransactions= user.getTransactions();
            Collections.sort(displayTransactions);
            for(Transactions t :  displayTransactions){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("trans.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Transcontroller controller = fxmlLoader.getController();

                if(t.send){
                    if(system.account_details(t.Receiver)!=null) controller.set_details(system.account_details(t.Receiver).getFirst_Name(),t.DATETIME.toString(),t.Amount,true);
                }
                else{
                    if(system.account_details(t.Sender)!=null)controller.set_details(system.account_details(t.Sender).getFirst_Name(),t.DATETIME.toString(),t.Amount,false);
                }
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e)
        {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void load_transactions(){
        newFDButton.setDisable(true);
        newLoanButton.setDisable(true);
        newTransactionButton.setDisable(false);
        breakFD.setDisable(true);
        breakLoan.setVisible(false);
        breakLoan.setDisable(true);
        newFDButton.setVisible(false);
        newLoanButton.setVisible(false);
        newTransactionButton.setVisible(true);
        breakFD.setVisible(false);
        grid.getChildren().clear();
        try {
            int column = 0;
            int row = 1;
            ArrayList<Transactions> displayTransactions= user.getTransactions();
            Collections.sort(displayTransactions);
            for(Transactions t :  displayTransactions){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("trans.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Transcontroller controller = fxmlLoader.getController();

                if(t.send){
                    if(system.account_details(t.Receiver)!=null) controller.set_details(system.account_details(t.Receiver).getFirst_Name(),t.DATETIME.toString(),t.Amount,true);
                }
                else{
                    if(system.account_details(t.Sender)!=null)controller.set_details(system.account_details(t.Sender).getFirst_Name(),t.DATETIME.toString(),t.Amount,false);
                }
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e)
        {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private AnchorPane optionsPane;
    @FXML
    private AnchorPane mainPane;

    @FXML
    public void make_transaction(){
        newTransaction.setDisable(false);
        optionsPane.setDisable(true);
        mainPane.setDisable(true);
        newTransaction.toFront();
        newTransaction.setLayoutX(348);
        newTransaction.setLayoutY(300.5);
    }
    @FXML
    public void check_transaction() throws SQLException {
        int error_code = system.make_transaction(user, Long.parseLong(receiverAccountNumber.getText()), Double.parseDouble(transactionAmount.getText()),"Net Banking");
        System.out.println(Long.parseLong(receiverAccountNumber.getText()));
        System.out.println(error_code);
        if(error_code==-1){
            transactionStatus.setText("Insufficient Balance!");
        }
        else if(error_code==-1){
            transactionStatus.setText("Please Recheck the account Number.");
        }
        else{
            transactionStatus.setText("Transaction Successful!");
            balanceDisplay.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(user.getBalance()).substring(1));
            user=system.account_details(user.getAccount_Number());
            load_transactions();
        }
    }
    @FXML
    public void closenewTransaction(){
        newTransaction.setDisable(true);
        optionsPane.setDisable(false);
        mainPane.setDisable(false);
        newTransaction.toBack();
        newTransaction.setLayoutX(800);
        newTransaction.setLayoutY(1300);
        load_transactions();
    }
    @FXML
    private ImageView newLoanButton;

    @FXML
    private ImageView newFDButton;

    @FXML
    private ImageView newTransactionButton;
    @FXML
    public void load_fds() throws SQLException {
        newFDButton.setDisable(false);
        newLoanButton.setDisable(true);
        newTransactionButton.setDisable(true);
        breakFD.setDisable(false);
        breakLoan.setVisible(false);
        breakLoan.setDisable(true);
        newFDButton.setVisible(true);
        newLoanButton.setVisible(false);
        newTransactionButton.setVisible(false);
        breakFD.setVisible(true);

        try {
            int column = 0;
            int row = 1;
            ArrayList<Fixed_Deposits> displayFds= user.getFixed_deposits();
            //Collections.sort(displayTransactions);
            grid.getChildren().clear();
            for(Fixed_Deposits fd :  displayFds){
                System.out.println(fd);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("fd.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Fdcontroller controller = fxmlLoader.getController();
                controller.set_data(fd);
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e)
        {
            e.printStackTrace();

        }


    }
    @FXML
    public  void load_loans(){
        newFDButton.setDisable(true);
        newLoanButton.setDisable(false);
        newTransactionButton.setDisable(true);
        breakFD.setDisable(true);
        breakLoan.setVisible(true);
        breakLoan.setDisable(false);
        newFDButton.setVisible(false);
        newLoanButton.setVisible(true);
        newTransactionButton.setVisible(false);
        breakFD.setVisible(false);
        try {
            int column = 0;
            int row = 1;
            ArrayList<Loans> displayloans = user.getLoans();
            grid.getChildren().clear();
            for(Loans l :  displayloans){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("loan.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                LoanController controller = fxmlLoader.getController();
                controller.set_data(l);
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e)
        {
            e.printStackTrace();

        }
    }

    @FXML
    public void load_cards() throws SQLException {
        newFDButton.setDisable(true);
        newLoanButton.setDisable(true);
        newTransactionButton.setDisable(true);
        breakFD.setDisable(true);
        breakLoan.setVisible(false);
        breakLoan.setDisable(true);
        newFDButton.setVisible(false);
        newLoanButton.setVisible(false);
        newTransactionButton.setVisible(false);
        breakFD.setVisible(false);

        try {
            int column = 0;
            int row = 1;
            ArrayList<Credit_Card> displayCredit= user.getCredit_cards();
            ArrayList<Debit_Card> displayDebit= user.getDebit_cards();
            //Collections.sort(displayTransactions);
            grid.getChildren().clear();
            for(Credit_Card cc : displayCredit ){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cc.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CCcontroller controller = fxmlLoader.getController();
                controller.setHolderName(user.getFirst_Name()+" "+user.getLast_Name());
                controller.set_data(cc);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e)
        {
            e.printStackTrace();

        }
    }
    @FXML
    private ImageView breakFD;
    @FXML
    private Group newBreak;
    @FXML
    private TextField break_number;
    @FXML
    public void make_fdbreak_animation(){
        newBreak.setDisable(false);
        optionsPane.setDisable(true);
        mainPane.setDisable(true);
        newBreak.toFront();
        newBreak.setLayoutX(398);
        newBreak.setLayoutY(280.5);
    }
    @FXML
    private Label FDStatus;
    @FXML
    public void make_break() throws SQLException{
        if(system.break_FD(user.getAccount_Number(), Long.parseLong(break_number.getText()))){
            FDStatus.setText("Success");
            user=system.account_details(user.getAccount_Number());
            balanceDisplay.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(user.getBalance()).substring(1));
            load_fds();
        }
        else{
            FDStatus.setText("Invalid FD Number");
        }

    }
    @FXML
    public void closenewBreak(){
        newBreak.setDisable(true);
        optionsPane.setDisable(false);
        mainPane.setDisable(false);
        newBreak.toBack();
        newBreak.setLayoutX(800);
        newBreak.setLayoutY(1200);
    }
    @FXML
    private ImageView breakLoan;
    @FXML
    private Group newLoanBreak;
    @FXML
    private TextField loan_type_inf;
    @FXML
    public void make_loanbreak_animation(){
        newLoanBreak.setDisable(false);
        optionsPane.setDisable(true);
        mainPane.setDisable(true);
        newLoanBreak.toFront();
        newLoanBreak.setLayoutX(398);
        newLoanBreak.setLayoutY(280.5);
    }
    @FXML
    private Label loanbreakStatus;
    @FXML
    public void make_loanbreak() throws SQLException{
        if(system.pay_loan(user.getAccount_Number(),loan_type_inf.getText() )){
            loan_type_inf.setText("Success");
            user=system.account_details(user.getAccount_Number());
            balanceDisplay.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(user.getBalance()).substring(1));


            load_loans();
        }
        else{
            FDStatus.setText("Insufficient Balance");
        }

    }
    @FXML
    public void closenewLoanBreak(){
        newLoanBreak.setDisable(true);
        optionsPane.setDisable(false);
        mainPane.setDisable(false);
        newLoanBreak.toBack();
        newLoanBreak.setLayoutX(800);
        newLoanBreak.setLayoutY(1200);
    }
    @FXML
    private Group newLoan;

    @FXML
    private TextField loanType;

    @FXML
    private TextField loanAmount;
    @FXML
    private Label loanStatus;
    @FXML
    private TextField loanTenure;
    @FXML
    public void make_loan_animation(){
        newLoan.setDisable(false);
        optionsPane.setDisable(true);
        mainPane.setDisable(true);
        newLoan.toFront();
        newLoan.setLayoutX(598);
        newLoan.setLayoutY(280.5);
        }

    @FXML
    public void make_loan() throws SQLException{
        system.take_loan(user.getAccount_Number(), Long.parseLong(loanAmount.getText()), loanType.getText(),6,Double.parseDouble(loanTenure.getText()));
        loanStatus.setText("Success");
        user=system.account_details(user.getAccount_Number());
        balanceDisplay.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(user.getBalance()).substring(1));

        load_loans();

    }
    @FXML
    public void closenewLoans(){
        newLoan.setDisable(true);
        optionsPane.setDisable(false);
        mainPane.setDisable(false);
        newLoan.toBack();
        newLoan.setLayoutX(800);
        newLoan.setLayoutY(1300);
    }

    @FXML
    private Group newFD;

    @FXML
    private TextField fdAmount;

    @FXML
    private Label fdStatus;

    @FXML
    private TextField fdTenure;

    @FXML
    public void make_fd_animation(){
        newFD.setDisable(false);
        optionsPane.setDisable(true);
        mainPane.setDisable(true);
        newFD.toFront();
        newFD.setLayoutX(348);
        newFD.setLayoutY(300.5);
    }

    @FXML
    public void make_fd() throws SQLException{
        system.create_FD(user.getAccount_Number(), Double.parseDouble(fdAmount.getText()), 6,Double.parseDouble(fdTenure.getText()));
        fdStatus.setText("Success");
        user=system.account_details(user.getAccount_Number());
        balanceDisplay.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(user.getBalance()).substring(1));

        load_fds();
    }
    @FXML
    public void closenewFD(){
        newFD.setDisable(true);
        optionsPane.setDisable(false);
        mainPane.setDisable(false);
        newFD.toBack();
        newFD.setLayoutX(800);
        newFD.setLayoutY(1300);
    }







}
