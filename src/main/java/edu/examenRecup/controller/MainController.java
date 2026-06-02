package edu.examenRecup.controller;

import edu.examenRecup.model.User;
import edu.examenRecup.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML private Label welcomeLabel;
    @FXML private Label titleLabel;
    @FXML private Button logoutButton;
    @FXML private StackPane contentArea;

    @FXML
    public void initialize() {
        User user = Session.getCurrentUser();

        if (user != null) {
            welcomeLabel.setText("Hola, " + user.getNombre());
            titleLabel.setText("Gestión de Usuarios");
        }

        if (user != null && user.getRole().name().equals("admin")) {
            loadView("admin_view");
        } else {
            loadView("user_view");
        }

        logoutButton.setOnAction(event -> {
            Session.clear();
            try {
                App.setRoot("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadView(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/examenRecup/" + fxml + ".fxml"));
            Node view = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
