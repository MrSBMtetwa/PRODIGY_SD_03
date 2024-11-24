/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactsmanagementsystem;


/**
 *
 * @author sthandisobrighton
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class ContactManagerGUI extends Application {
    private final ContactManager contactManager;
    private ListView<String> contactListView;
    private TextField nameField, phoneField, emailField;
    private Button addButton, editButton, deleteButton;

    public ContactManagerGUI() {
        contactManager = new ContactManager();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
       
        contactListView = new ListView<>();
        nameField = new TextField();
        phoneField = new TextField();
        emailField = new TextField();
        addButton = new Button("Add Contact");
        editButton = new Button("Edit Contact");
        deleteButton = new Button("Delete Contact");

        
        VBox vbox = new VBox(10, new Label("Name:"), nameField, new Label("Phone:"), phoneField,
                new Label("Email:"), emailField, addButton, editButton, deleteButton, new Label("Contacts:"),
                contactListView);
        vbox.setPadding(new Insets(10));

     
        addButton.setOnAction(e -> addContact());
        editButton.setOnAction(e -> editContact());
        deleteButton.setOnAction(e -> deleteContact());


        updateContactList();

        
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setTitle("Contact Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addContact() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            Contact contact = new Contact(name, phone, email);
            contactManager.addContact(contact);
            updateContactList();
            clearFields();
        }
    }

    private void editContact() {
        int selectedIndex = contactListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                Contact contact = new Contact(name, phone, email);
                contactManager.editContact(selectedIndex, contact);
                updateContactList();
                clearFields();
            }
        }
    }

    private void deleteContact() {
        int selectedIndex = contactListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            contactManager.deleteContact(selectedIndex);
            updateContactList();
        }
    }

    private void updateContactList() {
        List<Contact> contacts = contactManager.getAllContacts();
        contactListView.getItems().clear();
        for (Contact contact : contacts) {
            contactListView.getItems().add(contact.toString());
        }
    }

    private void clearFields() {
        nameField.clear();
        phoneField.clear();
        emailField.clear();
    }
}

   
