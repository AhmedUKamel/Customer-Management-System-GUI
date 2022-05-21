package com.example.customermanagementsystemgui.controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
public class sortCustomerController implements Initializable {
    // initialize Method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup_buttons();
    }
    // FXML Data fields
    @FXML
    private Button bubble_name;
    @FXML
    private Button insertion_name;
    @FXML
    private Button selection_name;
    @FXML
    private Button merge_name;
    @FXML
    private Button heap_name;
    @FXML
    private Button bubble_id;
    @FXML
    private Button insertion_id;
    @FXML
    private Button selection_id;
    @FXML
    private Button merge_id;
    @FXML
    private Button heap_id;
    // Methods
    private void setup_sort_buttons_ll() {
        bubble_name.setOnAction(e-> {
            HelloController.linked_list.bubble_sort();
            close_window();
        });
        bubble_id.setOnAction(e-> {
            HelloController.linked_list.bubble_sort_for_id();
            close_window();
        });
        selection_name.setOnAction(e-> {
            HelloController.linked_list.selection_sort();
            close_window();
        });
        selection_id.setOnAction(e-> {
            HelloController.linked_list.selection_sort_for_id();
            close_window();
        });
        insertion_name.setOnAction(e-> {
            HelloController.linked_list.insertion_sort();
            close_window();
        });
        insertion_id.setOnAction(e-> {
            HelloController.linked_list.insertion_sort_for_id();
            close_window();
        });
        merge_name.setOnAction(e-> {
            HelloController.linked_list.merge_sort();
            close_window();
        });
        merge_id.setOnAction(e-> {
            HelloController.linked_list.merge_sort_for_id();
            close_window();
        });
        heap_name.setOnAction(e-> {
            HelloController.linked_list.heap_sort();
            close_window();
        });
        heap_id.setOnAction(e-> {
            HelloController.linked_list.heap_sort_for_id();
            close_window();
        });
    }
    private void setup_sort_buttons_al() {
        bubble_name.setOnAction(e-> {
            HelloController.array_list.bubble_sort();
            close_window();
        });
        bubble_id.setOnAction(e-> {
            HelloController.array_list.bubble_sort_for_id();
            close_window();
        });
        selection_name.setOnAction(e-> {
            HelloController.array_list.selection_sort();
            close_window();
        });
        selection_id.setOnAction(e-> {
            HelloController.array_list.selection_sort_for_id();
            close_window();
        });
        insertion_name.setOnAction(e-> {
            HelloController.array_list.insertion_sort();
            close_window();
        });
        insertion_id.setOnAction(e-> {
            HelloController.array_list.insertion_sort_for_id();
            close_window();
        });
        merge_name.setOnAction(e-> {
            HelloController.array_list.merge_sort(0, HelloController.array_list.capacity() - 1);
            close_window();
        });
        merge_id.setOnAction(e-> {
            HelloController.array_list.merge_sort_for_id(0, HelloController.array_list.capacity() - 1);
            close_window();
        });
        heap_name.setOnAction(e-> {
            HelloController.array_list.heap_sort();
        });
        heap_id.setOnAction(e-> {
            HelloController.array_list.heap_sort_for_id();
            close_window();
        });
    }
    private void close_window() {
        Stage current_window = (Stage) bubble_name.getScene().getWindow();
        current_window.close();
    }
    private void setup_buttons() {
        if(HelloController.is_sort_array_list)
            setup_sort_buttons_al();
        else
            setup_sort_buttons_ll();
    }
}