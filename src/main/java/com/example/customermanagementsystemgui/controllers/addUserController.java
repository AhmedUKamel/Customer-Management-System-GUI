package com.example.customermanagementsystemgui.controllers;
import com.example.customermanagementsystemgui.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
public class addUserController {
    // FXML Data fields
    @FXML
    private TextField contract;
    @FXML
    private TextField name;
    @FXML
    private TextField nationality;
    @FXML
    private TextField phone;
    @FXML
    private TextField current;
    @FXML
    private TextField accumulated;
    // FXML Methods
    @FXML
    private void add_button() {
        if(!name.getText().isEmpty() &&
        !contract.getText().isEmpty() &&
        !nationality.getText().isEmpty() &&
        !phone.getText().isEmpty() &&
        !accumulated.getText().isEmpty() &&
        !current.getText().isEmpty()) {
            if(is_array_list) {
                if(HelloController.array_list.insert(load_text_fields()))
                    cancel_button();
                else
                    HelloController.warning_window("Customer has not been added", "I will try again");
            } else {
                if(HelloController.linked_list.insert(load_text_fields()))
                    cancel_button();
                else
                    HelloController.warning_window("Customer has not been added", "I will try again");
            }
        } else
            HelloController.warning_window("Text fields may not be empty", "I will enter data");
    }
    @FXML
    private void cancel_button() {
        Stage current_window = (Stage) name.getScene().getWindow();
        current_window.close();
    }
    // Data Fields
    public static boolean is_array_list;
    // Methods
    private Customer load_text_fields() {
        Customer customer = new Customer();
        customer.setName(name.getText());
        customer.setContract_id(Integer.parseInt(contract.getText()));
        customer.setNationality(nationality.getText());
        customer.setPhone(phone.getText());
        customer.setCurrent_bill(Double.parseDouble(current.getText()));
        customer.setAccumulated_bill(Double.parseDouble(accumulated.getText()));
        return customer;
    }
}