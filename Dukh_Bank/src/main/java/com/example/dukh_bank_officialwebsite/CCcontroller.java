package com.example.dukh_bank_officialwebsite;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.NumberFormat;
import java.util.Locale;

public class CCcontroller {

    @FXML
    private Label cardNumber;

    @FXML
    private Label validTill;

    @FXML
    private Label holderName;
    @FXML
    private ImageView skeleton;
    @FXML
    private ImageView network;
    @FXML
    private ImageView logo;

    String holder="";

    @FXML
    private Label limit;
    @FXML
    private Label cvv;
    public void setHolderName(String name){
        this.holder=name;
    }

    public void set_data(Credit_Card cc){
        cardNumber.setText(String.valueOf(cc.Card_Number).substring(0,4)+"   "+String.valueOf(cc.Card_Number).substring(4,8)+"   "+String.valueOf(cc.Card_Number).substring(8,12)+"   "+String.valueOf(cc.Card_Number).substring(12,16) );
        validTill.setText(String.valueOf(cc.Expiry_date).substring(5,7)+"/"+String.valueOf(cc.Expiry_date).substring(0,2));
        holderName.setText(this.holder);
        Image net= new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\card\\"+cc.Card_Network+".png");
        network.setImage(net);
        Image skel= new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\card\\skeleton.png");
        skeleton.setImage(skel);
        Image log = new Image("C:\\Users\\Dhruv\\IdeaProjects\\Dukh_Bank_OfficialWebsite\\src\\main\\java\\com\\example\\dukh_bank_officialwebsite\\card\\logo.png");
        logo.setImage(log);
        cvv.setText(String.valueOf(cc.CVV));
        limit.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format((cc.Withdrawl_Limit - cc.Used_Limit)));
    }


}
