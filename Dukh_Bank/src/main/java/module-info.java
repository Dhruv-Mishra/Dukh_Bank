module com.example.dukh_bank_officialwebsite {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.dukh_bank_officialwebsite to javafx.fxml;
    exports com.example.dukh_bank_officialwebsite;
}