package edu.examenRecup.controller;

import edu.examenRecup.dao.UserDAO;
import edu.examenRecup.model.User;
import edu.examenRecup.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController  {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label errorLabel;

    private UserDAO userDAO;

    @FXML
    public void initialize() {
        userDAO = new UserDAO();

        loginButton.setOnAction(event -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Por favor, rellena todos los campos.");
                errorLabel.setVisible(true);
                errorLabel.setManaged(true);
                return;
            }

            User user = userDAO.authenticate(email, password);

            if (user != null) {
                Session.setCurrentUser(user);
                try {
                    App.setRoot("main_view");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                errorLabel.setText("Email o contraseña incorrectos.");
                errorLabel.setVisible(true);
                errorLabel.setManaged(true);
            }
        });
    }
}
