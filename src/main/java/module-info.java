module com.example.customermanagementsystemgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;


    opens com.example.customermanagementsystemgui to javafx.fxml;
    exports com.example.customermanagementsystemgui;
    exports com.example.customermanagementsystemgui.controllers;
    opens com.example.customermanagementsystemgui.controllers to javafx.fxml;
}