package com.example.customermanagementsystemgui.controllers;
import com.example.customermanagementsystemgui.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
public class updateCustomerController implements Initializable {
    // initialize Method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load_customer_to_window();
    }
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
    private void update_button() {
        load_text_fields(HelloController.customer_to_update);
        cancel_button();
    }
    @FXML
    private void cancel_button() {
        Stage window = (Stage) name.getScene().getWindow();
        window.close();
    }
    // Methods
    private void load_text_fields(Customer customer) {
        customer.setName(name.getText());
        customer.setContract_id(Integer.parseInt(contract.getText()));
        customer.setNationality(nationality.getText());
        customer.setPhone(phone.getText());
        customer.setCurrent_bill(Double.parseDouble(current.getText()));
        customer.setAccumulated_bill(Double.parseDouble(accumulated.getText()));
    }
    private void load_customer_to_window() {
        Customer customer = HelloController.customer_to_update;
        contract.setText(Integer.toString(customer.getContract_id()));
        name.setText(customer.getName());
        nationality.setText(customer.getNationality());
        phone.setText(customer.getPhone());
        current.setText(Double.toString(customer.getCurrent_bill()));
        accumulated.setText(Double.toString(customer.getAccumulated_bill()));
    }
}