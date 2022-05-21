package com.example.customermanagementsystemgui.controllers;
import com.example.customermanagementsystemgui.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
public class HelloController implements Initializable {
    // initialize Method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup_table_ll();
        setup_table_al();
        search_text_field_al.textProperty().addListener((observable, oldValue, newValue)-> {load_array_list();});
        search_text_field_ll.textProperty().addListener((observable, oldValue, newValue)-> {load_linked_list();});
    }
    // FXML Data fields
    @FXML
    private TableView tableView_ll;
    @FXML
    private TableColumn contract_column_ll;
    @FXML
    private TableColumn name_column_ll;
    @FXML
    private TableColumn nationality_column_ll;
    @FXML
    private TableColumn phone_column_ll;
    @FXML
    private TableColumn current_column_ll;
    @FXML
    private TableColumn accumulated_column_ll;
    @FXML
    private TableView tableView_al;
    @FXML
    private TableColumn contract_column_al;
    @FXML
    private TableColumn name_column_al;
    @FXML
    private TableColumn nationality_column_al;
    @FXML
    private TableColumn phone_column_al;
    @FXML
    private TableColumn current_column_al;
    @FXML
    private TableColumn accumulated_column_al;
    @FXML
    private TextField search_text_field_ll;
    @FXML
    private TextField search_text_field_al;
    // Linked List implementation
    @FXML
    private void choose_file_ll() {
        try {
            Stage window = new Stage();
            FileChooser chooser = new FileChooser();
            file_ll = chooser.showOpenDialog(window);
            path_ll = file_ll.toString();
            Scanner reader = new Scanner(file_ll);
            linked_list = new CustomerLinkedList();
            while(reader.hasNextLine()) {
                String[] splited = reader.nextLine().split("-");

                linked_list.insert(new Customer(splited[0], // Name
                        Integer.parseInt(splited[1]),       // Contract ID
                        splited[2],                         // Nationality
                        splited[3],                         // Phone
                        Double.parseDouble(splited[4]),     // Current Bill
                        Double.parseDouble(splited[5]))     // Accumulated Bill
                );
            }
            load_linked_list();
        } catch (Exception e) {
            System.out.println("ERROR READING FILE");
        }
    }
    @FXML
    private void save_file_ll() {
        try {
            if(linked_list != null) {
                File file = new File(path_ll);
                FileWriter writer = new FileWriter(file);
                for (int index = 0; index < linked_list.capacity(); index++)
                    writer.write(linked_list.get(index).line() + "\n");
                writer.close();
            } else
                warning_window("No data to save", "I got it");
        } catch (Exception e) {
            System.out.println("ERROR WRITING FILE");
        }
    }
    @FXML
    private void browse_ll() {
        try {
            if(linked_list != null) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                Stage current_window = (Stage) tableView_ll.getScene().getWindow();
                File selectedDirectory = directoryChooser.showDialog(current_window);
                File file = new File(selectedDirectory + "\\Data.txt");
                FileWriter writer = new FileWriter(file);
                for (int index = 0; index < linked_list.capacity(); index++)
                    writer.write(linked_list.get(index).line() + "\n");
                writer.close();
            } else
                warning_window("No data to save", "I got it");
        } catch (Exception e) {
            System.out.println("ERROR WRITING FILE");
        }
    }
    @FXML
    private void add_customer_action_ll() {
        if(linked_list == null)
            warning_window("You can't add a user while you haven't selected the file", "I will select file");
        else {
            addUserController.is_array_list = false;
            new_window("add-customer-view.fxml", "Add customer window", 350, 500);
            load_linked_list();
        }
    }
    @FXML
    private void delete_customer_action_ll() {
        if(linked_list == null) {
            warning_window("No data to delete", "Ok");
        } else {
            Customer selected = (Customer) tableView_ll.getSelectionModel().getSelectedItem();
            if(selected != null) {
                linked_list.delete(selected.getContract_id());
                load_linked_list();
            } else
                warning_window("No selected customers to delete", "Select customer");
        }
    }
    @FXML
    private void update_customer_action_ll() {
        if(linked_list == null) {
            warning_window("No data to update", "Ok");
        } else {
            customer_to_update = (Customer) tableView_ll.getSelectionModel().getSelectedItem();
            if(customer_to_update != null) {
                new_window("update-customer-view.fxml", "Update customer window", 350, 500);
                load_linked_list();
            } else
                warning_window("No selected customers to update", "Select customer");
        }
    }
    @FXML
    private void clear_customer_action_ll() {
        if(linked_list != null) {
            linked_list.clear();
            load_linked_list();
        } else
            warning_window("No data to clear", "Ok");
    }
    @FXML
    private void sort_customers_action_ll() {
        if(linked_list != null) {
            is_sort_array_list = false;
            new_window("sort-customers-view.fxml", "Sort customers window", 350, 500);
            load_linked_list();
        } else
        warning_window("No data to sort", "Ok");
    }
    @FXML
    private void linear_search_action_ll() {
        if(linked_list != null) {
            tableView_ll.getItems().clear();
            if(search_text_field_ll.getText().isEmpty()) {
                load_linked_list();
            } else {
                Customer result;
                try {
                    int customer_id = Integer.parseInt(search_text_field_ll.getText().strip());
                    result = linked_list.linear_search(customer_id);
                    if(result != null)
                        tableView_ll.getItems().add(result);
                } catch (Exception e) {
                    result = linked_list.linear_search(search_text_field_ll.getText().strip());
                    if(result != null)
                        tableView_ll.getItems().add(result);
                }
            }
        } else
            warning_window("No data to search in", "Ok");
    }
    @FXML
    private void binary_search_action_ll() {
        if(linked_list != null) {
            tableView_ll.getItems().clear();
            if(search_text_field_ll.getText().isEmpty()) {
                load_linked_list();
            } else {
                Customer result;
                try {
                    int customer_id = Integer.parseInt(search_text_field_ll.getText().strip());
                    result = linked_list.binary_search(customer_id);
                    if(result != null)
                        tableView_ll.getItems().add(result);
                } catch (Exception e) {
                    result = linked_list.binary_search(search_text_field_ll.getText().strip());
                    if(result != null)
                        tableView_ll.getItems().add(result);
                }
            }
        } else
            warning_window("No data to search in", "Ok");
    }
    // Array List implementation
    @FXML
    private void choose_file_al() {
        try {
            Stage window = new Stage();
            FileChooser chooser = new FileChooser();
            file_al = chooser.showOpenDialog(window);
            path_al = file_al.toString();
            Scanner reader = new Scanner(file_al);
            array_list = new ArrayCustomerList();
            while(reader.hasNextLine()) {
                String[] splited = reader.nextLine().split("-");
                array_list.insert(new Customer(splited[0],  // Name
                        Integer.parseInt(splited[1]),       // Contract ID
                        splited[2],                         // Nationality
                        splited[3],                         // Phone
                        Double.parseDouble(splited[4]),     // Current Bill
                        Double.parseDouble(splited[5]))     // Accumulated Bill
                );
            }
            load_array_list();
        } catch (Exception e) {
            System.out.println("ERROR READING FILE");
        }
    }
    @FXML
    private void save_file_al() {
        try {
            if(array_list != null) {
                File file = new File(path_al);
                FileWriter writer = new FileWriter(file);
                for (int index = 0; index < array_list.capacity(); index++)
                    writer.write(array_list.get(index).line() + "\n");
                writer.close();
            } else
                warning_window("No data to save", "I got it");
        } catch (Exception e) {
            System.out.println("ERROR WRITING FILE");
        }
    }
    @FXML
    private void browse_al() {
        try {
            if(array_list != null) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                Stage current_window = (Stage) tableView_al.getScene().getWindow();
                File selectedDirectory = directoryChooser.showDialog(current_window);
                File file = new File(selectedDirectory + "\\Data.txt");
                FileWriter writer = new FileWriter(file);
                for (int index = 0; index < array_list.capacity(); index++)
                    writer.write(array_list.get(index).line() + "\n");
                writer.close();
            } else
                warning_window("No data to save", "I got it");
        } catch (Exception e) {
            System.out.println("ERROR WRITING FILE");
        }
    }
    @FXML
    private void add_customer_action_al() {
        if(array_list == null)
            warning_window("You can't add a user while you haven't selected the file", "I will select file");
        else {
            addUserController.is_array_list = true;
            new_window("add-customer-view.fxml", "Add customer window", 350, 500);
            load_array_list();
        }
    }
    @FXML
    private void delete_customer_action_al() {
        if(array_list == null) {
            warning_window("No data to delete", "Ok");
        } else {
            Customer selected = (Customer) tableView_al.getSelectionModel().getSelectedItem();
            if(selected != null) {
                array_list.delete(selected.getContract_id());
                load_array_list();
            } else
                warning_window("No selected customers to delete", "Select customer");
        }
    }
    @FXML
    private void update_customer_action_al() {
        if(array_list == null) {
            warning_window("No data to update", "Ok");
        } else {
            customer_to_update = (Customer) tableView_al.getSelectionModel().getSelectedItem();
            if(customer_to_update != null) {
                new_window("update-customer-view.fxml", "Update customer window", 350, 500);
                load_array_list();
            } else
                warning_window("No selected customers to update", "Select customer");
        }
    }
    @FXML
    private void clear_customer_action_al() {
        if(array_list != null) {
            array_list.clear();
            load_array_list();
        } else
            warning_window("No data to clear", "Ok");
    }
    @FXML
    private void sort_customers_action_al() {
        if(array_list != null) {
            is_sort_array_list = true;
            new_window("sort-customers-view.fxml", "Sort customers window", 350, 500);
            load_array_list();
        } else
            warning_window("No data to sort", "Ok");
    }
    @FXML
    private void linear_search_action_al() {
        if(array_list != null) {
            tableView_al.getItems().clear();
            if(search_text_field_al.getText().isEmpty()) {
                load_array_list();
            } else {
                Customer result;
                try {
                    int customer_id = Integer.parseInt(search_text_field_al.getText().strip());
                    result = array_list.linear_search(customer_id);
                    if(result != null)
                        tableView_al.getItems().add(result);
                } catch (Exception e) {
                    result = array_list.linear_search(search_text_field_al.getText().strip());
                    if(result != null)
                        tableView_al.getItems().add(result);
                }
            }
        } else
            warning_window("No data to search in", "Ok");
    }
    @FXML
    private void binary_search_action_al() {
        if(array_list != null) {
            tableView_al.getItems().clear();
            if(search_text_field_al.getText().isEmpty()) {
                load_array_list();
            } else {
                Customer result;
                try {
                    int customer_id = Integer.parseInt(search_text_field_al.getText().strip());
                    result = array_list.binary_search(customer_id);
                    if(result != null)
                        tableView_al.getItems().add(result);
                } catch (Exception e) {
                    result = array_list.binary_search(search_text_field_al.getText().strip());
                    if(result != null)
                        tableView_al.getItems().add(result);
                }
            }
        } else
            warning_window("No data to search in", "Ok");
    }
    // Data Fields
    protected static ArrayCustomerList array_list;
    protected static CustomerLinkedList linked_list;
    private static String path_ll;
    private static String path_al;
    private static File file_ll;
    private static File file_al;
    protected static Customer customer_to_update;
    public static boolean is_sort_array_list;
    // Methods
    private void setup_table_ll() {
        name_column_ll.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        contract_column_ll.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("contract_id"));
        nationality_column_ll.setCellValueFactory(new PropertyValueFactory<Customer, String>("nationality"));
        phone_column_ll.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        current_column_ll.setCellValueFactory(new PropertyValueFactory<Customer, Double>("current_bill"));
        accumulated_column_ll.setCellValueFactory(new PropertyValueFactory<Customer, Double>("accumulated_bill"));
    }
    private void setup_table_al() {
        name_column_al.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        contract_column_al.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("contract_id"));
        nationality_column_al.setCellValueFactory(new PropertyValueFactory<Customer, String>("nationality"));
        phone_column_al.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        current_column_al.setCellValueFactory(new PropertyValueFactory<Customer, Double>("current_bill"));
        accumulated_column_al.setCellValueFactory(new PropertyValueFactory<Customer, Double>("accumulated_bill"));
    }
    protected static void warning_window(String warning_message, String button_message) {
        Stage window = new Stage();
        window.setTitle("Warning window");
        window.setMinHeight(200);
        window.setMinWidth(600);
        Label label = new Label(warning_message);
        label.setFont(new Font("sans serif", 20));
        Button button = new Button(button_message);
        button.setOnAction(e-> {
            Stage current_window = (Stage) button.getScene().getWindow();
            current_window.close();
        });
        VBox  vBox  = new VBox(label, button);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }
    private void new_window(String loader_path, String title, int height, int width) {
        try {
            Stage window = new Stage();
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(loader_path));
            Scene scene = new Scene(loader.load(), width, height);
            window.setTitle(title);
            window.setScene(scene);
            window.showAndWait();
            load_linked_list();
            load_array_list();
            window.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR LOADING WINDOW");
        }
    }
    protected void load_linked_list() {
        tableView_ll.getItems().clear();
        for(int i = 0; i < linked_list.capacity(); i++)
            tableView_ll.getItems().add(linked_list.get(i));
    }
    protected void load_array_list() {
        tableView_al.getItems().clear();
        for(int i = 0; i < array_list.capacity(); i++)
            tableView_al.getItems().add(array_list.get(i));
    }
}