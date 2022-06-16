package com.example.dukh_bank_officialwebsite;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.NumberFormat;
import java.util.Locale;

public class Transcontroller {

    @FXML
    private ImageView image;

    @FXML
    private Label Name;

    @FXML
    private Label tofrom;
    @FXML
    private Label amount;

    @FXML
    private Label datetime;

    public void set_details(String name , String Datetime, Double Amount , boolean send){

        if(send){
            tofrom.setText("TO");
            Name.setText(name);
            amount.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(Amount));
            datetime.setText(Datetime);
           // image.setImage( new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\assets\\down_green.png"));
        }
        else{
            tofrom.setText("FROM");
            Name.setText(name);
            amount.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(Amount));
            datetime.setText(Datetime);
           // image.setImage( new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\assets\\down_green.png"));


        }


    }


}
