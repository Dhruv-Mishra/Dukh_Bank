package com.example.dukh_bank_officialwebsite;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.net.URL;
import java.sql.*;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    backend system = new backend(2);

    @FXML
    private ImageView login_button;
    @FXML
    private ImageView captcha;

    public HelloController() throws SQLException {}

    @FXML
    Stage myStage;
    public void setMyStage(Stage s){
        myStage=s;
    }

    @FXML
    public TextField enterCap;
    @FXML
    int setCaptcha(){
        Random r = new Random();
        int xx = r.nextInt(5)+1;
        Image cap = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\captcha\\captcha"+Integer.toString(xx)+".png");
        captcha.setImage(cap);
        return xx;
    }
    public int init_captcha;
    @FXML
    void checkCaptcha() throws SQLException, IOException {
        int x= init_captcha;
        boolean f = false;
        System.out.println(enterCap.getText().compareTo("DLAQ"));
        if(x==1){
            if(enterCap.getText().compareTo("DLAQ")==0){
                f=true;
            }
        }
        else if(x==2){
            if(enterCap.getText().compareTo("qAjP")==0){
                f=true;
            }
        }
        else if(x==3){
            if(enterCap.getText().compareTo("7D6e")==0){
                f=true;
            }
        }
        else if(x==4){
            if(enterCap.getText().compareTo("g524")==0){
                f=true;
            }
        }
        else if(x==5){
            if(enterCap.getText().compareTo("Q87f")==0){
                f=true;
            }
        }

        if(f==false){
            password.clear();
            username.clear();
            init_captcha= setCaptcha();
        }
        else{
            test();
        }
    }

    @FXML
    void test() throws SQLException, IOException {
               String Account_Number= username.getText();
               // String Account_Number = "100000000015";
                int account_choice= system.verify_login_details(Account_Number, password.getText());
                if (account_choice == 1) { //person is a user
                    system=null;
                    system= new backend(0);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gg.fxml"));
                    Parent root = fxmlLoader.load();
                    DashboardController dashboardController = fxmlLoader.getController();
                    Account a = system.account_details(Long.parseLong(Account_Number));
                    System.out.println(Long.parseLong(Account_Number));
                    System.out.println(a);
                    dashboardController.sendBackend(system);
                    dashboardController.setUser(a);
                    dashboardController.load_dashboard();

                    Scene scene = new Scene(root);
                    System.out.println(scene);
                   // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Stage stage = myStage;
                    stage.setScene(scene);
                    System.out.println(stage);
                    stage.setResizable(false);
                    stage.show();
                }
                else if(account_choice==-1){
                    system=null;
                    system=new backend(1);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employee.fxml"));
                    Parent root = fxmlLoader.load();
                    Empcontroller empcontroller = fxmlLoader.getController();
                    Employee e = system.employee_details(Long.parseLong(Account_Number));
                    System.out.println(Long.parseLong(Account_Number));
                    System.out.println(e);
                    empcontroller.sendBackend(system);
                    empcontroller.setEmployee(e);
                    Scene scene = new Scene(root);
                    System.out.println(scene);
                    // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Stage stage = myStage;
                    stage.setScene(scene);
                    empcontroller.setDesignation(e.Designation);
                    System.out.println(stage);
                    stage.setResizable(false);
                    stage.show();
                }
                else {
                    System.out.println("Wrong Password or Username. Please retry.");
                }
    }

    ////  LOGIN PAGE ////
    @FXML
    protected PasswordField password;
    @FXML
    protected TextField username;
    @FXML
    public ImageView arrow;
    @FXML
    public ImageView arrow1;
    @FXML
    public ImageView arrow2;
    @FXML
    public ImageView arrow3;
    public void arrow_animation(ImageView arrow,int x,int time){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(arrow);
        translate.setDuration(Duration.millis(time));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(x);
        translate.setAutoReverse(true);
        translate.play();}
    ////  LOGIN PAGE ////
    @FXML
    public void selLogin_button(){
//        ScaleTransition st = new ScaleTransition(Duration.millis(200), login_button);
//        st.setByX(1.5f);
//        st.setByY(1.5f);
//        st.setAutoReverse(true);
//        st.play();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image login_buttonimg = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\assets\\login.png");
        login_button.setImage(login_buttonimg);
        init_captcha = setCaptcha();
        Image ar= new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\assets\\arrow1.png");
        arrow.setImage(ar);
        arrow_animation(arrow,500,30000);
        Image ar2= new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\assets\\arrow.png");
        arrow1.setImage(ar2);
        arrow_animation(arrow1,-600,33000);
    }
}